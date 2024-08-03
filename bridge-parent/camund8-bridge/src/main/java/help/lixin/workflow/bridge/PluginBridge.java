package help.lixin.workflow.bridge;

import java.util.Map;

import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.constants.Constant;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.core.pipeline.ctx.impl.StagePipelineContext;
import help.lixin.core.pipeline.mediator.ActionMediator;
import help.lixin.workflow.constant.WorkflowConstants;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;

public class PluginBridge implements JobHandler {

    private ActionMediator mediator;

    public PluginBridge(ActionMediator mediator) {
        this.mediator = mediator;
    }

    public void setMediator(ActionMediator mediator) {
        this.mediator = mediator;
    }

    public ActionMediator getMediator() {
        return mediator;
    }

    @Override
    public void handle(JobClient client, ActivatedJob job) throws Exception {
        long processDefinitionId = job.getProcessDefinitionKey();
        int processDefinitionVersion = job.getProcessDefinitionVersion();
        long processInstanceId = job.getProcessInstanceKey();
        long taskId = job.getElementInstanceKey();

        Map<String, String> customHeaders = job.getCustomHeaders();
        if (null != customHeaders) {
            boolean isPlugin = customHeaders.containsKey(WorkflowConstants.PLUGIN);
            if (isPlugin) {
                String plugin = customHeaders.get(WorkflowConstants.PLUGIN);
                boolean isPluginParams = customHeaders.containsKey(WorkflowConstants.PLUGIN_PARAMS);
                String pluginParams = customHeaders.get(WorkflowConstants.PLUGIN_PARAMS);
                // lookup plugin
                Action action = getMediator().getAction(plugin);
                // 取得整个流程上的Global变量
                Map<String, Object> variables = job.getVariablesAsMap();

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
                stageCtx.addVar(Constant.Pipeline.PIPELINE_NODE_TASK_ID, nodeId);

                try {
                    action.before(stageCtx);
                    action.execute(stageCtx);
                    action.after(stageCtx);

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

                    // 完成任务
                    client.newCompleteCommand(job.getKey()) //
                        .variables(vars) //
                        .send();
                } catch (Exception e) {
                    client.newFailCommand(job.getKey());
                }
            }
        }
    }
}
