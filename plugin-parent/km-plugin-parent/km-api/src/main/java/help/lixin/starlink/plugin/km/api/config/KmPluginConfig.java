package help.lixin.starlink.plugin.km.api.config;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.km.api.factory.KmPluginNamedContextFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KmPluginConfig {
    @Bean
    public PluginNamedContextFactory kmPluginNamedContextFactory() {
        return new KmPluginNamedContextFactory();
    }
}
