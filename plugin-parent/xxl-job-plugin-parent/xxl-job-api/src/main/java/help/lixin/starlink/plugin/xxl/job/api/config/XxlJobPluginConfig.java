package help.lixin.starlink.plugin.xxl.job.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.xxl.job.api.factory.JobPluginNamedContextFactory;

@Configuration
public class XxlJobPluginConfig {
    @Bean
    public PluginNamedContextFactory jobPluginNamedContextFactory() {
        return new JobPluginNamedContextFactory();
    }
}
