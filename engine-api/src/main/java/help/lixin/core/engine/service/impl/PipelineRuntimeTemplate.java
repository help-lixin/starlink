package help.lixin.core.engine.service.impl;

import help.lixin.core.engine.model.PipelineInstance;
import help.lixin.core.engine.service.IPipelineRuntimeService;
import help.lixin.core.engine.service.IStartPipelineCallbackService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PipelineRuntimeTemplate {

    private IPipelineRuntimeService pipelineRuntimeService;
    private IStartPipelineCallbackService startPipelineCallbackService;

    public PipelineRuntimeTemplate(IPipelineRuntimeService pipelineRuntimeService,
                                   //
                                   IStartPipelineCallbackService startPipelineCallbackService) {
        this.pipelineRuntimeService = pipelineRuntimeService;
        this.startPipelineCallbackService = startPipelineCallbackService;
    }


    public PipelineInstance startPipelineInstanceById(String pipelineDeployId,
                                                      //
                                                      String businessKey,
                                                      //
                                                      Map<String, Object> vars) {
        PipelineInstance pipelineInstance = pipelineRuntimeService.startPipelineInstanceById(pipelineDeployId, businessKey, vars);
        callback(pipelineInstance);
        return pipelineInstance;
    }

    public PipelineInstance startPipelineInstanceByKey(String pipelineDeployKey,
                                                       //
                                                       Integer version,
                                                       //
                                                       String businessKey,
                                                       //
                                                       Map<String, Object> vars) {
        PipelineInstance pipelineInstance = pipelineRuntimeService.startPipelineInstanceByKey(pipelineDeployKey, businessKey, version, vars);
        callback(pipelineInstance);
        return pipelineInstance;
    }

    protected void callback(PipelineInstance pipelineInstance) {
        if (null != pipelineInstance) {
            startPipelineCallbackService.callback(pipelineInstance);
        }
    }
}
