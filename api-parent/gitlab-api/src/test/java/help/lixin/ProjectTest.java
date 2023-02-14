package help.lixin;


import org.gitlab4j.api.models.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ProjectTest extends BasicTest {
    @Test
    public void testAllProject() throws Exception {
        List<Project> projects = gitLabApi.getProjectApi().getProjects();
        Assert.assertNotNull(projects);
    }

    @Test
    public void testCreateProject() throws Exception {
        Project project = new Project();
        project.setName("test");

        List<Group> orderGroup = gitLabApi.getGroupApi().getGroups("order group");
        Group group = null;
        if (orderGroup.size() > 0) {
            group = orderGroup.get(0);
        }
        if (null != group) {
            project.setNamespace(new Namespace()
                    //
                    .withId(group.getId())
                    //
                    .withName(group.getName())
                    //
                    .withPath(group.getPath())
                    //
                    .withFullPath(group.getFullPath()));
        }
        project.setVisibility(Visibility.PRIVATE);
        project.setInitializeWithReadme(Boolean.TRUE);
        Project newProject = gitLabApi.getProjectApi().createProject(project);
        Assert.assertNotNull(newProject);
    }

    @Test
    public void testDeleteProject() throws Exception {
        List<Project> projects = gitLabApi.getProjectApi().getProjects();
        Project targetProject = null;
        for (Project project : projects) {
            if (project.getName().equals("test")) {
                targetProject = project;
                break;
            }
        }
        if (null != targetProject) {
            // id
            Long id = targetProject.getId();
            // namespace/path
            String pathWithNamespace = targetProject.getPathWithNamespace();
            Project testProject = gitLabApi.getProjectApi().getProject(pathWithNamespace);
            gitLabApi.getProjectApi().deleteProject(testProject.getId());
        }
    }

    @Test
    public void testProjectAddMember() throws Exception {
        Project project = gitLabApi.getProjectApi().getProject("order-group", "test");
        User zhgnsan = gitLabApi.getUserApi().getUser("zhangsan");
        gitLabApi.getProjectApi().addMember(project.getId(), zhgnsan.getId(), AccessLevel.DEVELOPER);

        // gitLabApi.getProjectApi().removeMember();
    }

}
