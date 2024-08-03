package help.lixin.starlink.plugin.gitlab.job.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.ProjectApi;
import org.gitlab4j.api.models.Namespace;
import org.gitlab4j.api.models.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.gitlab.domain.*;
import help.lixin.starlink.plugin.gitlab.mapper.*;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/26 9:50 下午
 * @Description 查询gitlab的project对比数据库的数据，不存在的插入到数据库中
 */
public class GitlabProjectJobService {

    private Logger logger = LoggerFactory.getLogger(GitlabProjectJobService.class);

    private GitlabProjectMapper projectMapper;
    private GitlabUserProjectMapper userProjectMapper;
    private GitlabGroupProjectMapper groupProjectMapper;
    private GitlabUserMapper userMapper;
    private GitlabGroupMapper groupMapper;

    private final AbstractServiceFactory gitlabServiceFactory;

    public void checkGitlabProjectList() {
        // 获取插件列表
        Set<String> contextNames = gitlabServiceFactory.getPluginNamedContextFactory().getContextNames();
        if (CollectionUtils.isEmpty(contextNames)) {
            return;
        }

        // 遍历插件列表
        contextNames.forEach(instanceName -> {
            ProjectApi projectApi = gitlabServiceFactory.getInstance(instanceName, ProjectApi.class);
            try {
                // 获取gitlab内部project的列表
                List<Project> projects = projectApi.getProjectsStream().collect(Collectors.toList());
                if (CollectionUtils.isEmpty(projects)) {
                    return;
                }
                // 转换成MAP<KEY:gitlabId,VALUE:project>集合，用户快速获取id对应的project数据
                Map<Long, Project> gitlabProjectIdAndProjectMap =
                    projectApi.getProjectsStream().collect(Collectors.toMap(Project::getId, v -> v));

                // 将gitlab的project列表信息转换成id的集合
                List<Long> gitlabProjectIds = projects.stream().map(Project::getId).collect(Collectors.toList());
                // user-project的集合
                List<Long> gitlabUserProjectIds =
                    projects.stream().filter(v -> v.getNamespace().getKind().equals("user")).map(Project::getId)
                        .collect(Collectors.toList());
                // group-project的集合
                List<Long> gitlabGroupProjectIds =
                    projects.stream().filter(v -> v.getNamespace().getKind().equals("group")).map(Project::getId)
                        .collect(Collectors.toList());

                // 根据上面的id列表在数据库中用 IN 查询
                List<GitlabProject> dbProjects = projectMapper.queryProjectByGitlabProjectIds(gitlabProjectIds);
                List<GitlabProject> dbUserProjects = projectMapper.queryUserProjectByProjectIds(gitlabUserProjectIds);
                List<GitlabProject> dbGroupProjects =
                    projectMapper.queryGroupProjectByProjectIds(gitlabGroupProjectIds);

                Map<Long, GitlabProject> dbGitlabProjectIdAndGitlabProject =
                    dbProjects.stream().collect(Collectors.toMap(v -> v.getGitlabProjectId(), v -> v));

                // 查询数据库结果数量 大于 插件中得出项目数量 的情况下进行插入
                if (gitlabProjectIds.size() > dbProjects.size()) {

                    // 将从数据库中获取的project列表信息转换成id列表
                    List<Long> dbProjectIds =
                        dbProjects.stream().map(GitlabProject::getGitlabProjectId).collect(Collectors.toList());
                    // 过滤出数据库中不存在的projectId
                    List<Long> collect =
                        gitlabProjectIds.stream().filter(v -> !dbProjectIds.contains(v)).collect(Collectors.toList());
                    if (CollectionUtils.isEmpty(collect)) {
                        return;
                    }

                    // 将数据库中不存在的project数据插入
                    for (Long gitlabProjectId : collect) {
                        // 根据上面gitlab转换的projectMap获取project数据
                        Project project = gitlabProjectIdAndProjectMap.get(gitlabProjectId);
                        if (project == null) {
                            throw new ServiceException("该项目不存在数据库中");
                        }

                        GitlabProject gitlabProject = new GitlabProject();
                        // 根据当前插件名称获取环境信息
                        gitlabProject.setInstanceCode(instanceName);
                        gitlabProject.setProjectName(project.getName());
                        gitlabProject.setGitlabProjectId(project.getId());
                        gitlabProject.setWebUrl(project.getHttpUrlToRepo());
                        gitlabProject.setSshUrl(project.getSshUrlToRepo());
                        gitlabProject.setPath(project.getPath());
                        gitlabProject.setVisibility(project.getVisibility().toValue());
                        gitlabProject.setInitiallizeWithReadme(project.getInitializeWithReadme());
                        gitlabProject.setRemark(project.getDescription());
                        // todo 这里有可能频繁连接数据库，探讨一下把所有sql语句串起来执行
                        projectMapper.insertSelective(gitlabProject);
                    }
                }

                // 查询数据库结果数量 大于 插件中得出项目数量 的情况下进行插入
                if (gitlabUserProjectIds.size() > dbUserProjects.size()) {
                    // 将从数据库中获取的project列表信息转换成id列表
                    List<Long> dbProjectIds =
                        dbUserProjects.stream().map(GitlabProject::getGitlabProjectId).collect(Collectors.toList());
                    // 过滤出数据库中不存在的projectId
                    List<Long> collect = gitlabUserProjectIds.stream().filter(v -> !dbProjectIds.contains(v))
                        .collect(Collectors.toList());
                    if (CollectionUtils.isEmpty(collect)) {
                        return;
                    }

                    List<Long> gitlabUserIds = projects.stream().filter(v -> v.getNamespace().getKind().equals("user"))
                        .map(Project::getNamespace).map(Namespace::getId).collect(Collectors.toList());

                    Map<Long, GitlabUser> gitlabUserIdAndUserMap = userMapper.queryUserByGitlabUserIds(gitlabUserIds)
                        .stream().collect(Collectors.toMap(v -> v.getGitlabUserId(), v -> v));

                    // 将数据库中不存在的project数据插入
                    for (Long gitlabProjectId : collect) {
                        // 根据上面转换的dbGitlabProjectIdAndGitlabProject获取库中project数据
                        GitlabProject gitlabProject = dbGitlabProjectIdAndGitlabProject.get(gitlabProjectId);
                        if (gitlabProject == null) {
                            throw new ServiceException("该项目不存在数据库中");
                        }

                        GitlabUserProject gitlabUserProject = new GitlabUserProject();

                        gitlabUserProject.setProjectId(gitlabProject.getId());
                        Project curProjectInfo = gitlabProjectIdAndProjectMap.get(gitlabProjectId);
                        if (curProjectInfo == null) {
                            throw new ServiceException("该项目不存在数据库中");
                        }

                        GitlabUser gitlabUser = gitlabUserIdAndUserMap.get(curProjectInfo.getNamespace().getId());
                        if (curProjectInfo == null) {
                            throw new ServiceException("该用户不存在数据库中");
                        }

                        gitlabUserProject.setUserId(gitlabUser.getId());
                        // todo 这里有可能频繁连接数据库，探讨一下把所有sql语句串起来执行
                        userProjectMapper.insert(gitlabUserProject);
                    }
                }

                // 查询数据库结果数量 大于 插件中得出项目数量 的情况下进行插入
                if (gitlabGroupProjectIds.size() > dbGroupProjects.size()) {
                    // 将从数据库中获取的project列表信息转换成id列表
                    List<Long> dbProjectIds =
                        dbGroupProjects.stream().map(GitlabProject::getGitlabProjectId).collect(Collectors.toList());
                    // 过滤出数据库中不存在的projectId
                    List<Long> collect = gitlabGroupProjectIds.stream().filter(v -> !dbProjectIds.contains(v))
                        .collect(Collectors.toList());
                    if (CollectionUtils.isEmpty(collect)) {
                        return;
                    }

                    List<Long> gitlabGroupIds =
                        projects.stream().filter(v -> v.getNamespace().getKind().equals("group"))
                            .map(Project::getNamespace).map(Namespace::getId).collect(Collectors.toList());

                    Map<Long, GitlabGroup> gitlabGroupIdAndGroupMap =
                        groupMapper.queryGroupByGitlabGroupIds(gitlabGroupIds).stream()
                            .collect(Collectors.toMap(v -> v.getGitlabGroupId(), v -> v));

                    // 将数据库中不存在的project数据插入
                    for (Long gitlabProjectId : collect) {
                        // 根据上面转换的dbGitlabProjectIdAndGitlabProject获取库中project数据
                        GitlabProject gitlabProject = dbGitlabProjectIdAndGitlabProject.get(gitlabProjectId);
                        if (gitlabProject == null) {
                            logger.error("该项目不存在数据库中");
                            continue;
                        }

                        GitlabGroupProject gitlabGroupProject = new GitlabGroupProject();

                        gitlabGroupProject.setProjectId(gitlabProject.getId());

                        Project curProjectInfo = gitlabProjectIdAndProjectMap.get(gitlabProjectId);
                        if (curProjectInfo == null) {
                            logger.error("该项目不存在gitlab中[gitlabProjectId:{}]", gitlabProjectId);
                            continue;
                        }

                        GitlabGroup gitlabGroup = gitlabGroupIdAndGroupMap.get(curProjectInfo.getNamespace().getId());
                        if (gitlabGroup == null) {
                            logger.error("该组不存在数据库中[gitlabGroupId:{}]", curProjectInfo.getNamespace().getId());
                            continue;
                        }

                        gitlabGroupProject.setGroupId(gitlabGroup.getId());
                        // todo 这里有可能频繁连接数据库，探讨一下把所有sql语句串起来执行
                        groupProjectMapper.insertSelective(gitlabGroupProject);
                    }
                }
            } catch (GitLabApiException e) {
                e.printStackTrace();
                throw new ServiceException("定时任务发生异常:" + e.getMessage());
            }

        });

    }

    public GitlabProjectJobService(AbstractServiceFactory gitlabServiceFactory, GitlabProjectMapper projectMapper,
        GitlabUserMapper userMapper, GitlabGroupMapper groupMapper, GitlabUserProjectMapper userProjectMapper,
        GitlabGroupProjectMapper groupProjectMapper) {
        this.projectMapper = projectMapper;
        this.userMapper = userMapper;
        this.groupMapper = groupMapper;
        this.userProjectMapper = userProjectMapper;
        this.groupProjectMapper = groupProjectMapper;
        this.gitlabServiceFactory = gitlabServiceFactory;
    }
}
