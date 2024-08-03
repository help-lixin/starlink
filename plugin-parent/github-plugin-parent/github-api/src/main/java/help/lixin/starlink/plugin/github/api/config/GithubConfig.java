package help.lixin.starlink.plugin.github.api.config;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import help.lixin.starlink.core.credentials.AbstractCredentials;
import help.lixin.starlink.plugin.github.api.properties.GithubProperties;

public class GithubConfig {

    @Value("${plugin:default}")
    private String plugin;

    @Value("${instance:default}")
    private String instance;

    @Bean
    public AbstractCredentials githubProperties(Environment environment) {
        String prefix = String.format("%s.%s", plugin, instance);
        GithubProperties githubProperties = Binder.get(environment)//
            .bind(prefix, GithubProperties.class)//
            .get();
        return githubProperties;
    }

    @Bean
    public GitHub gitHub(AbstractCredentials githubProperties) throws Exception {
        GitHubBuilder gitHubBuilder = new GitHubBuilder();
        if (githubProperties instanceof GithubProperties) {
            GithubProperties githubPropertiesTemp = (GithubProperties)githubProperties;
            if (null != githubPropertiesTemp.getUrl()) {
                gitHubBuilder.withEndpoint(githubPropertiesTemp.getUrl());
            }
            gitHubBuilder.withJwtToken(githubPropertiesTemp.getToken());
        }
        GitHub gitHub = gitHubBuilder.build();
        if (!gitHub.isCredentialValid()) {
            String msg = String.format("初始化失败:[%s]", instance);
            throw new RuntimeException(msg);
        }
        return gitHub;
    }
}
