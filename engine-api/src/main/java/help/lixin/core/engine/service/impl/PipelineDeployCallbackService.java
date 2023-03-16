package help.lixin.core.engine.service.impl;

import help.lixin.core.engine.model.PipelineDeploy;
import help.lixin.core.engine.service.IPipelineDeployCallbackService;

public class PipelineDeployCallbackService implements IPipelineDeployCallbackService {

    @Override
    public void callback(PipelineDeploy deploy) {
        System.out.println(deploy);
    }
}
