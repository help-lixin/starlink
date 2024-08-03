package help.lixin.starlink.plugin.k8s.config;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.k8s.factory.K8SPluginNamedContextFactory;
import help.lixin.starlink.plugin.k8s.service.impl.K8SServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class K8SPluginConfig {

    @Bean
    @ConditionalOnMissingBean(name = "k8SServiceFactory")
    public AbstractServiceFactory k8SServiceFactory(@Autowired @Qualifier("k8SPluginNamedContextFactory") //
                                                    PluginNamedContextFactory k8SPluginNamedContextFactory) {
        return new K8SServiceFactory(k8SPluginNamedContextFactory);
    }

    @Bean
    @ConditionalOnMissingBean(name = "k8SPluginNamedContextFactory")
    public PluginNamedContextFactory k8SPluginNamedContextFactory() {
        return new K8SPluginNamedContextFactory();
    }
}
