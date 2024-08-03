package help.lixin.starlink.plugin.km.api.config;

import help.lixin.starlink.plugin.km.api.mediator.KmCookieMediator;
import help.lixin.starlink.plugin.km.api.properties.KmProperties;
import help.lixin.starlink.plugin.km.api.service.IKmKafkaConnectorStateService;
import help.lixin.starlink.plugin.km.api.service.IKmLoginAPIService;
import help.lixin.starlink.plugin.km.api.service.KmFaceService;
import help.lixin.starlink.plugin.km.api.service.impl.KmKafkaConnectorStateServiceImpl;
import help.lixin.starlink.plugin.km.api.service.impl.KmLoginAPIServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class KmConfig {
    @Value("${plugin:default}")
    private String plugin;

    @Value("${instance:default}")
    private String instance;

    @Bean
    public KmProperties kmProperties(Environment environment) {
        String prefix = String.format("%s.%s", plugin, instance);
        KmProperties kmProperties = Binder.get(environment)//
                .bind(prefix, KmProperties.class)//
                .get();
        return kmProperties;
    }


    @Bean
    @ConditionalOnMissingBean
    public KmFaceService kmFaceService(IKmLoginAPIService kmLoginAPIService) {
        return new KmFaceService(kmLoginAPIService);
    }


    @Bean
    @ConditionalOnMissingBean
    public IKmLoginAPIService kmLoginAPIService(KmProperties kmProperties, KmCookieMediator kmCookieMediator){
        return new KmLoginAPIServiceImpl(kmProperties, kmCookieMediator);
    }

    @Bean
    @ConditionalOnMissingBean
    public KmCookieMediator kmCookieMediator(){
        return new KmCookieMediator();
    }

    @Bean
    @ConditionalOnMissingBean
    public IKmKafkaConnectorStateService kmKafkaConnectorStateService(KmProperties kmProperties, KmLoginAPIServiceImpl kmLoginAPIService){
        return new KmKafkaConnectorStateServiceImpl(kmProperties, kmLoginAPIService);
    }
}
