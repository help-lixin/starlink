package help.lixin.starlink.plugin.gitlab.service.impl;

import java.util.Date;
import java.util.List;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.GroupApi;
import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.Visibility;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import help.lixin.enums.IsDelEnum;
import help.lixin.enums.StatusEnum;
import help.lixin.exception.ServiceException;
import help.lixin.response.PageResponse;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.gitlab.domain.GitlabGroup;
import help.lixin.starlink.plugin.gitlab.domain.GitlabUser;
import help.lixin.starlink.plugin.gitlab.domain.GitlabUserGroup;
import help.lixin.starlink.plugin.gitlab.dto.OptionListDTO;
import help.lixin.starlink.plugin.gitlab.dto.group.*;
import help.lixin.starlink.plugin.gitlab.mapper.GitlabGroupMapper;
import help.lixin.starlink.plugin.gitlab.mapper.GitlabUserGroupMapper;
import help.lixin.starlink.plugin.gitlab.mapper.GitlabUserMapper;
import help.lixin.starlink.plugin.gitlab.service.IGroupService;

public class GitlabGroupService implements IGroupService {

    private GitlabGroupMapper groupMapper;
    private GitlabUserGroupMapper userGroupMapper;
    private GitlabUserMapper userMapper;

    private final AbstractServiceFactory gitlabServiceFactory;

    private QueryTemplate queryTemplate;

    public GitlabGroupService(GitlabGroupMapper groupMapper, GitlabUserGroupMapper userGroupMapper,
        GitlabUserMapper userMapper, AbstractServiceFactory gitlabServiceFactory, QueryTemplate queryTemplate) {
        this.gitlabServiceFactory = gitlabServiceFactory;
        this.groupMapper = groupMapper;
        this.userGroupMapper = userGroupMapper;
        this.userMapper = userMapper;
        this.queryTemplate = queryTemplate;
    }

    @Override
    public PageResponse<GitlabGroup> queryGroupList(GroupPageListDTO groupPageListDTO) {
        return queryTemplate.execute(groupPageListDTO, () -> {
            groupMapper.queryGitlabGroupListByTenantId(groupPageListDTO);
        });
    }

    @Override
    public int changeStatus(Long groupId, Integer status) {
        GitlabGroup gitlabGroup = groupMapper.selectByPrimaryKey(groupId);

        if (gitlabGroup == null) {
            throw new ServiceException("组id不存在");
        }

        gitlabGroup.setStatus(status);

        return groupMapper.updateByPrimaryKey(gitlabGroup);
    }

    @Override
    public int changeMemberStatus(Long id, Integer status) {
        GitlabUserGroup gitlabUserGroup = userGroupMapper.selectById(id);
        if (gitlabUserGroup == null) {
            throw new ServiceException("组成员id不存在");
        }

        return userGroupMapper.updateById(gitlabUserGroup);
    }

    @Override
    public List<GroupSelectGitlabIdOptionsDTO> querySelectGitlabIdOptions(String instanceCode) {
        return groupMapper.querySelectGitlabIdOptions(instanceCode);
    }

    @Override
    public List<GroupSelectIdOptionsDTO> querySelectIdOptions(String instanceCode) {
        return groupMapper.querySelectIdOptions(instanceCode);
    }

    @Override
    public String queryGitlabAddr(String instanceCode) {
        GitLabApi gitLabApi = gitlabServiceFactory.getInstance(instanceCode, GitLabApi.class);
        String gitLabServerUrl = gitLabApi.getGitLabServerUrl();
        if (gitLabServerUrl.endsWith("/")) {
            return gitLabServerUrl.substring(0, gitLabServerUrl.length() - 1);
        } else {
            return gitLabServerUrl;
        }
    }

