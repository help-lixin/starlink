package help.lixin.starlink.plugin.gitlab.job.config;

import help.lixin.starlink.plugin.gitlab.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.gitlab.job.GitlabGroupJob;
import help.lixin.starlink.plugin.gitlab.job.GitlabProjectJob;
import help.lixin.starlink.plugin.gitlab.job.GitlabUserJob;
import help.lixin.starlink.plugin.gitlab.job.service.GitlabGroupJobService;
import help.lixin.starlink.plugin.gitlab.job.service.GitlabProjectJobService;
import help.lixin.starlink.plugin.gitlab.job.service.GitlabUserJobService;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/29 12:14 下午
 * @Description
 */
@Configuration
public class GitlabJobConfig {

    @Bean
    public GitlabProjectJob gitlabProjectJob(GitlabProjectJobService gitlabProjectJobService) {
        return new GitlabProjectJob(gitlabProjectJobService);
    }

    @Bean
    public GitlabProjectJobService gitlabProjectJobService(
            @Autowired @Qualifier("gitlabServiceFactory") AbstractServiceFactory gitlabServiceFactory,
            GitlabProjectMapper projectMapper, GitlabUserMapper userMapper, GitlabGroupMapper groupMapper,
            GitlabUserProjectMapper userProjectMapper, GitlabGroupProjectMapper groupProjectMapper) {
        return new GitlabProjectJobService(gitlabServiceFactory, projectMapper, userMapper, groupMapper,
            userProjectMapper, groupProjectMapper);
    }

    @Bean
    public GitlabGroupJob gitlabGroupJob(GitlabGroupJobService gitlabGroupJobService) {
        return new GitlabGroupJob(gitlabGroupJobService);
    }

    @Bean
    public GitlabGroupJobService gitlabGroupJobService(
        @Autowired @Qualifier("gitlabServiceFactory") AbstractServiceFactory gitlabServiceFactory,
        GitlabGroupMapper gitlabGroupMapper) {
        return new GitlabGroupJobService(gitlabServiceFactory, gitlabGroupMapper);
    }

    @Bean
    public GitlabUserJob gitlabUserJob(GitlabUserJobService gitlabUserJobService) {
        return new GitlabUserJob(gitlabUserJobService);
    }

    @Bean
    public GitlabUserJobService gitlabUserJobService(
        @Autowired @Qualifier("gitlabServiceFactory") AbstractServiceFactory gitlabServiceFactory,
        GitlabUserMapper gitlabUserMapper) {
        return new GitlabUserJobService(gitlabServiceFactory, gitlabUserMapper);
    }
}
