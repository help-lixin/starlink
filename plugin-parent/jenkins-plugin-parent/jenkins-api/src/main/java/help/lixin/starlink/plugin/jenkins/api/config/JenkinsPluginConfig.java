package help.lixin.starlink.plugin.jenkins.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.jenkins.api.factory.JenkinsPluginNamedContextFactory;
import help.lixin.starlink.plugin.jenkins.api.service.impl.JenkinsServiceFactory;

@Configuration
public class JenkinsPluginConfig {

    @Bean
    @ConditionalOnMissingBean(name = "jenkinsServiceFactory")
    public AbstractServiceFactory jenkinsServiceFactory(@Autowired @Qualifier("jenkinsPluginNamedContextFactory") //
    PluginNamedContextFactory jenkinsPluginNamedContextFactory) {
        return new JenkinsServiceFactory(jenkinsPluginNamedContextFactory);
    }

    @Bean
    @ConditionalOnMissingBean(name = "jenkinsPluginNamedContextFactory")
    public PluginNamedContextFactory jenkinsPluginNamedContextFactory() {
        return new JenkinsPluginNamedContextFactory();
    }
}
