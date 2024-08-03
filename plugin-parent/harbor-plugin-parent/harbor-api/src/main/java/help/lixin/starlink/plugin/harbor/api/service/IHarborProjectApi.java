package help.lixin.starlink.plugin.harbor.api.service;

import java.util.List;

import help.lixin.starlink.plugin.harbor.api.dto.CreateProject;
import help.lixin.starlink.plugin.harbor.api.dto.Project;
import help.lixin.starlink.plugin.harbor.api.dto.TotalPageDTO;
import help.lixin.starlink.plugin.harbor.api.dto.repository.ArtifactDTO;
import help.lixin.starlink.plugin.harbor.api.dto.repository.RepositoriesDTO;

public interface IHarborProjectApi {
    // GET /api/v2.0/projects?project_name=library
    Boolean checkProjectNameIsExist(String projectName);

    // GET /api/v2.0/projects?page=1&page_size=10
    List<Project> query(int page, int pageSize, String name, Integer publicFlag);

    TotalPageDTO totalPage();

    // POST /api/v2.0/projects
    void create(CreateProject project);

    void delete(Long projectId);

    // 查询仓库目录
    List<RepositoriesDTO> queryRepositories(String projectName);

    // 查询仓库下镜像列表
    List<ArtifactDTO> queryImages(String projectName, String repostoryName);

    Boolean changeAccessLevel(Long harborProjectId);

}
