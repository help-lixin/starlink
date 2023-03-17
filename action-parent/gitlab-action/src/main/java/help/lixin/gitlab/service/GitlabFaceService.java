package help.lixin.gitlab.service;

public class GitlabFaceService {

    private IGroupService groupService;

    private IProjectService projectService;

    private IRepositoryService repositoryService;

    private IUserService userService;

    public GitlabFaceService(IGroupService groupService,
                             //
                             IProjectService projectService,
                             //
                             IRepositoryService repositoryService,
                             //
                             IUserService userService) {
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
}
