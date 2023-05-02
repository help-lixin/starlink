package help.lixin.core.engine.service;

import help.lixin.core.definition.PipelineDefinition;
import help.lixin.core.engine.model.PipelineDeploy;

public interface IPipelineDeployService {
    PipelineDeploy deploy(PipelineDefinition pipeline);

    PipelineDeploy get(String deployId);
}
