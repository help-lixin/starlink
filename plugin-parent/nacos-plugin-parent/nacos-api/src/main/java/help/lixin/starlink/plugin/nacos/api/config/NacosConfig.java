package help.lixin.starlink.plugin.nacos.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import help.lixin.service.IExpressionService;
import help.lixin.starlink.plugin.nacos.api.properties.NacosProperties;
import help.lixin.starlink.plugin.nacos.api.service.*;
import help.lixin.starlink.plugin.nacos.api.service.impl.*;

public class NacosConfig {

    @Value("${plugin:default}")
    private String plugin;

    @Value("${instance:default}")
    private String instance;

    @Bean
    public NacosProperties nacosProperties(Environment environment) {
        String prefix = String.format("%s.%s", plugin, instance);
        NacosProperties nacosProperties = Binder.get(environment)//
            .bind(prefix, NacosProperties.class)//
            .get();
        return nacosProperties;
    }

    @Bean
    @ConditionalOnMissingBean(name = "nacosApiFaceService")
    public NacosApiFaceService nacosApiFaceService(IExpressionService expressionService, //
        INacosConfigService configService, //
        INacosInstanceService instanceService, //
        INacosNameSpaceService nameSpaceService, //
        INacosServiceManageService serviceManageService) {
        return new NacosApiFaceService(expressionService, configService, instanceService, nameSpaceService,
            serviceManageService);
    }

    @Bean
    @ConditionalOnMissingBean(name = "nacosConfigService")
    public INacosConfigService nacosConfigService(NacosProperties nacosProperties) {
        return new NacosConfigService(nacosProperties);
    }

    @Bean
    @ConditionalOnMissingBean(name = "nacosInstanceService")
    public INacosInstanceService nacosInstanceService(NacosProperties properties,
        INacosCacheService nacosCacheManager) {
        return new NacosInstanceService(properties, nacosCacheManager);
    }

    @Bean
    @ConditionalOnMissingBean(name = "nacosServiceManageService")
    public INacosServiceManageService nacosServiceManageService(NacosProperties properties) {
        return new NacosServiceManageService(properties);
    }

    @Bean
    @ConditionalOnMissingBean(name = "nacosNameSpaceService")
    public INacosNameSpaceService nacosNameSpaceService(NacosProperties properties) {
        return new NacosNameSpaceService(properties);
    }

    @Bean
    @ConditionalOnMissingBean(name = "nacosCacheService")
    public INacosCacheService nacosCacheService(NacosProperties properties) {
        return new NacosCacheService(properties.getExpiration());
    }

}
