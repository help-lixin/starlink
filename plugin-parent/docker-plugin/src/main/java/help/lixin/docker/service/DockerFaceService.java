package help.lixin.docker.service;

import help.lixin.core.pipeline.service.IExpressionService;
import help.lixin.docker.properties.DockerProperties;

public class DockerFaceService {
    private DockerProperties dockerProperties;
    private IDockerImageApiService dockerImageApiService;
    private IExpressionService expressionService;

    public DockerFaceService(DockerProperties dockerProperties,//
                             IDockerImageApiService dockerImageApiService, //
                             IExpressionService expressionService) {
        this.dockerProperties = dockerProperties;
        this.dockerImageApiService = dockerImageApiService;
        this.expressionService = expressionService;
    }

    public IDockerImageApiService getDockerImageApiService() {
        return dockerImageApiService;
    }

    public DockerProperties getDockerProperties() {
        return dockerProperties;
    }

    public IExpressionService getExpressionService() {
        return expressionService;
    }
}
