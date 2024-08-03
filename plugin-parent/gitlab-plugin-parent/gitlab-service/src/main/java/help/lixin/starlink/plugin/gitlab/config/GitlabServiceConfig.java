package help.lixin.starlink.plugin.gitlab.config;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.service.IExpressionService;
import help.lixin.starlink.plugin.gitlab.mapper.*;
import help.lixin.starlink.plugin.gitlab.service.IGroupService;
import help.lixin.starlink.plugin.gitlab.service.IProjectService;
import help.lixin.starlink.plugin.gitlab.service.IRepositoryService;
import help.lixin.starlink.plugin.gitlab.service.IUserService;
import help.lixin.starlink.plugin.gitlab.service.impl.*;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/7 7:13 下午
 * @Description
 */

@Configuration
public class GitlabServiceConfig {

    // 业务 Service定义
    @Bean
    @ConditionalOnMissingBean
    public IUserService gitlabUserService(
        @Autowired @Qualifier("gitlabServiceFactory") AbstractServiceFactory gitlabServiceFactory,
        GitlabUserMapper userMapper, //
        StringEncryptor jasyptFactory, //
        QueryTemplate queryTemplate) {
        return new GitlabUserService(gitlabServiceFactory, userMapper, jasyptFactory, queryTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public IGroupService gitlabGroupService(
        @Autowired @Qualifier("gitlabServiceFactory") AbstractServiceFactory gitlabServiceFactory,
        GitlabGroupMapper groupMapper, GitlabUserGroupMapper userGroupMapper, GitlabUserMapper userMapper,
        QueryTemplate queryTemplate) {
        return new GitlabGroupService(groupMapper, userGroupMapper, userMapper, gitlabServiceFactory, queryTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public IRepositoryService gitlabRepositoryService(
        @Autowired @Qualifier("gitlabServiceFactory") AbstractServiceFactory gitlabServiceFactory) {
        return new GitlabRepositoryService(gitlabServiceFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public IProjectService gitlabProjectService(
        @Autowired @Qualifier("gitlabServiceFactory") AbstractServiceFactory gitlabServiceFactory,
        GitlabProjectMapper projectMapper, //
        GitlabUserProjectMapper userProjectMapper, //
        GitlabGroupProjectMapper groupProjectMapper, //
        GitlabUserMapper userMapper, //
        GitlabGroupMapper groupMapper, //
        QueryTemplate queryTemplate) {
        return new GitlabProjectService(gitlabServiceFactory, projectMapper, userProjectMapper, groupProjectMapper,
            userMapper, groupMapper, queryTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public GitlabFaceService gitlabFaceService(IExpressionService expressionService, //
        IGroupService gitlabGroupService, //
        IRepositoryService gitlabRepositoryService, //
        IProjectService gitlabProjectService, //
        IUserService gitlabUserService) {
        return new GitlabFaceService(expressionService, gitlabGroupService, gitlabProjectService,
            gitlabRepositoryService, gitlabUserService);
    }
}
