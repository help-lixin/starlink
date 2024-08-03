package help.lixin.plugin.eureka.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import help.lixin.plugin.eureka.api.properties.EurekaProperties;
import help.lixin.plugin.eureka.api.service.EurekaFaceService;
import help.lixin.plugin.eureka.api.service.IEurekaCacheManager;
import help.lixin.plugin.eureka.api.service.IEurekaClient;
import help.lixin.plugin.eureka.api.service.impl.EurekaCacheManagerImpl;
import help.lixin.plugin.eureka.api.service.impl.EurekaClientImpl;

public class EurekaConfig {

    @Value("${plugin:default}")
    private String plugin;

    @Value("${instance:default}")
    private String instance;

    @Bean
    public EurekaProperties eurekaProperties(Environment environment) {
        String prefix = String.format("%s.%s", plugin, instance);
        EurekaProperties nacosProperties = Binder.get(environment)//
            .bind(prefix, EurekaProperties.class)//
            .get();
        return nacosProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public EurekaFaceService eurekaFaceService(IEurekaClient eurekaClient) {
        return new EurekaFaceService(eurekaClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public IEurekaClient eurekaClient(EurekaProperties eurekaProperties, IEurekaCacheManager eurekaCacheManager) {
        return new EurekaClientImpl(eurekaProperties, eurekaCacheManager);
    }

    @Bean
    @ConditionalOnMissingBean
    public IEurekaCacheManager eurekaCacheManager(EurekaProperties eurekaProperties) {
        return new EurekaCacheManagerImpl(eurekaProperties.getExpiration());
    }

}
