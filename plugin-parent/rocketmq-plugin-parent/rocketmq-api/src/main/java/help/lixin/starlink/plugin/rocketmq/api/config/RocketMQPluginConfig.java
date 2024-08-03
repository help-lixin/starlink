package help.lixin.starlink.plugin.rocketmq.api.config;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.rocketmq.api.factory.RocketMQPluginNamedContextFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketMQPluginConfig {
    @Bean
    public PluginNamedContextFactory rocketMQPluginNamedContextFactory() {
        return new RocketMQPluginNamedContextFactory();
    }
}
