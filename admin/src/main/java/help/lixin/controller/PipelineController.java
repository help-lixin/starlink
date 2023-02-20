package help.lixin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.definition.ElementDefinition;
import help.lixin.core.definition.PipelineDefinition;
import help.lixin.core.pipeline.Pipeline;
import help.lixin.vo.Response;
import help.lixin.vo.SubmitPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/pipline")
@RestController
public class PipelineController {

    @Autowired
    private Pipeline pipeline;

    @PostMapping
    public Response submitPipeline(@RequestBody SubmitPipeline submitPipeline) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ElementDefinition> elements = null;
        try {
            elements = objectMapper.readValue(submitPipeline.getContnet(), new TypeReference<List<ElementDefinition>>() {
            });
        } catch (JsonProcessingException e) {
            return new Response(500, "parse DSL error" + e.getMessage());
        }

        PipelineDefinition pipelineDefinition = new PipelineDefinition();
        pipelineDefinition.setName(submitPipeline.getName());
        pipelineDefinition.setPipelines(elements);
        try {
            pipeline.execute(pipelineDefinition);
        } catch (Exception e) {
            return new Response(500, e.getMessage());
        }
        return new Response(200, "SUCCESS");
    }
}
