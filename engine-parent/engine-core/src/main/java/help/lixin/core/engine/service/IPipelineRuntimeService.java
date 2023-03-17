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

    PipelineInstance startPipelineInstanceById(String pipelineDeployId,
                                               //
                                               String businessKey,
                                               //
                                               Map<String, Object> vars);


    default PipelineInstance startPipelineInstanceByKey(String pipelineDeployKey) {
        return startPipelineInstanceByKey(pipelineDeployKey, null);
    }

    default PipelineInstance startPipelineInstanceByKey(String pipelineDeployKey,
                                                        //
                                                        String businessKey) {
        return startPipelineInstanceByKey(pipelineDeployKey, businessKey, null);
    }


    default PipelineInstance startPipelineInstanceByKey(String pipelineDeployKey,
                                                        //
                                                        String businessKey,
                                                        //
                                                        Integer version) {
        return startPipelineInstanceByKey(pipelineDeployKey, businessKey, version, new HashMap<>());
    }

    PipelineInstance startPipelineInstanceByKey(String pipelineDeployKey,
                                                //
                                                String businessKey,
                                                //
                                                Integer version,
                                                //
                                                Map<String, Object> vars);
}