    @Override
    public GitlabGroup queryGroupInfoById(Long id) {
        return groupMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public int addGroup(GroupSaveDTO groupSaveDTO) {

        Long groupId = groupSaveDTO.getGroupId();
        GitlabGroup queryGroupInfo = null;
        if (groupId == null) {
            GitlabGroup gitlabGroupInfo = groupMapper.selectOne(new QueryWrapper<GitlabGroup>()
                .eq("is_del", IsDelEnum.NORMAL.getValue()).eq("gitlab_group_name", groupSaveDTO.getGroupName()));
            if (gitlabGroupInfo != null) {
                throw new ServiceException("组名已存在");
            }
        } else {
            queryGroupInfo = groupMapper.selectByPrimaryKey(groupId);
            if (queryGroupInfo == null) {
                throw new ServiceException("组id不存在");
            }
        }

        GroupApi groupApi = gitlabServiceFactory.getInstance(groupSaveDTO.getInstanceCode(), GroupApi.class);

        Group groupInfo;
        GitlabGroup gitlabGroup = new GitlabGroup();

        try {
            // 新增
            if (queryGroupInfo == null) {
                Group group = new Group();
                group.setName(groupSaveDTO.getGroupName());
                // TODO lixin 保存可见性级别后会有一些错误日志出现，不影响使用
                group.setVisibility(Visibility.forValue(groupSaveDTO.getVisibility().name()));
                group.setPath(groupSaveDTO.getPath());
                group.setDescription(groupSaveDTO.getRemark());
                groupInfo = groupApi.addGroup(group);
                gitlabGroup.setCreateBy(groupSaveDTO.getCreateBy());
                gitlabGroup.setCreateTime(new Date());
            } else { // 更新
                groupInfo = groupApi.getGroup(queryGroupInfo.getGitlabGroupId());
                if (groupInfo == null) {
                    throw new ServiceException("组不存在该数据");
                }
                groupInfo.setVisibility(Visibility.forValue(groupSaveDTO.getVisibility().name()));
                groupInfo.setPath(groupSaveDTO.getPath());
                groupInfo.setDescription(groupSaveDTO.getRemark());
                groupInfo.setName(groupSaveDTO.getGroupName());
                groupApi.updateGroup(groupInfo);
                gitlabGroup.setId(groupSaveDTO.getGroupId());
            }
        } catch (GitLabApiException e) {
            throw new ServiceException("gitlabAPI出现异常：" + e.getMessage());
        }

        gitlabGroup.setGitlabGroupName(groupInfo.getName());
        gitlabGroup.setGitlabGroupId(groupInfo.getId());
        gitlabGroup.setRemark(groupSaveDTO.getRemark());
        gitlabGroup.setInstanceCode(groupSaveDTO.getInstanceCode());
        gitlabGroup.setVisibility(groupSaveDTO.getVisibility().name());
        gitlabGroup.setPath(groupSaveDTO.getPath());
        gitlabGroup.setStatus(groupSaveDTO.getStatus());
        gitlabGroup.setUpdateBy(groupSaveDTO.getCreateBy());

        if (gitlabGroup.getId() == null) {
            return groupMapper.insertSelective(gitlabGroup);
        }

        return groupMapper.updateByPrimaryKeySelective(gitlabGroup);
    }

    @Override
    public GitlabGroup queryByGroupName(GroupQueryDTO groupQueryDTO) {
        GitlabGroup gitlabGroup = groupMapper.queryGroupByGroupName(groupQueryDTO);
        if (gitlabGroup == null) {
            throw new ServiceException("组不存在该数据");
        }
        return gitlabGroup;
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public int removeGroup(Long groupId) {
        GitlabGroup gitlabGroup = groupMapper.selectByPrimaryKey(groupId);
        if (gitlabGroup == null) {
            throw new ServiceException("组不存在该数据");
        }
        GroupApi groupApi = gitlabServiceFactory.getInstance(gitlabGroup.getInstanceCode(), GroupApi.class);
        try {
            Group group = groupApi.getGroup(gitlabGroup.getGitlabGroupId());
            if (group == null) {
                throw new ServiceException("组不存在该数据");
            }
            int result = groupMapper.removeGroup(groupId);
            groupApi.deleteGroup(gitlabGroup.getGitlabGroupId());
            return result;
        } catch (GitLabApiException e) {
            throw new ServiceException("gitlabAPI出现异常：" + e.getMessage());
        }

    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean addMember(GroupMemberAddDTO groupMemberAddDTO) {
        GitlabGroup gitlabGroup = groupMapper.selectByPrimaryKey(groupMemberAddDTO.getGroupId());
        if (gitlabGroup == null) {
            throw new ServiceException("组数据不存在");
        }

        Long userId = groupMemberAddDTO.getUserId();

        // 查出库中存在的成员数据
        GroupMemberPageListDTO pageListDTO = new GroupMemberPageListDTO();
        pageListDTO.setGroupId(groupMemberAddDTO.getGroupId());

        GroupApi groupApi = gitlabServiceFactory.getInstance(groupMemberAddDTO.getInstanceCode(), GroupApi.class);

        GitlabUser gitlabUser = userMapper.selectByPrimaryKey(userId);
        if (gitlabUser == null) {
            throw new ServiceException("用户数据不存在");
        }

        QueryWrapper<GitlabUserGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_del", IsDelEnum.NORMAL.getValue());
        queryWrapper.eq("status", StatusEnum.NORMAL.getValue());
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("group_id", groupMemberAddDTO.getGroupId());
        GitlabUserGroup gitlabUserGroup = userGroupMapper.selectOne(queryWrapper);
        if (gitlabUserGroup != null) {
            throw new ServiceException("存在相同数据");
        }

        try {
            groupApi.addMember(gitlabGroup.getGitlabGroupId(), gitlabUser.getGitlabUserId(),
                AccessLevel.forValue(groupMemberAddDTO.getAccessLevel().value), groupMemberAddDTO.getExpiresAt());
        } catch (GitLabApiException e) {
            throw new ServiceException("gitlabAPI出现异常：" + e.getMessage());
        }
        GitlabUserGroup userGroup = new GitlabUserGroup();
        userGroup.setGroupId(groupMemberAddDTO.getGroupId());
        userGroup.setUserId(userId);
        userGroup.setInstanceCode(groupMemberAddDTO.getInstanceCode());
        userGroup.setAccessLevel(groupMemberAddDTO.getAccessLevel().value);
        userGroup.setStatus(StatusEnum.NORMAL.getValue());
        userGroup.setExpiresAt(groupMemberAddDTO.getExpiresAt());
        userGroup.setCreateBy(groupMemberAddDTO.getCreateBy());
        userGroup.setCreateTime(new Date());

        userGroupMapper.insertGroup(userGroup);

        return true;
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public int updateMember(GroupMemberUpdateDTO updateMemberDTO) {
        GitlabUserGroup userGroupInfo = userGroupMapper.selectById(updateMemberDTO.getUserGroupId());
        if (userGroupInfo == null) {
            throw new ServiceException("不存在该用户组数据");
        }

        GitlabGroup gitlabGroup = groupMapper.selectByPrimaryKey(userGroupInfo.getGroupId());
        if (userGroupInfo == null) {
            throw new ServiceException("不存在该群组数据");
        }

        GitlabUser gitlabUser = userMapper.selectByPrimaryKey(userGroupInfo.getUserId());
        if (userGroupInfo == null) {
            throw new ServiceException("不存在该用户数据");
        }

        GroupApi groupApi = gitlabServiceFactory.getInstance(userGroupInfo.getInstanceCode(), GroupApi.class);
        try {
            groupApi.updateMember(gitlabGroup.getGitlabGroupId(), gitlabUser.getGitlabUserId(),
                AccessLevel.forValue(updateMemberDTO.getAccessLevel().value));
        } catch (GitLabApiException e) {
            throw new ServiceException("gitlabAPI出现异常：" + e.getMessage());
        }

        userGroupInfo.setAccessLevel(updateMemberDTO.getAccessLevel().value);
        userGroupInfo.setUpdateBy(updateMemberDTO.getUpdateBy());
        userGroupInfo.setUpdateTime(new Date());

        return userGroupMapper.updateById(userGroupInfo);

    }

    @Override
    public PageResponse<GroupMemberUserGroupDTO> queryMemberList(GroupMemberPageListDTO groupMemberPageListDTO) {

        if (groupMemberPageListDTO.getGroupId() != null) {
            GitlabGroup gitlabGroup = groupMapper.selectByPrimaryKey(groupMemberPageListDTO.getGroupId());
            if (gitlabGroup == null) {
                throw new ServiceException("组不存在该数据");
            }
        }

        return queryTemplate.execute(groupMemberPageListDTO, () -> {
            userGroupMapper.queryUserGroupList(groupMemberPageListDTO);
        });
    }

    @Override
    public List<OptionListDTO> optionalUsers(Long groupId, String userName) {
        List<Long> userIds = userGroupMapper.queryByGroupId(groupId);
        return userMapper.queryNotInIds(userIds, userName);
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public int removeMember(Long id, String updateBy) {

        GitlabUserGroup gitlabUserGroup = userGroupMapper.selectById(id);
        if (gitlabUserGroup == null) {
            throw new ServiceException("该id不存在");
        }

        GitlabUser gitlabUser = userMapper.selectByPrimaryKey(gitlabUserGroup.getUserId());
        GitlabGroup gitlabGroup = groupMapper.selectByPrimaryKey(gitlabUserGroup.getGroupId());
        if (gitlabUser == null) {
            throw new ServiceException("用户id不存在");
        }
        if (gitlabGroup == null) {
            throw new ServiceException("组id不存在");
        }

        GroupApi groupApi = gitlabServiceFactory.getInstance(gitlabGroup.getInstanceCode(), GroupApi.class);
        try {
            Member member = groupApi.getMember(gitlabGroup.getGitlabGroupId(), gitlabUser.getGitlabUserId());

            if (member == null) {
                throw new ServiceException("成员在gitlab中不存在");
            }

            int result = userGroupMapper.removeById(gitlabUserGroup.getId(), updateBy);
            groupApi.removeMember(gitlabGroup.getGitlabGroupId(), gitlabUser.getGitlabUserId());
            return result;
        } catch (GitLabApiException e) {
            throw new ServiceException("gitlabAPI出现异常：" + e.getMessage());
        }

    }

}
