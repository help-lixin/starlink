package help.lixin.starlink.plugin.gitlab.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.ProjectApi;
import org.gitlab4j.api.RepositoryApi;
import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.Visibility;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.dto.Option;
import help.lixin.exception.ServiceException;
import help.lixin.response.PageResponse;
import help.lixin.starlink.core.dto.EnvDTO;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.gitlab.convert.ProjectServiceConvert;
import help.lixin.starlink.plugin.gitlab.domain.*;
import help.lixin.starlink.plugin.gitlab.dto.OptionListDTO;
import help.lixin.starlink.plugin.gitlab.dto.base.ChangeStatusDTO;
import help.lixin.starlink.plugin.gitlab.dto.project.*;
import help.lixin.starlink.plugin.gitlab.dto.user.UserProjectDTO;
import help.lixin.starlink.plugin.gitlab.mapper.*;
import help.lixin.starlink.plugin.gitlab.service.IProjectService;

public class GitlabProjectService implements IProjectService {

    private Logger logger = LoggerFactory.getLogger(GitlabProjectService.class);
    private GitlabUserMapper userMapper;
    private GitlabProjectMapper projectMapper;
    private GitlabUserProjectMapper userProjectMapper;
    private GitlabGroupProjectMapper groupProjectMapper;
    private GitlabGroupMapper groupMapper;

    private QueryTemplate queryTemplate;

    private final AbstractServiceFactory gitlabServiceFactory;

    public GitlabProjectService(AbstractServiceFactory gitlabServiceFactory, GitlabProjectMapper projectMapper,
        GitlabUserProjectMapper userProjectMapper, GitlabGroupProjectMapper groupProjectMapper,
        GitlabUserMapper userMapper, GitlabGroupMapper groupMapper, QueryTemplate queryTemplate) {
        this.gitlabServiceFactory = gitlabServiceFactory;
        this.projectMapper = projectMapper;
        this.userProjectMapper = userProjectMapper;
        this.groupProjectMapper = groupProjectMapper;
        this.userMapper = userMapper;
        this.groupMapper = groupMapper;
        this.queryTemplate = queryTemplate;
    }

    @Override
    public PageResponse<GitlabProject> queryProjects(ProjectQueryDTO projectQueryDTO) {
        return queryTemplate.execute(projectQueryDTO, () -> {
            projectMapper.queryProjects(projectQueryDTO);
        });
    }

    @Override
    public List<Option> queryProjectOption(String instanceCode) {
        List<GitlabProject> gitlabProjects = projectMapper.queryProjectListByInstanceCode(instanceCode);
        return gitlabProjects //
            .stream() //
            .map((item) -> {
                Option option = new Option();
                option.setLabel(item.getProjectName());
                option.setValue(String.valueOf(item.getGitlabProjectId()));
                return option;
            })//
            .collect(Collectors.toList());
    }

