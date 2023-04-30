package help.lixin.gitlab.service;

import help.lixin.core.pipeline.service.IExpressionService;

public class GitlabFaceService {

    private IGroupService groupService;

    private IProjectService projectService;

    private IRepositoryService repositoryService;

    private IUserService userService;

    private IExpressionService expressionService;

    public GitlabFaceService(IExpressionService expressionService,
                             //
                             IGroupService groupService,
                             //
                             IProjectService projectService,
                             //
                             IRepositoryService repositoryService,
                             //
                             IUserService userService) {
        this.expressionService = expressionService;
        this.groupService = groupService;
        this.projectService = projectService;
        this.repositoryService = repositoryService;
        this.userService = userService;
    }

    public IGroupService getGroupService() {
        return groupService;
    }

    public IProjectService getProjectService() {
        return projectService;
    }

    public IRepositoryService getRepositoryService() {
        return repositoryService;
    }

    public IUserService getUserService() {
        return userService;
    }

    public IExpressionService getExpressionService() {
        return expressionService;
    }
}
