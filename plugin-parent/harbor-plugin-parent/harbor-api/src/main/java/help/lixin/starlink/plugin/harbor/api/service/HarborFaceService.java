package help.lixin.starlink.plugin.harbor.api.service;

import help.lixin.starlink.plugin.harbor.api.properties.HarborProperties;

public class HarborFaceService {
    private IHarborProjectApi projectService;
    private HarborProperties harborProperties;

    public HarborFaceService(HarborProperties harborProperties, IHarborProjectApi projectService) {
        this.harborProperties = harborProperties;
        this.projectService = projectService;
    }

    public HarborProperties getHarborProperties() {
        return harborProperties;
    }

    public IHarborProjectApi getProjectService() {
        return projectService;
    }

}
