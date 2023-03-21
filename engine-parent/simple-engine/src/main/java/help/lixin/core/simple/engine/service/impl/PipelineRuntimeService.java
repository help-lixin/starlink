package help.lixin.core.simple.engine.service.impl;

import help.lixin.core.engine.model.PipelineInstance;
import help.lixin.core.engine.service.IPipelineRuntimeService;
import help.lixin.core.simple.engine.PipelineEngine;

import java.util.Map;

public class PipelineRuntimeService implements IPipelineRuntimeService {

    private PipelineEngine pipelineEngine;

    public PipelineRuntimeService(PipelineEngine pipelineEngine) {
        this.pipelineEngine = pipelineEngine;
    }

    @Override
    public PipelineInstance startPipelineInstanceById(String pipelineDeployId, String businessKey, Map<String, Object> vars) {
        // 1. 要据pipelineDeployId,反查询出流程定义信息.
        // 2. 创建流水线"实例信息"
        // 3. 委托给:PipelineEngine去执行.

        // TODO lixin
        return null;
    }

    @Override
    public PipelineInstance startPipelineInstanceByKey(String pipelineDeployKey, String businessKey, Integer version, Map<String, Object> vars) {
        // TODO lixin
        return null;
    }
}
