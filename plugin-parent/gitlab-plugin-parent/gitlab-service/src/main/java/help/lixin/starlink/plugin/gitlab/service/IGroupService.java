package help.lixin.starlink.plugin.gitlab.service;

import java.util.List;

import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.gitlab.domain.GitlabGroup;
import help.lixin.starlink.plugin.gitlab.dto.OptionListDTO;
import help.lixin.starlink.plugin.gitlab.dto.group.*;

public interface IGroupService {
    PageResponse<GitlabGroup> queryGroupList(GroupPageListDTO groupPageListDTO);

    GitlabGroup queryGroupInfoById(Long id);

    int addGroup(GroupSaveDTO groupSaveDTO);

    GitlabGroup queryByGroupName(GroupQueryDTO groupQueryDTO);

    int removeGroup(Long groupId);

    Boolean addMember(GroupMemberAddDTO groupMemberAddDTO);

    int updateMember(GroupMemberUpdateDTO updateMemberDTO);

    PageResponse<GroupMemberUserGroupDTO> queryMemberList(GroupMemberPageListDTO pageListDTO);

    List<OptionListDTO> optionalUsers(Long groupId, String userName);

    int removeMember(Long id, String updateBy);

    int changeStatus(Long groupId, Integer status);

    int changeMemberStatus(Long id, Integer status);

    List<GroupSelectGitlabIdOptionsDTO> querySelectGitlabIdOptions(String instanceCode);

    List<GroupSelectIdOptionsDTO> querySelectIdOptions(String instanceCode);

    String queryGitlabAddr(String instanceCode);
}
