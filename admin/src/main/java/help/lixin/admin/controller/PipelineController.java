package help.lixin.admin.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import help.lixin.admin.vo.Response;
import help.lixin.core.definition.PipelineDefinition;
import help.lixin.core.engine.service.impl.PipelineDeployTemplate;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

@RequestMapping("/pipline")
@RestController
public class PipelineController {

    private Logger logger = LoggerFactory.getLogger(PipelineController.class);

    @Autowired
    private PipelineDeployTemplate pipelineDeployTemplate;

    @PostMapping
    public Response applyJson(@RequestParam("content") MultipartFile content) {
        try {
            if (null == content || content.isEmpty()) {
                return new Response(500, "要提交的pipeline内容不能为空.");
            }
            StringWriter output = new StringWriter();
            IOUtils.copy(content.getInputStream(), output, StandardCharsets.UTF_8);
            String body = output.toString();
            PipelineDefinition pipelineDefinition = null;
            if (body.startsWith("{")) {
                pipelineDefinition = readPipelineDefinitionForJson(body);
            } else {
                pipelineDefinition = readPipelineDefinitionForYaml(body);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("apply pipeline:\n{}\n", body);
            }
            pipelineDeployTemplate.deploy(pipelineDefinition);
        } catch (Exception e) {
            return new Response(500, e.getMessage());
        }
        return new Response(200, "SUCCESS");
    }


    private PipelineDefinition readPipelineDefinitionForJson(String json) throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        PipelineDefinition pipelineDefinition = jsonMapper.readValue(json, new TypeReference<PipelineDefinition>() {
        });
        return pipelineDefinition;
    }


    private PipelineDefinition readPipelineDefinitionForYaml(String yaml) throws Exception {
        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        PipelineDefinition pipelineDefinition = yamlMapper.readValue(yaml, new TypeReference<PipelineDefinition>() {
        });
        return pipelineDefinition;
    }
}
