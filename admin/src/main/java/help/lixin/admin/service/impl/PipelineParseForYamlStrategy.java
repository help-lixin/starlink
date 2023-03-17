package help.lixin.admin.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import help.lixin.admin.service.IPipelineParseStrategy;
import help.lixin.core.definition.PipelineDefinition;

public class PipelineParseForYamlStrategy implements IPipelineParseStrategy {

    public static final String SUPPORT_TYPE = "yaml";

    @Override
    public PipelineDefinition parse(String yaml) throws Exception {
        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        PipelineDefinition pipelineDefinition = yamlMapper.readValue(yaml, new TypeReference<PipelineDefinition>() {
        });
        return pipelineDefinition;
    }

    @Override
    public String supportType() {
        return SUPPORT_TYPE;
    }
}
