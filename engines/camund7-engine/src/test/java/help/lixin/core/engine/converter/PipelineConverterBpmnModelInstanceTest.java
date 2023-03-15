package help.lixin.core.engine.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.definition.PipelineDefinition;
import help.lixin.core.engine.CamundaSevenEngineConfig;
import help.lixin.core.engine.service.IPipelineConverterBpmnModelInstance;
import org.apache.commons.io.FileUtils;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PipelineConverterBpmnModelInstanceTest {

    @Rule
    public TestName testName = new TestName();

    private static final String OUT_PATH = "target/out/";

    private PipelineDefinition pipelineDefinition;

    private IPipelineConverterBpmnModelInstance pipelineConverterBpmnModelInstance;

    @Before
    public void loadPipelineJsonFileAndInject() throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CamundaSevenEngineConfig.class);
        pipelineConverterBpmnModelInstance = applicationContext.getBean(IPipelineConverterBpmnModelInstance.class);


        URL resource = PipelineConverterBpmnModelInstanceTest.class.getClassLoader().getResource("pipline.json");
        String file = resource.getFile();
        String json = FileUtils.readFileToString(new File(file), "UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        pipelineDefinition = mapper.readValue(json, new TypeReference<PipelineDefinition>() {
        });
        Assert.assertNotNull(pipelineDefinition);
    }

    @Test
    public void testPipelineForJson() throws Exception {
        // BpmnModelInstance bpmnModelInstance = pipelineConverterBpmnModelInstance.toBpmnModelInstance(pipelineDefinition);
//        Path path = Paths.get(OUT_PATH + testName.getMethodName() + ".bpmn");
//        if (path.toFile().exists()) {
//            path.toFile().delete();
//        }
//        Files.createDirectories(path.getParent());
//        Bpmn.writeModelToFile(Files.createFile(path).toFile(), bpmnModelInstance);
    }
}
