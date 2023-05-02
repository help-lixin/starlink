package help.lixin.core.simple.engine.service.impl;

import help.lixin.core.definition.PipelineDefinition;
import help.lixin.core.engine.model.PipelineDeploy;
import help.lixin.core.engine.model.PipelineInstance;
import help.lixin.core.engine.service.IPipelineDeployService;
import help.lixin.core.engine.service.IPipelineRuntimeService;
import help.lixin.core.simple.engine.PipelineEngine;

import java.util.Map;

public class PipelineRuntimeService implements IPipelineRuntimeService {

    private PipelineEngine pipelineEngine;

    private IPipelineDeployService pipelineDeployService;

    public PipelineRuntimeService(IPipelineDeployService pipelineDeployService,
                                  //
                                  PipelineEngine pipelineEngine) {
        this.pipelineDeployService = pipelineDeployService;
        this.pipelineEngine = pipelineEngine;
    }

    @Override
    public PipelineInstance startPipelineInstanceById(String pipelineDeployId, String businessKey, Map<String, Object> vars) throws Exception {
        // 1. 要据pipelineDeployId,反查询出流程定义信息.
        // 2. 创建流水线"实例信息"
        // 3. 委托给:PipelineEngine去执行.
        PipelineDeploy pipelineDeploy = pipelineDeployService.get(pipelineDeployId);
        PipelineDefinition pipelineDefinition = pipelineDeploy.getPipelineDefinition();
        pipelineEngine.execute(pipelineDefinition);
        return null;
    }

    @Override
    @Deprecated
    public PipelineInstance startPipelineInstanceByKey(String pipelineDeployKey, String businessKey, Integer version, Map<String, Object> vars) throws Exception {
        // TODO lixin
        return null;
    }
}
