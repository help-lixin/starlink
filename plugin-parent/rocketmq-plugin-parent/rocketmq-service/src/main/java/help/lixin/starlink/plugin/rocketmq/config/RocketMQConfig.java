package help.lixin.starlink.plugin.rocketmq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.rocketmq.mapper.RocketmqBrokerInfoMapper;
import help.lixin.starlink.plugin.rocketmq.mapper.RocketmqClusterConfigMapper;
import help.lixin.starlink.plugin.rocketmq.mapper.RocketmqClusterInfoMapper;
import help.lixin.starlink.plugin.rocketmq.service.*;
import help.lixin.starlink.plugin.rocketmq.service.impl.*;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/18 4:01 下午
 * @Description
 */
@Configuration
public class RocketMQConfig {

    @Bean
    @ConditionalOnMissingBean
    public IRocketMQTopicService rocketMQTopicService(
        @Autowired @Qualifier("rocketMQPluginNamedContextFactory") PluginNamedContextFactory pluginNamedContextFactory) {
        return new RocketMQTopicServiceImpl(pluginNamedContextFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public IRocketMQClusterService rocketMQClusterService(
        @Autowired @Qualifier("rocketMQPluginNamedContextFactory") PluginNamedContextFactory pluginNamedContextFactory,
        RocketmqBrokerInfoMapper brokerInfoMapper, RocketmqClusterConfigMapper clusterConfigMapper,
        RocketmqClusterInfoMapper clusterInfoMapper, QueryTemplate queryTemplate) {
        return new RocketMQClusterServiceImpl(pluginNamedContextFactory, brokerInfoMapper, clusterConfigMapper,
            clusterInfoMapper, queryTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public IRocketMQMessageService rocketMQMessageService(
        @Autowired @Qualifier("rocketMQPluginNamedContextFactory") PluginNamedContextFactory pluginNamedContextFactory) {
        return new RocketMQMessageServiceImpl(pluginNamedContextFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public IRocketMQConsumerService rocketMQConsumerService(
        @Autowired @Qualifier("rocketMQPluginNamedContextFactory") PluginNamedContextFactory pluginNamedContextFactory) {
        return new RocketMQConsumerServiceImpl(pluginNamedContextFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public IRocketMQDashboardService rocketDashboardService(
        @Autowired @Qualifier("rocketMQPluginNamedContextFactory") PluginNamedContextFactory pluginNamedContextFactory) {
        return new RocketMQDashboardServiceImpl(pluginNamedContextFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public IRocketMQNameSvrAddrService rocketNameSvrAddrService(
        @Autowired @Qualifier("rocketMQPluginNamedContextFactory") PluginNamedContextFactory pluginNamedContextFactory) {
        return new RocketMQNameSvrAddrServiceImpl(pluginNamedContextFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public IRocketMQProducerService rocketMQProducerService(
        @Autowired @Qualifier("rocketMQPluginNamedContextFactory") PluginNamedContextFactory pluginNamedContextFactory) {
        return new RocketMQProducerServiceImpl(pluginNamedContextFactory);
    }

}
