package help.lixin.starlink.plugin.gitlab.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import help.lixin.starlink.plugin.gitlab.domain.GitlabUser;
import help.lixin.starlink.plugin.gitlab.dto.OptionListDTO;
import help.lixin.starlink.plugin.gitlab.dto.group.GroupMemberPageListDTO;
import help.lixin.starlink.plugin.gitlab.dto.project.ProjectPageListDTO;
import help.lixin.starlink.plugin.gitlab.dto.user.UserPageListDTO;
import help.lixin.starlink.plugin.gitlab.dto.user.UserProjectDTO;
import help.lixin.starlink.plugin.gitlab.dto.user.UserSelectOptionDTO;

public interface GitlabUserMapper extends BaseMapper<GitlabUser> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(GitlabUser record);

    GitlabUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GitlabUser record);

    int updateByPrimaryKey(GitlabUser record);

    /** ==================== custom sql ==================== **/

    List<GitlabUser> selectUserList(UserPageListDTO userPageListDTO);

    GitlabUser selectUser(@Param("userName") String userName);

    List<GitlabUser> selectUserByGroupIdOfGroupProject(GroupMemberPageListDTO groupMemberPageListDTO);

    List<UserProjectDTO> selectUserByProjectIdOfUserProject(ProjectPageListDTO pageListDTO);

    int removeById(@Param("id") Long id);

    List<GitlabUser> queryUserByGitlabUserIds(List<Long> gitlabUserIds);

    List<UserSelectOptionDTO> querySelectOption(@Param("instanceCode") String instanceCode);

    GitlabUser queryUserBySysUserId(Long sysUserId);

    List<OptionListDTO> queryNotInIds(@Param("userIds") List<Long> userIds, @Param("userName") String userName);

}