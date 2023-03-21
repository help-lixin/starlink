package help.lixin.core.engine.service.impl;

import help.lixin.core.engine.model.PipelineInstance;
import help.lixin.core.engine.service.IStartPipelineCallbackService;

public class StartPipelineCallbackService implements IStartPipelineCallbackService {

    @Override
    public void callback(PipelineInstance instance) {
        // TODO lixin
        System.out.println(instance);
    }
}
