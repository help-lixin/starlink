package help.lixin.workflow.bridge;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.flowable.bpmn.model.ExtensionElement;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.constants.Constant;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.core.pipeline.ctx.impl.StagePipelineContext;
import help.lixin.core.pipeline.interceptor.IActionExecuteInterceptor;
import help.lixin.core.pipeline.mediator.ActionMediator;
import help.lixin.core.log.Log;
import help.lixin.workflow.constant.WorkflowConstants;
import help.lixin.workflow.service.IProcessExecuteFailCallback;

public class Flowable6PluginBridge implements JavaDelegate {
    private ActionMediator mediator;
    private List<IActionExecuteInterceptor> interceptors = new ArrayList<>(0);
    private List<IProcessExecuteFailCallback> processExecuteFailCallbacks = new ArrayList<>(0);

    public Flowable6PluginBridge(ActionMediator mediator, //
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

    public void setInterceptors(List<IActionExecuteInterceptor> interceptors) {
        if (null != interceptors) {
            this.interceptors = interceptors;
        }
    }

    public List<IActionExecuteInterceptor> getInterceptors() {
        return interceptors;
    }

    @Override
    public void execute(DelegateExecution execution) {
        // 整个流程的唯一id
        String processInstanceId = execution.getProcessInstanceId();
        // 当前任务的id
        String taskId = execution.getId();
        // 对应流程定义XML中,节点的:id
        String nodeId = execution.getCurrentActivityId();
        // 对应流程定义XML中,节点的:名称
        String nodeName = nodeId;

        FlowElement currentFlowElement = execution.getCurrentFlowElement();
        Map<String, List<ExtensionElement>> extensionElements = currentFlowElement.getExtensionElements();
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

        // String taskId = execution.getId();
        // String processInstanceId = execution.getProcessInstanceId();
        // String nodeId = execution.getCurrentActivityId();
        //
        // FlowElement currentFlowElement = execution.getCurrentFlowElement();
        // Map<String, List<ExtensionElement>> extensionElements = currentFlowElement.getExtensionElements();
        // Map<String, String> nodePropertyMap = extractPluginInfo(extensionElements);
        // boolean isPlugin = nodePropertyMap.containsKey(WorkflowConstants.PLUGIN);
        // if (isPlugin) {
        // String plugin = nodePropertyMap.get(WorkflowConstants.PLUGIN);
        // boolean isPluginParams = nodePropertyMap.containsKey(WorkflowConstants.PLUGIN_PARAMS);
        // String pluginParams = nodePropertyMap.get(WorkflowConstants.PLUGIN_PARAMS);
        // // lookup plugin
        // Action action = getMediator().getAction(plugin);
        //
        // Map<String, Object> variables = execution.getVariables();
        // // 阶段级上下文
        // PipelineContext stageCtx = new StagePipelineContext();
        // if (null != variables) {
        // stageCtx.getVars().putAll(variables);
        // }
        //
        // if (isPluginParams) {
        // stageCtx.setStageParams(pluginParams);
        // }
        //
        // stageCtx.addVar(Constant.Common.ACTION_NAME, action.name());
        // stageCtx.addVar(Constant.Pipeline.PIPELINE_INSTANCE_ID, processInstanceId);
        // stageCtx.addVar(Constant.Pipeline.PIPELINE_NODE_TASK_ID, taskId);
        // stageCtx.addVar(Constant.Pipeline.PIPELINE_NODE_ID, nodeId);
        // stageCtx.addVar(Constant.Pipeline.PIPELINE_NODE_TASK_ID, nodeId);
        //
        //
        // try {
        // // 按照升序执行
        // List<IActionExecuteInterceptor> beforeInterceptors = interceptors.stream().sorted((i, j) -> {
        // return i.getOrder() - j.getOrder();
        // }).collect(Collectors.toList());
        // for (IActionExecuteInterceptor beforeInterceptor : beforeInterceptors) {
        // beforeInterceptor.beforeExecute(stageCtx);
        // }
        //
        //
        // action.execute(stageCtx);
        //
        // // 按照降序执行
        // List<IActionExecuteInterceptor> afterInterceptors = interceptors.stream().sorted((i, j) -> {
        // return j.getOrder() - i.getOrder();
        // }).collect(Collectors.toList());
        // for (IActionExecuteInterceptor afterInterceptor : afterInterceptors) {
        // afterInterceptor.afterExecute(stageCtx);
        // }
        //
        // Map<String, Object> vars = stageCtx.getVars();
        // vars.remove(Constant.Pipeline.PIPELINE_INSTANCE_ID);
        // vars.remove(Constant.Pipeline.PIPELINE_NODE_TASK_ID);
        // vars.remove(Constant.Pipeline.PIPELINE_NODE_ID);
        // vars.remove(Constant.Pipeline.PIPELINE_NODE_NAME);
        // vars.remove(Constant.Common.ACTION_NAME);
        //
        // // 清除上下文的数据
        // if (null != variables) {
        // variables.forEach((key, value) -> {
        // vars.remove(key);
        // });
        // } // end if
        //
        // // 添加变量到流程引擎
        // execution.setVariables(vars);
        // } catch (Exception e) {
        // throw new RuntimeException(e);
        // }
        // }
    }

    /**
     * 提取插件信息
     */
    public Map<String, String> extractPluginInfo(Map<String, List<ExtensionElement>> extensionElements) {
        Map<String, String> pluginMap = new HashMap<>();
        if (null != extensionElements) {
            Iterator<Map.Entry<String, List<ExtensionElement>>> it = extensionElements.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, List<ExtensionElement>> next = it.next();
                Map<String, String> tmpMap = next.getValue() //
                    .stream() //
                    .collect(Collectors //
                        .toMap(ExtensionElement::getName, ExtensionElement::getElementText));
                if (tmpMap.containsKey(WorkflowConstants.PLUGIN)
                    || tmpMap.containsKey(WorkflowConstants.PLUGIN_PARAMS)) {
                    pluginMap.putAll(tmpMap);
                }
            }
        }
        return pluginMap;
    }
}