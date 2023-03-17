package help.lixin.admin.controller;

import help.lixin.admin.service.impl.PipelineParseFaceService;
import help.lixin.admin.vo.Response;
import help.lixin.admin.vo.StartPipelineByIdParam;
import help.lixin.admin.vo.StartPipelineByKeyParam;
import help.lixin.core.definition.PipelineDefinition;
import help.lixin.core.engine.model.PipelineDeploy;
import help.lixin.core.engine.model.PipelineInstance;
import help.lixin.core.engine.service.impl.PipelineDeployTemplate;
import help.lixin.core.engine.service.impl.PipelineRuntimeTemplate;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

@RequestMapping("/pipline")
@RestController
public class PipelineController {

    private Logger logger = LoggerFactory.getLogger(PipelineController.class);

    @Autowired
    private PipelineParseFaceService pipelineParseFaceService;

    @Autowired
    private PipelineDeployTemplate pipelineDeployTemplate;

    @Autowired
    private PipelineRuntimeTemplate pipelineRuntimeTemplate;

    @PostMapping
    public Response<PipelineDeploy> apply(@RequestParam("file") MultipartFile file) {
        try {
            if (null == file || file.isEmpty()) {
                return Response.newBuilder().code(500).msg("要提交的pipeline内容不能为空.").build();
            }
            StringWriter output = new StringWriter();
            IOUtils.copy(file.getInputStream(), output, StandardCharsets.UTF_8);
            String body = output.toString();

            // 委托给具体的实现去解析.
            PipelineDefinition pipelineDefinition = pipelineParseFaceService.parse(body);

            if (logger.isDebugEnabled()) {
                logger.debug("apply pipeline:\n{}\n", body);
            }
            PipelineDeploy deploy = pipelineDeployTemplate.deploy(pipelineDefinition);
            return Response.<PipelineDeploy>newBuilder().code(200).data(deploy).build();
        } catch (Exception e) {
            return Response.newBuilder().code(500).msg(e.getMessage()).build();
        }
    }

    @PostMapping("/startById")
    public Response<PipelineInstance> startById(@RequestBody StartPipelineByIdParam param) {
        PipelineInstance instance = pipelineRuntimeTemplate.startPipelineInstanceById(param.getDeployId(),
                //
                param.getBusinessKey(),
                //
                param.getVars());
        return Response.<PipelineInstance>newBuilder()
                //
                .code(200)
                //
                .data(instance)
                //
                .build();
    }

    @PostMapping("/startByKey")
    public Response<PipelineInstance> startByKey(@RequestBody StartPipelineByKeyParam param) {
        PipelineInstance instance = pipelineRuntimeTemplate.startPipelineInstanceByKey(param.getKey(),
                //
                param.getVersion(),
                //
                param.getBusinessKey(),
                //
                param.getVars());
        return Response.<PipelineInstance>newBuilder()
                //
                .code(200)
                //
                .data(instance)
                //
                .build();
    }
}
