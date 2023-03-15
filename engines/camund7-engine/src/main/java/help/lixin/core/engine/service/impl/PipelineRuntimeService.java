package help.lixin.core.engine.service.impl;

import help.lixin.core.engine.model.PipelineInstance;
import help.lixin.core.engine.service.IPipelineRuntimeService;

import java.util.Map;

public class PipelineRuntimeService implements IPipelineRuntimeService {


    @Override
    public PipelineInstance startPipelineInstanceById(String pipelineDeployId,
                                                      //
                                                      String businessKey,
                                                      //
                                                      Map<String, Object> globalVars,
                                                      //
                                                      Map<String, Object> localVars) {
        return null;
    }


    @Override
    public PipelineInstance startPipelineInstanceByKey(String pipelineDeployKey,
                                                       //
                                                       String businessKey,
                                                       //
                                                       Map<String, Object> globalVars,
                                                       //
                                                       Map<String, Object> localVars) {
        return null;
    }

}
