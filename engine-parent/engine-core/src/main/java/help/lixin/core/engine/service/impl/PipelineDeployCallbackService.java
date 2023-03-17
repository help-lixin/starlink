package help.lixin.core.engine.service.impl;

import help.lixin.core.definition.PipelineDefinition;
import help.lixin.core.engine.model.PipelineDeploy;
import help.lixin.core.engine.service.IPipelineDeployCallbackService;

public class PipelineDeployCallbackService implements IPipelineDeployCallbackService {

    @Override
    public void callback(PipelineDefinition pipeline, PipelineDeploy deployResult) {
        System.out.println(deployResult);
    }
}
