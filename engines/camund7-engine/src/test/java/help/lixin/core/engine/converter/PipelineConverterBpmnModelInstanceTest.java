package help.lixin.core.engine.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.definition.PipelineDefinition;
import help.lixin.core.engine.config.Camunda7EngineConfig;
import help.lixin.core.engine.service.IPipelineConverterBpmnModelInstance;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.net.URL;

public class PipelineConverterBpmnModelInstanceTest {

    @Rule
    public TestName testName = new TestName();

    private static final String OUT_PATH = "target/out/";

    private PipelineDefinition pipelineDefinition;

    private IPipelineConverterBpmnModelInstance pipelineConverterBpmnModelInstance;

    @Before
    public void loadPipelineJsonFileAndInject() throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Camunda7EngineConfig.class);
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
