package help.lixin.core.engine.service;

import help.lixin.core.engine.model.PipelineInstance;

import java.util.HashMap;
import java.util.Map;

/**
 * Pipeline 实例管理(启动实例)
 */
public interface IPipelineRuntimeService {

    default PipelineInstance startPipelineInstanceById(String pipelineDeployId) throws Exception {
        return startPipelineInstanceById(pipelineDeployId, null);
    }

    default PipelineInstance startPipelineInstanceById(String pipelineDeployId, String businessKey) throws Exception {
        return startPipelineInstanceById(pipelineDeployId, businessKey, new HashMap<>());
    }

    PipelineInstance startPipelineInstanceById(String pipelineDeployId,
                                               //
                                               String businessKey,
                                               //
                                               Map<String, Object> vars) throws Exception ;


    default PipelineInstance startPipelineInstanceByKey(String pipelineDeployKey) throws Exception {
        return startPipelineInstanceByKey(pipelineDeployKey, null);
    }

    default PipelineInstance startPipelineInstanceByKey(String pipelineDeployKey,
                                                        //
                                                        String businessKey) throws Exception {
        return startPipelineInstanceByKey(pipelineDeployKey, businessKey, null);
    }


    default PipelineInstance startPipelineInstanceByKey(String pipelineDeployKey,
                                                        //
                                                        String businessKey,
                                                        //
                                                        Integer version) throws Exception {
        return startPipelineInstanceByKey(pipelineDeployKey, businessKey, version, new HashMap<>());
    }

    PipelineInstance startPipelineInstanceByKey(String pipelineDeployKey,
                                                //
                                                String businessKey,
                                                //
                                                Integer version,
                                                //
                                                Map<String, Object> vars) throws Exception ;
}