    @Override
    public List<Option> branchList(Long projectId, String instanceCode) {
        try {
            RepositoryApi repositoryApi = gitlabServiceFactory.getInstance(instanceCode, RepositoryApi.class);
            List<Branch> branches = repositoryApi.getBranches(projectId);
            return branches.stream().map(item -> {
                Option option = new Option();
                option.setLabel(item.getName());
                option.setValue(item.getName());
                return option;
            }).collect(Collectors.toList());
        } catch (GitLabApiException e) {
            String errMsg = "获取分支异常:" + e.getMessage();
            logger.error(errMsg);
            throw new ServiceException(errMsg);
        }
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer saveProject(ProjectSaveDTO projectSaveDTO) {

        Project projectInfo = null;
        // gitlab的NameSpaceId
        Long nameSpaceId = projectSaveDTO.getNamespaceByGroup() == null ? projectSaveDTO.getNamespaceByUser()
            : projectSaveDTO.getNamespaceByGroup();
        GitlabProject gitlabProject =
            projectMapper.queryProjectByProjectName(projectSaveDTO.getProjectName(), projectSaveDTO.getInstanceCode());
        if (gitlabProject != null && projectSaveDTO.getId() == null) {
            String errMsg = "项目名已存在，不能重复创建";
            logger.error(errMsg);
            throw new ServiceException(errMsg);
        }

        String createBy = UserContext.getUser().getUserName();

        ProjectApi projectApi = gitlabServiceFactory.getInstance(projectSaveDTO.getInstanceCode(), ProjectApi.class);
        Project project = new Project();
        project.setName(projectSaveDTO.getProjectName());
        project.setVisibility(Visibility.forValue(projectSaveDTO.getVisibility().name()));

        // 验证路径合法
        String path = projectSaveDTO.getPath();
        boolean validatePath = validatePath(path);

        if (validatePath) {
            String errMsg = "唯一编码只允许英文字母数字跟下划线";
            logger.error(errMsg);
            throw new ServiceException(errMsg);
        }

        project.setPath(path);
        project.setInitializeWithReadme(projectSaveDTO.getInitiallizeWithReadme());
        project.setDescription(projectSaveDTO.getRemark());

        try {
            // 需要想下namespace应该如何替换

            // 保存gitlab
            if (projectSaveDTO.getId() == null) {
                projectInfo = projectApi.createProject(nameSpaceId, project);
                gitlabProject = new GitlabProject();
            } else {
                gitlabProject = projectMapper.selectByPrimaryKey(projectSaveDTO.getId());
                if (gitlabProject == null) {
                    throw new ServiceException("此id不存在");
                }
                projectInfo = projectApi.getProject(gitlabProject.getGitlabProjectId());
                if (projectInfo == null) {
                    throw new ServiceException("此gitlab项目不存在");
                }
                projectInfo.setName(projectSaveDTO.getProjectName());
                projectInfo.setDescription(projectSaveDTO.getRemark());

                projectApi.updateProject(projectInfo);
            }
        } catch (GitLabApiException e) {
            throw new ServiceException("gitlab api发生异常" + e.getMessage());
        }

        gitlabProject.setProjectName(projectSaveDTO.getProjectName());
        gitlabProject.setRemark(projectSaveDTO.getRemark());
        gitlabProject.setStatus(projectSaveDTO.getStatus());

        // 保存到数据库
        if (projectSaveDTO.getId() == null) {
            gitlabProject.setInitiallizeWithReadme(projectSaveDTO.getInitiallizeWithReadme());
            gitlabProject.setCreateBy(createBy);
            gitlabProject.setInstanceCode(projectSaveDTO.getInstanceCode());
            gitlabProject.setGitlabProjectId(projectInfo.getId());
            gitlabProject.setSshUrl(projectInfo.getSshUrlToRepo());
            gitlabProject.setWebUrl(projectInfo.getHttpUrlToRepo());
            gitlabProject.setVisibility(projectSaveDTO.getVisibility().name());
            gitlabProject.setNamespaceByGroup(projectSaveDTO.getNamespaceByGroup());
            gitlabProject.setNamespaceByUser(projectSaveDTO.getNamespaceByUser());
            gitlabProject.setPath(projectSaveDTO.getPath());
            projectMapper.insertSelective(gitlabProject);

        } else {
            gitlabProject.setUpdateBy(createBy);
            projectMapper.updateByPrimaryKey(gitlabProject);
        }

        // 保存到中间表
        if (projectSaveDTO.getNamespaceByUser() != null) {
            GitlabUserProject userProject = userProjectMapper.queryProjectByProjectId(gitlabProject.getId());
            if (userProject == null) {
                userProject = new GitlabUserProject();

                userProject.setUserId(projectSaveDTO.getNamespaceByUser());
                userProject.setProjectId(gitlabProject.getId());
                userProject.setCreateBy(createBy);
                return userProjectMapper.insert(userProject);
            } else {
                userProject.setUserId(projectSaveDTO.getNamespaceByUser());
                userProject.setProjectId(gitlabProject.getId());
                userProject.setUpdateBy(createBy);
                return userProjectMapper.updateById(userProject);
            }

        } else {
            GitlabGroupProject groupProject = groupProjectMapper.queryProjectByProjectId(gitlabProject.getId());
            if (groupProject == null) {
                groupProject = new GitlabGroupProject();
                groupProject.setGroupId(projectSaveDTO.getNamespaceByGroup());
                groupProject.setProjectId(gitlabProject.getId());
                groupProject.setCreateBy(createBy);
                return groupProjectMapper.insertSelective(groupProject);
            } else {
                groupProject.setGroupId(projectSaveDTO.getNamespaceByGroup());
                groupProject.setProjectId(gitlabProject.getId());
                groupProject.setUpdateBy(createBy);
                return groupProjectMapper.updateByPrimaryKeySelective(groupProject);
            }
        }

    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer removeProject(Long projectId) {
        GitlabProject gitlabProject = projectMapper.selectByPrimaryKey(projectId);
        if (gitlabProject == null) {
            String errMsg = "项目在库中不存在";
            logger.error(errMsg);
            throw new ServiceException(errMsg);
        }
        ProjectApi projectApi = gitlabServiceFactory.getInstance(gitlabProject.getInstanceCode(), ProjectApi.class);
        if (projectApi == null) {
            String errMsg = "项目在gitlab中不存在";
            logger.error(errMsg);
            throw new ServiceException(errMsg);
        }

        int result = projectMapper.removeProject(gitlabProject.getId());
        try {
            projectApi.deleteProject(gitlabProject.getGitlabProjectId());
        } catch (GitLabApiException e) {
            throw new ServiceException("gitlab删除项目出现异常:" + e.getMessage());
        }

        return result;
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer changeStatus(ChangeStatusDTO changeStatusDTO) {
        Long id = changeStatusDTO.getId();
        Integer status = changeStatusDTO.getStatus();

        // 根据id查询项目
        GitlabProject gitlabProject = projectMapper.selectByPrimaryKey(id);
        if (gitlabProject == null) {
            throw new ServiceException("该id不存在");
        }

        gitlabProject.setStatus(status);
        gitlabProject.setUpdateBy(changeStatusDTO.getCreateBy());
        // 更新项目状态获取更新结果
        int result = projectMapper.updateByPrimaryKeySelective(gitlabProject);

        // 更新gitlab中的权限状态
        try {
            ProjectApi projectApi =
                gitlabServiceFactory.getInstance(changeStatusDTO.getInstanceCode(), ProjectApi.class);
            Project project = projectApi.getProject(gitlabProject.getGitlabProjectId());
            if (status == 0) {
                project.setVisibility(Visibility.PRIVATE);
            } else {
                project.setVisibility(Visibility.valueOf(gitlabProject.getVisibility()));
            }
            projectApi.updateProject(project);
        } catch (GitLabApiException e) {
            throw new ServiceException("gitlabAPI出现异常：" + e.getMessage());
        }

        return result;
    }

    @Override
    public Integer changeMemberStatus(Long id, Integer status) {
        GitlabUserProject gitlabUserProject = userProjectMapper.selectById(id);
        if (gitlabUserProject == null) {
            throw new ServiceException("该id不存在，请确认后重试");
        }

        gitlabUserProject.setStatus(status);
        return userProjectMapper.updateById(gitlabUserProject);
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer updateProject(String projectName, Long projectId, String updateBy, EnvDTO envDTO)
        throws GitLabApiException {
        GitlabProject gitlabProject = projectMapper.selectByPrimaryKey(projectId);
        if (gitlabProject == null) {
            throw new ServiceException("项目在gitlab中不存在");
        }

        ProjectApi projectApi = gitlabServiceFactory.getInstance(envDTO.getInstanceCode(), ProjectApi.class);
        Project projectInfo = projectApi.getProject(gitlabProject.getGitlabProjectId());
        if (projectInfo == null) {
            throw new ServiceException("项目在库中不存在");
        }

        Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setId(gitlabProject.getGitlabProjectId());
        projectApi.updateProject(newProject);

        gitlabProject = new GitlabProject();
        gitlabProject.setId(projectId);
        gitlabProject.setProjectName(projectName);
        gitlabProject.setInstanceCode(envDTO.getInstanceCode());
        gitlabProject.setUpdateBy(updateBy);
        return projectMapper.updateByPrimaryKeySelective(gitlabProject);

    }

    @Override
    public PageResponse<GitlabGroup> queryGroupMemberList(ProjectGroupListDTO projectGroupListDTO) {
        GitlabProject gitlabProject = projectMapper.selectByPrimaryKey(projectGroupListDTO.getProjectId());
        if (gitlabProject == null) {
            throw new ServiceException("项目不存在该数据");
        }

        return queryTemplate.execute(projectGroupListDTO, () -> {
            groupMapper.selectGroupByProjectIdOfGroupProject(projectGroupListDTO);
        });
    }

    @Override
    public PageResponse<UserProjectDTO> queryUserMemberList(ProjectPageListDTO pageListDTO) {

        return queryTemplate.execute(pageListDTO, () -> {
            userMapper.selectUserByProjectIdOfUserProject(pageListDTO);
        });
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean saveProjectMemeber(ProjectMemberSaveDTO projectMemberSaveDTO) {
        Long userId = projectMemberSaveDTO.getUserId();
        Long projectId = projectMemberSaveDTO.getProjectId();

        GitlabUser gitlabUser = userMapper.selectByPrimaryKey(userId);
        if (gitlabUser == null) {
            throw new ServiceException("该用户不存在");
        }

        GitlabProject gitlabProject = projectMapper.selectByPrimaryKey(projectId);
        if (gitlabProject == null) {
            throw new ServiceException("该项目不存在");
        }

        GitlabUserProject gitlabUserProject = userProjectMapper.selectByUserIdAndProjectId(userId, projectId);
        if (gitlabUserProject != null) {
            throw new ServiceException("已存在相同的数据，不能再次添加");
        }

        ProjectApi projectApi =
            gitlabServiceFactory.getInstance(projectMemberSaveDTO.getInstanceCode(), ProjectApi.class);

        ProjectServiceConvert mapper = Mappers.getMapper(ProjectServiceConvert.class);
        GitlabUserProject insertData = mapper.convert(projectMemberSaveDTO);
        insertData.setUserId(userId);
        userProjectMapper.insert(insertData);

        try {
            projectApi.addMember(gitlabProject.getGitlabProjectId(), gitlabUser.getGitlabUserId(),
                projectMemberSaveDTO.getAccessLevel(), projectMemberSaveDTO.getExpiresAt());
        } catch (GitLabApiException e) {
            throw new ServiceException("gitlabAPI发生异常" + e.getMessage());
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer removeMember(Long id) throws GitLabApiException {
        GitlabUserProject gitlabUserProject = userProjectMapper.selectById(id);
        if (gitlabUserProject == null) {
            throw new ServiceException("项目成员id在数据库中不存在");
        }

        GitlabProject gitlabProject = projectMapper.selectByPrimaryKey(gitlabUserProject.getProjectId());
        if (gitlabProject == null) {
            throw new ServiceException("项目id在数据库中不存在");
        }

        GitlabUser gitlabUser = userMapper.selectByPrimaryKey(gitlabUserProject.getUserId());
        if (gitlabUser == null) {
            throw new ServiceException("成员id在数据库中不存在");
        }

        int result = userProjectMapper.removeById(gitlabUserProject.getId());
        ProjectApi projectApi = gitlabServiceFactory.getInstance(gitlabProject.getInstanceCode(), ProjectApi.class);
        projectApi.removeMember(gitlabProject.getGitlabProjectId(), gitlabUser.getGitlabUserId());

        return result;

    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer updateMember(ProjectMemberUpdateDTO projectMemberUpdateDTO) {
        GitlabUserProject userProject = userProjectMapper.selectById(projectMemberUpdateDTO.getUserProjectId());
        if (userProject == null) {
            throw new ServiceException("不存在该用户数据");
        }

        GitlabProject gitlabProject = projectMapper.selectByPrimaryKey(userProject.getProjectId());
        if (gitlabProject == null) {
            throw new ServiceException("不存在该项目数据");
        }

        GitlabUser gitlabUser = userMapper.selectByPrimaryKey(userProject.getUserId());
        if (userProject == null) {
            throw new ServiceException("不存在该用户数据");
        }

        ProjectApi projectApi = gitlabServiceFactory.getInstance(userProject.getInstanceCode(), ProjectApi.class);
        try {
            projectApi.updateMember(gitlabProject.getGitlabProjectId(), gitlabUser.getGitlabUserId(),
                AccessLevel.forValue(projectMemberUpdateDTO.getAccessLevel().value));
        } catch (GitLabApiException e) {
            throw new ServiceException("gitlabAPI出现异常：" + e.getMessage());
        }

        userProject.setAccessLevel(projectMemberUpdateDTO.getAccessLevel().value);
        userProject.setUpdateBy(projectMemberUpdateDTO.getUpdateBy());
        userProject.setUpdateTime(new Date());

        return userProjectMapper.updateById(userProject);
    }

    @Override
    public GitlabProject queryByGitlabProjectId(ProjectInfoDTO projectInfoDTO) {
        return projectMapper.queryByGitlabProjectId(projectInfoDTO);
    }

    @Override
    public List<OptionListDTO> optionalUsers(Long projectId, String userName) {
        List<Long> userIds = userProjectMapper.queryUserIdByProjectId(projectId);
        return userMapper.queryNotInIds(userIds, userName);
    }

    public boolean validatePath(String input) {
        String regex = "^[a-zA-Z0-9_]*$";
        return !input.matches(regex);
    }
}
