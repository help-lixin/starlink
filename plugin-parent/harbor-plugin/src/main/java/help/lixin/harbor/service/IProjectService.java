package help.lixin.harbor.service;

import help.lixin.harbor.dto.CreateProject;
import help.lixin.harbor.model.Project;

import java.util.List;

public interface IProjectService {
    // GET /api/v2.0/projects?page=1&page_size=10
    List<Project> query(int page, int pageSize);

    // POST /api/v2.0/projects
    void create(CreateProject project);

    void delete(Integer projectId);
}
