package help.lixin.docker.service;

import help.lixin.docker.properties.DockerProperties;

public class DockerFaceService {
    private DockerProperties dockerProperties;
    private IDockerImageApiService dockerImageApiService;


    public DockerFaceService(DockerProperties dockerProperties, IDockerImageApiService dockerImageApiService) {
        this.dockerProperties = dockerProperties;
        this.dockerImageApiService = dockerImageApiService;
    }

    public IDockerImageApiService getDockerImageApiService() {
        return dockerImageApiService;
    }
}
