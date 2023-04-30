package help.lixin.mvn.compile.service;

import help.lixin.core.pipeline.service.IExpressionService;

public class DockerMvnFaceService {

    private IExpressionService expressionService;

    private IContainerService containerService;


    public IContainerService getContainerService() {
        return containerService;
    }

    public void setContainerService(IContainerService containerService) {
        this.containerService = containerService;
    }

    public IExpressionService getExpressionService() {
        return expressionService;
    }

    public void setExpressionService(IExpressionService expressionService) {
        this.expressionService = expressionService;
    }
}
