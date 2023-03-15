package help.lixin.core.engine.service;

import help.lixin.core.engine.model.PipelineDeploy;

/**
 * Pipeline部署回调
 */
public interface IPipelineDeployCallbackService {
    /**
     * 部署流水线回调
     */
    void callback(PipelineDeploy deploy);
}
