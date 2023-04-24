package help.lixin.docker.service.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageCmd;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.command.PushImageResultCallback;
import help.lixin.docker.action.DockerBuildArg;
import help.lixin.docker.service.IDockerImageApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DockerImageApiService implements IDockerImageApiService {

    private Logger logger = LoggerFactory.getLogger(DockerImageApiService.class);

    private DockerClient dockerClient;

    public DockerImageApiService(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    @Override
    public Set<String> buildImage(String dockerFile, List<DockerBuildArg> args, Set<String> tags) {
        Set<String> tagsResult = new HashSet<>();

        File dockerFileFile = new File(dockerFile);
        BuildImageCmd buildImageCmd = dockerClient.buildImageCmd(dockerFileFile);

        if (null != args) {
            for (DockerBuildArg arg : args) {
                buildImageCmd.withBuildArg(arg.getKey(), arg.getValue());
            }
        }

        if (null != tags && !tags.isEmpty()) {
            buildImageCmd.withTags(tags);
            //
            tagsResult.addAll(tags);
        }

        try {
            logger.info("START build image,for Dockerfile:[{}]", dockerFile);
            buildImageCmd.exec(new BuildImageResultCallback()).awaitCompletion();
            logger.info("END build image,for Dockerfile:[{}]", dockerFile);
        } catch (InterruptedException e) {
            logger.error("build cmd:[{}],to image error:[{}]", buildImageCmd, e);
        }
        return tagsResult;
    }

    @Override
    public void pushImage(String imageId) {
        List<Image> images = dockerClient.listImagesCmd().withReferenceFilter(imageId).exec();
        if (images.size() == 0) {
            String msg = String.format("image:[%s] not found", imageId);
            logger.error(msg);
            throw new RuntimeException(msg);
        } else {
            logger.info("START  Push image:[{}]", imageId);

            try {
                dockerClient.pushImageCmd(imageId).exec(new PushImageResultCallback()).awaitCompletion();
            } catch (InterruptedException e) {
                logger.error("Push image:[{}] error:[{}]", imageId, e);
                throw new RuntimeException(e);
            }
            logger.info("END Push image:[{}]", imageId);
        }
    }
}
