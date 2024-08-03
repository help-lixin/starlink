package help.lixin.starlink.plugin.gitlab.api.config;

import org.gitlab4j.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import help.lixin.starlink.core.credentials.AbstractCredentials;
import help.lixin.starlink.plugin.gitlab.api.properties.GitlabProperties;
import help.lixin.starlink.plugin.gitlab.api.service.IGitLabApiCustomizer;

public class GitlabConfig {

    @Value("${plugin:default}")
    private String plugin;

    @Value("${instance:default}")
    private String instance;

    @Bean
    public AbstractCredentials gitlabProperties(Environment environment) {
        String prefix = String.format("%s.%s", plugin, instance);
        GitlabProperties gitlabProperties = Binder.get(environment)//
            .bind(prefix, GitlabProperties.class)//
            .get();
        return gitlabProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public GitLabApi gitLabApi(GitlabProperties gitlabProperties,
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
    public RepositoryApi repositoryApi(GitLabApi gitLabApi) {
        return gitLabApi.getRepositoryApi();
    }

    @Bean
    @ConditionalOnMissingBean
    public ProjectApi projectApi(GitLabApi gitLabApi) {
        return gitLabApi.getProjectApi();
    }

}
