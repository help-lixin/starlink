package help.lixin.core.simple.engine.service.impl;

import help.lixin.core.definition.PipelineDefinition;
import help.lixin.core.engine.model.PipelineDeploy;
import help.lixin.core.engine.service.IPipelineDeployService;

public class PipelineDeployService implements IPipelineDeployService {

    @Override
    public PipelineDeploy deploy(PipelineDefinition pipeline) {
        // 1. 把pipeline转换成json对象,进行md5
        // 2. 根据: pipeline.getKey() 在DB里验证:md5是否存在,如果存在,则不进行pipeline的更新,如果不存在,则,新增记录(或者版本增加)

        // TODO lixin
        return null;
    }
}
