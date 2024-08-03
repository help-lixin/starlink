package help.lixin.starlink.plugin.gitlab.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.gitlab.api.factory.GitlabPluginNamedContextFactory;
import help.lixin.starlink.plugin.gitlab.api.service.impl.GitlabServiceFactory;

@Configuration
public class GitlabPluginConfig {

    @Bean
    @ConditionalOnMissingBean(name = "gitlabServiceFactory")
    public AbstractServiceFactory gitlabServiceFactory(@Autowired @Qualifier("gitlabPluginNamedContextFactory") //
    PluginNamedContextFactory gitlabPluginNamedContextFactory) {
        return new GitlabServiceFactory(gitlabPluginNamedContextFactory);
    }

    @Bean
    @ConditionalOnMissingBean(name = "gitlabPluginNamedContextFactory")
    public PluginNamedContextFactory gitlabPluginNamedContextFactory() {
        return new GitlabPluginNamedContextFactory();
    }
}
