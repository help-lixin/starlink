package help.lixin.harbor.service;


import help.lixin.harbor.properties.HarborProperties;

public class HarborFaceService {
    private IProjectService projectService;
    private HarborProperties harborProperties;

    public HarborFaceService(HarborProperties harborProperties, IProjectService projectService) {
        this.harborProperties = harborProperties;
        this.projectService = projectService;
    }

    public HarborProperties getHarborProperties() {
        return harborProperties;
    }

    public IProjectService getProjectService() {
        return projectService;
    }
}
