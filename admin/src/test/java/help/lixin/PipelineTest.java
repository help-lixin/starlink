package help.lixin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.action.gitlab.GitlabAction;
import help.lixin.core.definition.ElementDefinition;
import help.lixin.core.definition.PipelineDefinition;
import help.lixin.core.pipeline.Pipeline;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.action.impl.SequenceFlowAction;
import help.lixin.core.pipeline.mediator.ActionMediator;
import help.lixin.core.pipeline.mediator.impl.DefaultActionMediator;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.net.URL;
import java.util.List;

public class PipelineTest {

    private String pipelineDSL = "pipline.json";

    private ActionMediator actionMediator;

    private Pipeline pipeline;

    private PipelineDefinition pipelineDefinition;

    @Before
    public void init() throws Exception {
        // 1.
        actionMediator = new DefaultActionMediator();
        Action flowAction = new SequenceFlowAction();
        Action gitlab = new GitlabAction();
        actionMediator.register(flowAction.name(), flowAction);
        actionMediator.register(gitlab.name(), gitlab);
        this.actionMediator = actionMediator;

        // 2.
        pipeline = new Pipeline(actionMediator);

        // 3.
        URL resource = PipelineTest.class.getClassLoader().getResource(pipelineDSL);
        String pipelineDSLPath = resource.getFile();
        String jsonDSL = FileUtils.readFileToString(new File(pipelineDSLPath), "UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        List<ElementDefinition> elements = mapper.readValue(jsonDSL, new TypeReference<List<ElementDefinition>>() {
        });
        Assertions.assertNotNull(elements);
        pipelineDefinition = new PipelineDefinition();
        pipelineDefinition.setName("test hello");
        pipelineDefinition.setPipelines(elements);
    }

    @Test
    public void testPipeline() throws Exception {
        pipeline.execute(pipelineDefinition);
        System.out.println();
    }
}
