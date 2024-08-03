package help.lixin.starlink.plugin.gitlab.job.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.GroupApi;
import org.gitlab4j.api.models.Group;
import org.springframework.util.CollectionUtils;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.gitlab.domain.GitlabGroup;
import help.lixin.starlink.plugin.gitlab.mapper.GitlabGroupMapper;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/26 9:50 下午
 * @Description 查询gitlab的Group对比数据库的数据，不存在的插入到数据库中
 */
public class GitlabGroupJobService {

    private GitlabGroupMapper groupMapper;

    private final AbstractServiceFactory gitlabServiceFactory;

    public void checkGitlabGroupList() {
        // 获取插件列表
        Set<String> contextNames = gitlabServiceFactory.getPluginNamedContextFactory().getContextNames();
        if (CollectionUtils.isEmpty(contextNames)) {
            return;
        }

        // 遍历插件列表
        contextNames.forEach(instanceName -> {
            GroupApi groupApi = gitlabServiceFactory.getInstance(instanceName, GroupApi.class);
            try {
                // 获取gitlab内部group的列表
                List<Group> groups = groupApi.getGroupsStream().collect(Collectors.toList());
                // 转换成MAP<KEY:gitlabId,VALUE:group>集合，用户快速获取id对应的group数据
                Map<Long, Group> gitlabGroupIdAndGroupMap =
                    groupApi.getGroupsStream().collect(Collectors.toMap(Group::getId, v -> v));

                // 将gitlab的group列表信息转换成id的集合
                List<Long> gitlabGroupIds = groups.stream().map(Group::getId).collect(Collectors.toList());
                // 根据上面的id列表在数据库中用 IN 查询
                List<GitlabGroup> dbGroups = groupMapper.queryGroupByGitlabGroupIds(gitlabGroupIds);

                if (gitlabGroupIds.size() == dbGroups.size()) {
                    return;
                }

                // 将从数据库中获取的group列表信息转换成id列表
                List<Long> dbGroupIds =
                    dbGroups.stream().map(GitlabGroup::getGitlabGroupId).collect(Collectors.toList());
                // 过滤出数据库中不存在的groupId
                List<Long> collect =
                    gitlabGroupIds.stream().filter(v -> !dbGroupIds.contains(v)).collect(Collectors.toList());
                if (CollectionUtils.isEmpty(collect)) {
                    return;
                }

                // 将数据库中不存在的group数据插入
                for (Long gitlabGroupId : collect) {
                    // 根据上面gitlab转换的groupMap获取group数据
                    Group group = gitlabGroupIdAndGroupMap.get(gitlabGroupId);

                    GitlabGroup gitlabGroup = new GitlabGroup();
                    // 根据当前插件名称获取环境信息
                    // EnvDTO env = PluginUtils.envParse(instanceName);
                    // if (env == null){
                    // throw new ServiceException("插件名称格式异常");
                    // }

                    gitlabGroup.setPath(group.getPath());
                    gitlabGroup.setInstanceCode(instanceName);
                    gitlabGroup.setGitlabGroupName(group.getName());
                    gitlabGroup.setGitlabGroupId(group.getId());
                    // todo 这里有可能频繁连接数据库，探讨一下把所有sql语句串起来执行
                    groupMapper.insert(gitlabGroup);
                }
            } catch (GitLabApiException e) {
                e.printStackTrace();
                throw new ServiceException("定时任务发生异常");
            }

        });

    }

    public GitlabGroupJobService(AbstractServiceFactory gitlabServiceFactory, GitlabGroupMapper groupMapper) {
        this.gitlabServiceFactory = gitlabServiceFactory;
        this.groupMapper = groupMapper;
    }
}
