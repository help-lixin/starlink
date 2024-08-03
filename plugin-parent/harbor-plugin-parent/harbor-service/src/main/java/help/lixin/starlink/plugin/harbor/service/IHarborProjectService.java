package help.lixin.starlink.plugin.harbor.service;

import java.util.List;

import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.harbor.domain.HarborProject;
import help.lixin.starlink.plugin.harbor.dto.*;

public interface IHarborProjectService {

    int createProject(CreateProjectDTO createProjectDTO);

    List<HarborProject> queryProject(String projectName, String instanceCode);

    PageResponse<HarborProject> pageList(PageListDTO pageListDTO);

    Boolean removeProject(Long projectId);

    Boolean changeStatus(Long id, Integer status, String updateBy);

    List<RepositoryDTO> queryRepository(String projectName, String instanceCode);

    Boolean projectNameIsExist(String projectName, String instanceCode);

    List<IMageDTO> queryImages(String projectName, String repositoryName, String instanceCode);

    List<RepositoryNodeDTO> queryNodeList(String instanceCode, String projectName);

    Boolean changeAccessLevel(Long projectId);

    List<HarborProjectOption> queryOptions(String instanceCode);

    HarborProject getHarborProject(Long id);
}
