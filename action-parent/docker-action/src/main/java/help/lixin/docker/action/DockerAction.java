package help.lixin.docker.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.docker.service.DockerFaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class DockerAction implements Action {
    private Logger logger = LoggerFactory.getLogger(DockerAction.class);

    private DockerFaceService dockerFaceService;

    public DockerAction(DockerFaceService dockerFaceService) {
        this.dockerFaceService = dockerFaceService;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        String stageParams = ctx.getStageParams();
        ObjectMapper mapper = new ObjectMapper();
        DockerParams actionParams = mapper.readValue(stageParams, DockerParams.class);

        Set<String> images = dockerFaceService.getDockerImageApiService().buildImage(
                //
                actionParams.getDockerFile()
                //
                , actionParams.getArgs()
                //
                , actionParams.getTags());

        if (!images.isEmpty()) {
            for (String imageName : images) {
                dockerFaceService.getDockerImageApiService().pushImage(imageName);
            }
        }
        return true;
    }
}
