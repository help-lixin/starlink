package help.lixin.gitlab.config;

import help.lixin.core.pipeline.service.IExpressionService;
import help.lixin.gitlab.properties.GitlabProperties;
import help.lixin.gitlab.service.*;
import help.lixin.gitlab.service.impl.GroupService;
import help.lixin.gitlab.service.impl.ProjectService;
import help.lixin.gitlab.service.impl.RepositoryService;
import help.lixin.gitlab.service.impl.UserService;
import org.gitlab4j.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(GitlabProperties.class)
public class GitlabConfig {

    @Bean
    @ConditionalOnMissingBean
    public GitLabApi gitLabApi(GitlabProperties gitlabProperties,
                               //
                               @Autowired(required = false) IGitLabApiCustomizer gitLabApiCustomizer) {
        GitLabApi gitLabApi = new GitLabApi(gitlabProperties.getUrl(), gitlabProperties.getToken());
        if (null != gitLabApiCustomizer) {
            gitLabApiCustomizer.customizer(gitlabProperties, gitLabApi);
        }
        return gitLabApi;
    }

    @Bean
    @ConditionalOnMissingBean
    public UserApi userApi(GitLabApi gitLabApi) {
        return gitLabApi.getUserApi();
    }

    @Bean
    @ConditionalOnMissingBean
    public GroupApi groupApi(GitLabApi gitLabApi) {
        return gitLabApi.getGroupApi();
    }

    @Bean
    @ConditionalOnMissingBean
    public ProjectApi projectApi(GitLabApi gitLabApi) {
        return gitLabApi.getProjectApi();
    }

    @Bean
    @ConditionalOnMissingBean
    public RepositoryApi gitlabRepositoryApi(GitLabApi gitLabApi) {
        return gitLabApi.getRepositoryApi();
    }

    // 业务 Service定义
    @Bean
    @ConditionalOnMissingBean
    public IUserService gitlabUserService(UserApi userApi) {
        return new UserService(userApi);
    }

    @Bean
    @ConditionalOnMissingBean
    public IGroupService gitlabGroupService(GroupApi groupApi) {
        return new GroupService(groupApi);
    }

    @Bean
    @ConditionalOnMissingBean
    public IRepositoryService gitlabRepositoryService(RepositoryApi repositoryApi) {
        return new RepositoryService(repositoryApi);
    }

    @Bean
    @ConditionalOnMissingBean
    public IProjectService gitlabProjectService(ProjectApi projectApi) {
        return new ProjectService(projectApi);
    }

    @Bean
    @ConditionalOnMissingBean
    public GitlabFaceService gitlabFaceService(IExpressionService expressionService,
                                               //
                                               IGroupService gitlabGroupService,
                                               //
                                               IRepositoryService gitlabRepositoryService,
                                               //
                                               IProjectService gitlabProjectService,
                                               //
                                               IUserService gitlabUserService) {
        return new GitlabFaceService(expressionService, gitlabGroupService, gitlabProjectService, gitlabRepositoryService, gitlabUserService);
    }
}
