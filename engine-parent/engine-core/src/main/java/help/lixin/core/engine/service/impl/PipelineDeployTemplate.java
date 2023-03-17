package help.lixin.core.engine.service.impl;

import help.lixin.core.definition.PipelineDefinition;
import help.lixin.core.engine.model.PipelineDeploy;
import help.lixin.core.engine.service.IPipelineDeployCallbackService;
import help.lixin.core.engine.service.IPipelineDeployService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PipelineDeployTemplate {

    private IPipelineDeployService pipelineDeployService;

    private IPipelineDeployCallbackService pipelineDeployCallbackService;

    public PipelineDeployTemplate(IPipelineDeployService pipelineDeployService, IPipelineDeployCallbackService pipelineDeployCallbackService) {
        this.pipelineDeployService = pipelineDeployService;
        this.pipelineDeployCallbackService = pipelineDeployCallbackService;
    }

    public PipelineDeploy deploy(PipelineDefinition pipeline) {
        PipelineDeploy deploy = pipelineDeployService.deploy(pipeline);
        if (null != deploy) {
            pipelineDeployCallbackService.callback(pipeline, deploy);
        }
        // TODO
        // TODO lixin,需要创建相应的流程消费者
        return deploy;
    }
}
