package help.lixin.harobor.api;

import help.lixin.harobor.BasicTest;
import help.lixin.harobor.api.impl.ProjectService;
import help.lixin.harobor.dto.CreateProject;
import help.lixin.harobor.model.Project;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProjectServiceTest extends BasicTest {

    private IProjectService projectService;

    @Before
    public void init() {
        projectService = new ProjectService(harborProperties);
    }

    @Test
    public void testQuery() {
        int page = 1;
        int pageSize = 10;
        List<Project> results = projectService.query(page, pageSize);
        Assert.assertNotNull(results);
    }

    @Test
    public void testCreatProject() {
        CreateProject project = new CreateProject();
        project.setProject_name("test");
        project.setRegistry_id(null);
        project.getMetadata().setPublic("false");
        project.setStorage_limit(-1);
        projectService.create(project);
    }

    @Test
    public void testDelProject() {
        projectService.delete(7);
    }
}
