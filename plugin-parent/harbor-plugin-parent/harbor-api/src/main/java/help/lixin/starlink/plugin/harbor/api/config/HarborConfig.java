package help.lixin.starlink.plugin.harbor.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import help.lixin.starlink.plugin.harbor.api.properties.HarborProperties;
import help.lixin.starlink.plugin.harbor.api.service.HarborFaceService;
import help.lixin.starlink.plugin.harbor.api.service.IHarborProjectApi;
import help.lixin.starlink.plugin.harbor.api.service.impl.HarborLoginfoApi;
import help.lixin.starlink.plugin.harbor.api.service.impl.HarborProjectApi;

public class HarborConfig {

    @Value("${plugin:default}")
    private String plugin;

    @Value("${instance:default}")
    private String instance;

    @Bean
    public HarborProperties harborProperties(Environment environment) {
        String prefix = String.format("%s.%s", plugin, instance);
        HarborProperties harborProperties = Binder.get(environment)//
            .bind(prefix, HarborProperties.class)//
            .get();
        return harborProperties;
    }

    @Bean
    @ConditionalOnMissingBean(name = "harborFaceService")
    public HarborFaceService harborFaceService(HarborProperties harborProperties, //
        IHarborProjectApi harborProjectService) {
        return new HarborFaceService(harborProperties, harborProjectService);
    }

    @Bean
    @ConditionalOnMissingBean(name = "harborProjectApiharborProjectApi")
    public HarborProjectApi harborProjectApi(HarborProperties properties) {
        return new HarborProjectApi(properties);
    }

    @Bean
    @ConditionalOnMissingBean(name = "harborLoginfoApi")
    public HarborLoginfoApi harborLoginfoApi(HarborProperties properties) {
        return new HarborLoginfoApi(properties);
    }
}
