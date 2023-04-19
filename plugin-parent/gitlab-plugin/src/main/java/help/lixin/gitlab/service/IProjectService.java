package help.lixin.gitlab.service;

import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.*;

import java.util.List;

public interface IProjectService {

    List<Project> all() throws GitLabApiException;

    Project createProject(Group group, Project project) throws GitLabApiException;

    void deleteProject(Long projectId) throws GitLabApiException;

    Member addMember(User user, String projectIdOrPath, AccessLevel accessLevel) throws GitLabApiException;
}
