package help.lixin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.definition.ElementDefinition;
import help.lixin.core.definition.PipelineDefinition;
import help.lixin.core.pipeline.Pipeline;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.net.URL;
import java.util.List;

@SpringBootApplication(scanBasePackages = "help.lixin")
public class Application {
    private static String PIPELINE_DSL = "pipline-jenkins.json";

    public static void main(String[] args) throws Exception {
        URL resource = Application.class.getClassLoader().getResource(PIPELINE_DSL);
        String pipelineDSLPath = resource.getFile();
        String jsonDSL = FileUtils.readFileToString(new File(pipelineDSLPath), "UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        List<ElementDefinition> elements = mapper.readValue(jsonDSL, new TypeReference<List<ElementDefinition>>() {
        });
        PipelineDefinition pipelineDefinition = new PipelineDefinition();
        pipelineDefinition.setName("test hello");
        pipelineDefinition.setPipelines(elements);

        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        Pipeline pipelineBean = ctx.getBean(Pipeline.class);
        pipelineBean.execute(pipelineDefinition);
        System.out.println();
    }
}
