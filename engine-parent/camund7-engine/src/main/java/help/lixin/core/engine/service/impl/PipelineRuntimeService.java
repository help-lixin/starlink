package help.lixin.core.engine.service.impl;

import help.lixin.core.engine.model.PipelineInstance;
import help.lixin.core.engine.service.IPipelineRuntimeService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;

import java.util.HashMap;
import java.util.Map;

public class PipelineRuntimeService implements IPipelineRuntimeService {

    private RuntimeService runtimeService;

    public PipelineRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }


    @Override
    public PipelineInstance startPipelineInstanceById(String pipelineDeployId,
                                                      //
                                                      String businessKey,
                                                      //
                                                      Map<String, Object> vars) {
        if (null == vars) {
            vars = new HashMap<>();
        }
        // query by id
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(pipelineDeployId, businessKey, vars);
        return toPipelineInstance(processInstance);
    }


    @Override
    public PipelineInstance startPipelineInstanceByKey(String pipelineDeployKey,
                                                       //
                                                       String businessKey,
                                                       //
                                                       Integer version,
                                                       //
                                                       Map<String, Object> vars) {
        // query by key and version
        // TODO lixin
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(pipelineDeployKey, businessKey, vars);
        return toPipelineInstance(processInstance);
    }

    public PipelineInstance toPipelineInstance(ProcessInstance processInstance) {
        if (null != processInstance) {
            PipelineInstance instance = new PipelineInstance();
            instance.setRootPipelineInstanceId(processInstance.getRootProcessInstanceId());
            instance.setPipelineDeployId(processInstance.getProcessDefinitionId());
            instance.setBusinessKey(processInstance.getBusinessKey());
            instance.setId(processInstance.getId());
            return instance;
        }
        return null;
    }
}
