package help.lixin.workflow.bridge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.model.bpmn.instance.ExtensionElements;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.constants.Constant;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.core.pipeline.ctx.impl.StagePipelineContext;
import help.lixin.core.pipeline.interceptor.IActionExecuteInterceptor;
import help.lixin.core.pipeline.mediator.ActionMediator;
import help.lixin.core.log.Log;
import help.lixin.workflow.constant.WorkflowConstants;
import help.lixin.workflow.service.IProcessExecuteFailCallback;

public class Camunda7PluginBridge implements JavaDelegate {

    private Logger logger = LoggerFactory.getLogger(Camunda7PluginBridge.class);

    private ActionMediator mediator;
    private List<IActionExecuteInterceptor> interceptors = new ArrayList<>(0);
    private List<IProcessExecuteFailCallback> processExecuteFailCallbacks = new ArrayList<>(0);

    public Camunda7PluginBridge(ActionMediator mediator, //
        List<IActionExecuteInterceptor> interceptors, //
        List<IProcessExecuteFailCallback> processExecuteFailCallbacks) {
        this.mediator = mediator;
        if (null != interceptors) {
            this.interceptors = interceptors;
        }
        if (null != processExecuteFailCallbacks) {
            this.processExecuteFailCallbacks = processExecuteFailCallbacks;
        }
    }

    public void setMediator(ActionMediator mediator) {
        this.mediator = mediator;
    }

    public ActionMediator getMediator() {
        return mediator;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // 整个流程的唯一id
        String processInstanceId = execution.getProcessInstanceId();
        // 当前任务的id
        String taskId = execution.getId();
        // 对应流程定义XML中,节点的:id
        String nodeId = execution.getCurrentActivityId();
        // 对应流程定义XML中,节点的:名称
        String nodeName = execution.getCurrentActivityName();

        ExtensionElements extensionElements = execution.getBpmnModelElementInstance().getExtensionElements();
        Map<String, String> nodePropertyMap = extractPluginInfo(extensionElements);
        boolean isPlugin = nodePropertyMap.containsKey(WorkflowConstants.PLUGIN);
        if (isPlugin) {
            String plugin = nodePropertyMap.get(WorkflowConstants.PLUGIN);
            boolean isPluginParams = nodePropertyMap.containsKey(WorkflowConstants.PLUGIN_PARAMS);
            String pluginParams = nodePropertyMap.get(WorkflowConstants.PLUGIN_PARAMS);
            // lookup plugin
            Action action = getMediator().getAction(plugin);

            Map<String, Object> variables = execution.getVariables();
            // 阶段级上下文
            PipelineContext stageCtx = new StagePipelineContext();
            if (null != variables) {
                stageCtx.getVars().putAll(variables);
            }

            if (isPluginParams) {
                stageCtx.setStageParams(pluginParams);
            }

            stageCtx.addVar(Constant.Common.ACTION_NAME, action.name());
            stageCtx.addVar(Constant.Pipeline.PIPELINE_INSTANCE_ID, processInstanceId);
            stageCtx.addVar(Constant.Pipeline.PIPELINE_NODE_TASK_ID, taskId);
            stageCtx.addVar(Constant.Pipeline.PIPELINE_NODE_ID, nodeId);
            stageCtx.addVar(Constant.Pipeline.PIPELINE_NODE_NAME, nodeName);
            try {

                // 按照升序执行
                List<IActionExecuteInterceptor> beforeInterceptors = interceptors.stream().sorted((i, j) -> {
                    return i.getOrder() - j.getOrder();
                }).collect(Collectors.toList());
                for (IActionExecuteInterceptor beforeInterceptor : beforeInterceptors) {
                    beforeInterceptor.beforeExecute(stageCtx);
                }

                Log.trace("开始执行插件:[%s]", action.name());
                action.execute(stageCtx);
                Log.trace("[%s]插件执行结束", action.name());

                // 按照降序执行
                List<IActionExecuteInterceptor> afterInterceptors = interceptors.stream().sorted((i, j) -> {
                    return j.getOrder() - i.getOrder();
                }).collect(Collectors.toList());
                for (IActionExecuteInterceptor afterInterceptor : afterInterceptors) {
                    afterInterceptor.afterExecute(stageCtx);
                }

                Map<String, Object> vars = stageCtx.getVars();
                vars.remove(Constant.Pipeline.PIPELINE_INSTANCE_ID);
                vars.remove(Constant.Pipeline.PIPELINE_NODE_TASK_ID);
                vars.remove(Constant.Pipeline.PIPELINE_NODE_ID);
                vars.remove(Constant.Pipeline.PIPELINE_NODE_NAME);
                vars.remove(Constant.Common.ACTION_NAME);

                // 清除上下文的数据
                if (null != variables) {
                    variables.forEach((key, value) -> {
                        vars.remove(key);
                    });
                }

                // 添加变量到流程引擎
                execution.setVariables(vars);
            } catch (Exception e) {
                String stackTrace = ExceptionUtils.getStackTrace(e);
                Map<String, Object> ctx = execution.getVariables();
                ctx.put(Constant.Common.ERROR_MSG, stackTrace);
                // 1. 日志进行记录
                Log.trace("运行插件:[%s]出现异常,异常详细信息如下:[%s]", action.name(), stackTrace);
                // 2. 抛给回调函数处理
                processExecuteFailCallbacks.forEach((callback) -> callback.callback(ctx));
                throw new RuntimeException(e);
            }
        }
    }

    public Map<String, String> extractPluginInfo(ExtensionElements extensionElements) {
        Map<String, String> nodePropertyMap = new HashMap<>();
        if (null != extensionElements) {
            List<CamundaProperty> camundaProperties = extensionElements.getChildElementsByType(CamundaProperty.class) //
                .stream().collect(Collectors.toList());
            if (!camundaProperties.isEmpty()) {
                Map<String, String> map = camundaProperties.stream() //
                    .collect(Collectors.toMap(CamundaProperty::getCamundaName, CamundaProperty::getCamundaValue));
                nodePropertyMap.putAll(map);
            }
        }
        return nodePropertyMap;
    }
}
