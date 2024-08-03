package help.lixin.starlink.plugin.harbor.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.harbor.api.factory.HarborPluginNamedContextFactory;
import help.lixin.starlink.plugin.harbor.api.service.impl.HarborServiceFactory;

@Configuration
public class HarborPluginConfig {

    @Bean
    @ConditionalOnMissingBean(name = "harborServiceFactory")
    public AbstractServiceFactory harborServiceFactory(@Autowired @Qualifier("harborPluginNamedContextFactory") //
    PluginNamedContextFactory harborPluginNamedContextFactory) {
        return new HarborServiceFactory(harborPluginNamedContextFactory);
    }

    @Bean
    @ConditionalOnMissingBean(name = "harborPluginNamedContextFactory")
    public PluginNamedContextFactory harborPluginNamedContextFactory() {
        return new HarborPluginNamedContextFactory();
    }
}
