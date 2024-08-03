package help.lixin.starlink.plugin.svn.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.svn.api.factory.SvnPluginNamedContextFactory;
import help.lixin.starlink.plugin.svn.api.service.impl.SvnServiceFactory;

@Configuration
public class SvnPluginConfig {

    @Bean
    @ConditionalOnMissingBean(name = "svnServiceFactory")
    public AbstractServiceFactory svnServiceFactory(@Autowired @Qualifier("svnPluginNamedContextFactory") //
    PluginNamedContextFactory svnPluginNamedContextFactory) {
        return new SvnServiceFactory(svnPluginNamedContextFactory);
    }

    @Bean
    @ConditionalOnMissingBean(name = "svnPluginNamedContextFactory")
    public PluginNamedContextFactory svnPluginNamedContextFactory() {
        return new SvnPluginNamedContextFactory();
    }
}
