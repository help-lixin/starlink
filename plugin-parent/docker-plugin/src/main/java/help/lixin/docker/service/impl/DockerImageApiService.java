package help.lixin.docker.service.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageCmd;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.model.AuthConfig;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.command.PushImageResultCallback;
import help.lixin.docker.action.DockerBuildArg;
import help.lixin.docker.service.IDockerImageApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DockerImageApiService implements IDockerImageApiService {

    private Logger logger = LoggerFactory.getLogger(DockerImageApiService.class);

    private DefaultDockerClientConfig dockerClientConfig;

    private DockerClient dockerClient;

    public DockerImageApiService(DefaultDockerClientConfig dockerClientConfig, DockerClient dockerClient) {
        this.dockerClientConfig = dockerClientConfig;
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
            logger.info("根据Dockerfile:[{}],开始构建镜像.", dockerFile);
            buildImageCmd.exec(new BuildImageResultCallback()).awaitCompletion();
            logger.info("根据Dockerfile:[{}],构建镜像结束.", dockerFile);
        } catch (InterruptedException e) {
            String msg = String.format("根据Dockerfile:[%s],构建镜像失败,错误详细信息如下:[%s]", dockerFile, e.getMessage());
            logger.error(msg);
            throw new RuntimeException(msg);
        }
        return tagsResult;
    }

    @Override
    public void pushImage(String imageId, String userName, String password) {
        List<Image> images = dockerClient.listImagesCmd().withReferenceFilter(imageId).exec();
        if (images.size() == 0) {
            String msg = String.format("本地镜像:[%s]不存在", imageId);
            logger.error(msg);
            throw new RuntimeException(msg);
        } else {
            logger.info("准备推送镜像:[{}]", imageId);
            String tempUrl = String.format("http://%s", imageId);
            try {
                // 配置私有仓库的账号和密码
                if (null != userName && null != password) {
                    try {
                        URL url = new URL(tempUrl);
                        String host = url.getHost();
                        AuthConfig authConfig = new AuthConfig() //
                                .withRegistryAddress(host) //
                                .withUsername(userName) //
                                .withPassword(password);
                        dockerClientConfig.getDockerConfig().getAuths().put(host, authConfig);
                    } catch (MalformedURLException e) {
                        String msg = String.format("解析URL:[%s]出现异常,错误详细信息如下:[%s]", tempUrl, e.getMessage());
                        throw new RuntimeException(msg);
                    }
                }

                dockerClient.pushImageCmd(imageId).exec(new PushImageResultCallback()).awaitCompletion();
            } catch (InterruptedException e) {
                logger.error("推送镜像ID:[{}]出现错误,异常详细如下:[{}]", imageId, e.getMessage());
                throw new RuntimeException(e);
            }
            logger.info("结束镜像推送:[{}]", imageId);
        }
    }
}
