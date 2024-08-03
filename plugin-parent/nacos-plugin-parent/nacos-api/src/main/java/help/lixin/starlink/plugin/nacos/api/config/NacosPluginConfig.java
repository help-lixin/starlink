package help.lixin.starlink.plugin.nacos.api.config;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.nacos.api.factory.NacosPluginNamedContextFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NacosPluginConfig {
    @Bean
    public PluginNamedContextFactory nacosPluginNamedContextFactory() {
        return new NacosPluginNamedContextFactory();
    }
}
