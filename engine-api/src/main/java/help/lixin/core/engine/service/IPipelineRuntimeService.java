package help.lixin.core.engine.service;

import help.lixin.core.engine.model.PipelineInstance;

import java.util.HashMap;
import java.util.Map;

/**
 * Pipeline 实例管理(启动实例)
 */
public interface IPipelineRuntimeService {

    default PipelineInstance startPipelineInstanceById(String pipelineDeployId) {
        return startPipelineInstanceById(pipelineDeployId, null);
    }

    default PipelineInstance startPipelineInstanceById(String pipelineDeployId, String businessKey) {
        return startPipelineInstanceById(pipelineDeployId, businessKey, new HashMap<>());
    }

    default PipelineInstance startPipelineInstanceById(String pipelineDeployId,
                                                       //
                                                       String businessKey,
                                                       //
                                                       Map<String, Object> globalVars) {
        return startPipelineInstanceById(pipelineDeployId, businessKey, globalVars, new HashMap<>());
    }

    PipelineInstance startPipelineInstanceById(String pipelineDeployId,
                                               //
                                               String businessKey,
                                               //
                                               Map<String, Object> globalVars,
                                               //
                                               Map<String, Object> localVars);


    default PipelineInstance startPipelineInstanceByKey(String pipelineDeployKey) {
        return startPipelineInstanceByKey(pipelineDeployKey, null);
    }

    default PipelineInstance startPipelineInstanceByKey(String pipelineDeployKey,
                                                        //
                                                        String businessKey) {
        return startPipelineInstanceById(pipelineDeployKey, businessKey, new HashMap<>());
    }

    default PipelineInstance startPipelineInstanceByKey(String pipelineDeployKey,
                                                        //
                                                        String businessKey,
                                                        //
                                                        Map<String, Object> globalVars) {
        return startPipelineInstanceByKey(pipelineDeployKey, businessKey, globalVars, new HashMap<>());
    }

    PipelineInstance startPipelineInstanceByKey(String pipelineDeployKey,
                                                //
                                                String businessKey,
                                                //
                                                Map<String, Object> globalVars,
                                                //
                                                Map<String, Object> localVars);
}
