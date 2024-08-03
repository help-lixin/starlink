package help.lixin.starlink.plugin.gitlab.service;

import java.util.List;

import org.gitlab4j.api.GitLabApiException;

import help.lixin.starlink.core.dto.EnvDTO;
import help.lixin.dto.Option;
import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.gitlab.domain.GitlabGroup;
import help.lixin.starlink.plugin.gitlab.domain.GitlabProject;
import help.lixin.starlink.plugin.gitlab.dto.OptionListDTO;
import help.lixin.starlink.plugin.gitlab.dto.base.ChangeStatusDTO;
import help.lixin.starlink.plugin.gitlab.dto.project.*;
import help.lixin.starlink.plugin.gitlab.dto.user.UserProjectDTO;

public interface IProjectService {

    PageResponse<GitlabProject> queryProjects(ProjectQueryDTO projectQueryDTO) throws GitLabApiException;

    List<Option> queryProjectOption(String instanceCode) throws GitLabApiException;

    List<Option> branchList(Long projectId, String instanceCode);

    Integer saveProject(ProjectSaveDTO projectSaveDTO) throws GitLabApiException;

    Integer removeProject(Long projectId) throws GitLabApiException;

    Integer changeStatus(ChangeStatusDTO changeStatusDTO);

    Integer changeMemberStatus(Long id, Integer status);

    Integer updateProject(String projectName, Long projectId, String updateBy, EnvDTO envDTO) throws GitLabApiException;

    PageResponse<GitlabGroup> queryGroupMemberList(ProjectGroupListDTO projectGroupListDTO);

    PageResponse<UserProjectDTO> queryUserMemberList(ProjectPageListDTO pageListDTO);

    Boolean saveProjectMemeber(ProjectMemberSaveDTO projectMemberSaveDTO);

    Integer updateMember(ProjectMemberUpdateDTO projectMemberUpdateDTO);

    Integer removeMember(Long id) throws GitLabApiException;

    GitlabProject queryByGitlabProjectId(ProjectInfoDTO projectInfoDTO);

    List<OptionListDTO> optionalUsers(Long projectId, String userName);
}
