package help.lixin.admin.service;

import help.lixin.core.definition.PipelineDefinition;

public interface IPipelineParseStrategy {
    PipelineDefinition parse(String body) throws Exception;

    String supportType();
}
