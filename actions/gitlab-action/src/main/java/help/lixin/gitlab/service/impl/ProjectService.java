package help.lixin.gitlab.service.impl;

import help.lixin.gitlab.service.IProjectService;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.ProjectApi;
import org.gitlab4j.api.models.*;

import java.util.List;

public class ProjectService implements IProjectService {

    private ProjectApi projectApi;

    public ProjectService(ProjectApi projectApi) {
        this.projectApi = projectApi;
    }

    @Override
    public List<Project> all() throws GitLabApiException {
        return projectApi.getProjects();
    }

    @Override
    public Project createProject(Group group, Project project) throws GitLabApiException {
        return projectApi.createProject(project);
    }

    @Override
    public void deleteProject(Long projectId) throws GitLabApiException {
        projectApi.deleteProject(projectId);
    }

    @Override
    public Member addMember(User user, String projectIdOrPath, AccessLevel accessLevel) throws GitLabApiException {
        return projectApi.addMember(projectIdOrPath, user.getId(), accessLevel);
    }
}
