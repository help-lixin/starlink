package help.lixin.starlink.plugin.gitlab.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import help.lixin.starlink.plugin.gitlab.domain.GitlabUserGroup;
import help.lixin.starlink.plugin.gitlab.dto.group.GroupMemberPageListDTO;
import help.lixin.starlink.plugin.gitlab.dto.group.GroupMemberUserGroupDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GitlabUserGroupMapper extends BaseMapper<GitlabUserGroup> {
    GitlabUserGroup queryByUserIdAndGroupId(@Param("userId") Long userId,@Param("groupId") Long groupId);

    List<Long> queryByGroupId(@Param("groupId") Long groupId);

    List<GroupMemberUserGroupDTO> queryUserGroupList(GroupMemberPageListDTO groupMemberPageListDTO);

    int insertGroup(GitlabUserGroup entity);

    int removeById(@Param("id")Long id,@Param("updateBy")String updateBy);
}