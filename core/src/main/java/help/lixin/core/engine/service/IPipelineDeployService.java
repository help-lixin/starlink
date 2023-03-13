package help.lixin.core.engine.service;

import help.lixin.core.definition.PipelineDefinition;
import help.lixin.core.engine.model.PipelineDeploy;

/**
 * Pipeline部署
 */
public interface IPipelineDeployService {
    /**
     * 部署流水线
     *
     * @param pipeline
     */
    PipelineDeploy deploy(PipelineDefinition pipeline);
}
