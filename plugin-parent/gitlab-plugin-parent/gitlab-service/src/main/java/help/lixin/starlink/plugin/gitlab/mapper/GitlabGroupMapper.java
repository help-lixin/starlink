package help.lixin.starlink.plugin.gitlab.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import help.lixin.starlink.plugin.gitlab.domain.GitlabGroup;
import help.lixin.starlink.plugin.gitlab.dto.group.GroupPageListDTO;
import help.lixin.starlink.plugin.gitlab.dto.group.GroupQueryDTO;
import help.lixin.starlink.plugin.gitlab.dto.group.GroupSelectGitlabIdOptionsDTO;
import help.lixin.starlink.plugin.gitlab.dto.group.GroupSelectIdOptionsDTO;
import help.lixin.starlink.plugin.gitlab.dto.project.ProjectGroupListDTO;

import java.util.List;

public interface GitlabGroupMapper extends BaseMapper<GitlabGroup> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(GitlabGroup record);

    GitlabGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GitlabGroup record);

    int updateByPrimaryKey(GitlabGroup record);

    /** ============= custom sql ============= **/
    GitlabGroup queryGroupByGroupName(GroupQueryDTO groupQueryDTO);

    GitlabGroup selectByGitlabGroupId(Long gitlabGroupId);

    List<GitlabGroup> queryGitlabGroupListByTenantId(GroupPageListDTO groupPageListDTO);

    List<GitlabGroup> queryGroupByGitlabGroupIds(List<Long> gitlabGroupIds);

    List<GitlabGroup> selectGroupByProjectIdOfGroupProject(ProjectGroupListDTO pageListDTO);

    List<GroupSelectGitlabIdOptionsDTO> querySelectGitlabIdOptions(String instanceCode);

    List<GroupSelectIdOptionsDTO> querySelectIdOptions(String instanceCode);

    int removeGroup(Long id);

}