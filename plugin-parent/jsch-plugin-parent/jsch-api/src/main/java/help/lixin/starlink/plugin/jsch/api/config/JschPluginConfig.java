package help.lixin.starlink.plugin.jsch.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.jsch.api.factory.JschPluginNamedContextFactory;
import help.lixin.starlink.plugin.jsch.api.service.impl.JschServiceFactory;

@Configuration
public class JschPluginConfig {

    @Bean
    @ConditionalOnMissingBean(name = "jschServiceFactory")
    public AbstractServiceFactory jschServiceFactory(@Autowired @Qualifier("jschPluginNamedContextFactory") //
    PluginNamedContextFactory jschPluginNamedContextFactory) {
        return new JschServiceFactory(jschPluginNamedContextFactory);
    }

    @Bean
    @ConditionalOnMissingBean(name = "jschPluginNamedContextFactory")
    public PluginNamedContextFactory jschPluginNamedContextFactory() {
        return new JschPluginNamedContextFactory();
    }
}
