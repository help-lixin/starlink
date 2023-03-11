package help.lixin.core.definition.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.definition.ElementDefinition;
import help.lixin.core.definition.PipelineDefinition;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

public class PipelineDefinitionParseTest {

    @Test
    public void testSerial() throws Exception {
        ElementDefinition gitlab = new PluginDefinition();
        gitlab.setId("1");
        gitlab.setName("git clone源码");
        gitlab.setTarget("2");
        gitlab.setParams("{ 'branch' :'main', 'url': 'ssh://git@103.215.125.86:2222/order-group/spring-web-demo.git' }");

        ElementDefinition sequenceFlowDefinition = new SequenceFlowDefinition();
        sequenceFlowDefinition.setId("2");
        sequenceFlowDefinition.setSource("1");
        sequenceFlowDefinition.setTarget("3");

        ElementDefinition jenkins = new PluginDefinition();
        jenkins.setId("3");
        jenkins.setSource("2");
        jenkins.setName("git clone源码");
        jenkins.setParams("{  'credentialId':'zhangsan','compile':'maven','cmd':'mvn clean install -DskipTests -X','archiveArtifacts':'target/*.jar' }");

        PipelineDefinition pipelineDefinition = new PipelineDefinition();
        pipelineDefinition.setKey("pipline-test");
        pipelineDefinition.setName("测试");
        pipelineDefinition.add(gitlab);
        pipelineDefinition.add(sequenceFlowDefinition);
        pipelineDefinition.add(jenkins);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(pipelineDefinition);
        Assert.assertNotNull(json);
    }

    @Test
    public void testParse() throws Exception {
        URL resource = PipelineDefinitionParseTest.class.getClassLoader().getResource("pipline.json");
        String file = resource.getFile();
        String json = FileUtils.readFileToString(new File(file), "UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        PipelineDefinition pipelineDefinition = mapper.readValue(json, new TypeReference<PipelineDefinition>() {
        });
        Assert.assertNotNull(pipelineDefinition);
        Assert.assertEquals(9, pipelineDefinition.getPipelines());
    }
}
