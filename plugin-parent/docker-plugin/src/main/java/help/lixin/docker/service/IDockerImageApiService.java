package help.lixin.docker.service;

import help.lixin.docker.action.DockerBuildArg;

import java.util.List;
import java.util.Set;

public interface IDockerImageApiService {
    /**
     * 构建镜像
     *
     * @param dockerFile
     * @param args
     * @param tags
     */
    Set<String> buildImage(String dockerFile, List<DockerBuildArg> args, Set<String> tags);

    /**
     * 推送镜像
     *
     * @param imageId
     */
    void pushImage(String imageId, String userName, String password);
}
