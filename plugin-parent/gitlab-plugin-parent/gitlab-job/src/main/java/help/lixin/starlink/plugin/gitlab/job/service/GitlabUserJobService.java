package help.lixin.starlink.plugin.gitlab.job.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.UserApi;
import org.gitlab4j.api.models.User;
import org.springframework.util.CollectionUtils;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.gitlab.domain.GitlabUser;
import help.lixin.starlink.plugin.gitlab.mapper.GitlabUserMapper;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/26 9:50 下午
 * @Description 查询gitlab的project对比数据库的数据，不存在的插入到数据库中
 */
public class GitlabUserJobService {

    private GitlabUserMapper userMapper;

    private final AbstractServiceFactory gitlabServiceFactory;

    public void checkGitlabUserList() {
        // 获取插件列表
        Set<String> contextNames = gitlabServiceFactory.getPluginNamedContextFactory().getContextNames();
        if (CollectionUtils.isEmpty(contextNames)) {
            return;
        }

        // 遍历插件列表
        contextNames.forEach(instanceName -> {
            UserApi userApi = gitlabServiceFactory.getInstance(instanceName, UserApi.class);
            try {
                // 获取gitlab内部user的列表
                List<User> users = userApi.getUsersStream().collect(Collectors.toList());
                // 转换成MAP<KEY:gitlabId,VALUE:user>集合，用户快速获取id对应的user数据
                Map<Long, User> gitlabUserIdAndUserMap =
                    userApi.getUsersStream().collect(Collectors.toMap(User::getId, v -> v));

                // 将gitlab的user列表信息转换成id的集合
                List<Long> gitlabUserIds = users.stream().map(User::getId).collect(Collectors.toList());
                // 根据上面的id列表在数据库中用 IN 查询
                List<GitlabUser> dbUsers = userMapper.queryUserByGitlabUserIds(gitlabUserIds);

                if (gitlabUserIds.size() == dbUsers.size()) {
                    return;
                }

                // 将从数据库中获取的user列表信息转换成id列表
                List<Long> dbUserIds = dbUsers.stream().map(GitlabUser::getGitlabUserId).collect(Collectors.toList());
                // 过滤出数据库中不存在的userId
                List<Long> collect =
                    gitlabUserIds.stream().filter(v -> !dbUserIds.contains(v)).collect(Collectors.toList());
                if (CollectionUtils.isEmpty(collect)) {
                    return;
                }

                // 将数据库中不存在的user数据插入
                for (Long gitlabUserId : collect) {
                    // 根据上面gitlab转换的userMap获取user数据
                    User user = gitlabUserIdAndUserMap.get(gitlabUserId);

                    GitlabUser gitlabUser = new GitlabUser();
                    // 根据当前插件名称获取环境信息
                    // EnvDTO env = PluginUtils.envParse(instanceName);
                    // if (env == null){
                    // throw new ServiceException("插件名称格式异常");
                    // }

                    gitlabUser.setUserName(user.getName());
                    gitlabUser.setGitlabUserId(user.getId());
                    gitlabUser.setNickName(user.getName());
                    gitlabUser.setEmail(user.getEmail());
                    userMapper.insert(gitlabUser);
                }
            } catch (GitLabApiException e) {
                e.printStackTrace();
                throw new ServiceException("定时任务发生异常");
            }

        });

    }

    public GitlabUserJobService(AbstractServiceFactory gitlabServiceFactory, GitlabUserMapper userMapper) {
        this.gitlabServiceFactory = gitlabServiceFactory;
        this.userMapper = userMapper;
    }
}
