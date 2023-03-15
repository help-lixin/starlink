package help.lixin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import help.lixin.core.definition.PipelineDefinition;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public class YamlParseTest {
    @Test
    public void testYamlToPipelineDefinition() throws Exception {
        StringWriter writer = new StringWriter();
        IOUtils.copy(new FileInputStream(new File("/Users/lixin/GitRepository/spider-web-platform/admin/src/main/resources/pipeline.yml")), writer, StandardCharsets.UTF_8);
        String yaml = writer.toString();
        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        PipelineDefinition pipelineDefinition = yamlMapper.readValue(yaml, new TypeReference<PipelineDefinition>() {
        });
        Assert.assertNotNull(pipelineDefinition);
    }
}