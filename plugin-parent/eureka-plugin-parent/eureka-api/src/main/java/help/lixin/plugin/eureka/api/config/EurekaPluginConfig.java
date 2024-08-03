package help.lixin.plugin.eureka.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.plugin.eureka.api.factory.EurekaPluginNamedContextFactory;

@Configuration
public class EurekaPluginConfig {
    @Bean
    public PluginNamedContextFactory eurekaPluginNamedContextFactory() {
        return new EurekaPluginNamedContextFactory();
    }
}
