package help.lixin.starlink.plugin.github.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.github.api.factory.GithubPluginNamedContextFactory;
import help.lixin.starlink.plugin.github.api.service.impl.GithubServiceFactory;

@Configuration
public class GithubPluginConfig {

    @Bean
    @ConditionalOnMissingBean(name = "githubServiceFactory")
    public AbstractServiceFactory githubServiceFactory(@Autowired @Qualifier("githubPluginNamedContextFactory") //
    PluginNamedContextFactory githubPluginNamedContextFactory) {
        return new GithubServiceFactory(githubPluginNamedContextFactory);
    }

    @Bean
    @ConditionalOnMissingBean(name = "githubPluginNamedContextFactory")
    public PluginNamedContextFactory githubPluginNamedContextFactory() {
        return new GithubPluginNamedContextFactory();
    }
}
