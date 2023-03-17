package help.lixin.admin.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.admin.service.IPipelineParseStrategy;
import help.lixin.core.definition.PipelineDefinition;

public class PipelineParseForJsonStrategy implements IPipelineParseStrategy {

    public static final String SUPPORT_TYPE = "json";

    @Override
    public PipelineDefinition parse(String json) throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        PipelineDefinition pipelineDefinition = jsonMapper.readValue(json, new TypeReference<PipelineDefinition>() {
        });
        return pipelineDefinition;
    }

    @Override
    public String supportType() {
        return SUPPORT_TYPE;
    }
}
