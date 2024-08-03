package help.lixin.starlink.plugin.rocketmq.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import help.lixin.starlink.plugin.rocketmq.api.properties.RocketMQProperties;
import help.lixin.starlink.plugin.rocketmq.api.service.*;
import help.lixin.starlink.plugin.rocketmq.api.service.impl.*;

public class RocketMQConfig {
    @Value("${plugin:default}")
    private String plugin;

    @Value("${instance:default}")
    private String instance;

    @Bean
    public RocketMQProperties rocketMQProperties(Environment environment) {
        String prefix = String.format("%s.%s", plugin, instance);
        RocketMQProperties rocketMQProperties = Binder.get(environment)//
            .bind(prefix, RocketMQProperties.class)//
            .get();
        return rocketMQProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public ITopicService topicService(RocketMQProperties rocketMQProperties) {
        return new TopicServiceImpl(rocketMQProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public IClusterService clusterService(RocketMQProperties rocketMQProperties) {
        return new ClusterServiceImpl(rocketMQProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public IMessageService messageService(RocketMQProperties rocketMQProperties) {
        return new MessageServiceImpl(rocketMQProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public IConsumerService consumerService(RocketMQProperties rocketMQProperties) {
        return new ConsumerServiceImpl(rocketMQProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public INameSvrAddrService nameSvrAddrService(RocketMQProperties rocketMQProperties) {
        return new NameSvrAddrServiceImpl(rocketMQProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public IDashboardService dashboardService(RocketMQProperties rocketMQProperties) {
        return new DashboardServiceImpl(rocketMQProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public IProducerService producerService(RocketMQProperties rocketMQProperties) {
        return new ProducerServiceImpl(rocketMQProperties);
    }

}
