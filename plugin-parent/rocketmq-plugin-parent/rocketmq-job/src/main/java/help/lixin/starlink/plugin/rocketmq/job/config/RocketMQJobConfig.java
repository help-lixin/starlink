package help.lixin.starlink.plugin.rocketmq.job.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.rocketmq.job.RocketMQClusterJob;
import help.lixin.starlink.plugin.rocketmq.job.RocketMQTopicJob;
import help.lixin.starlink.plugin.rocketmq.job.service.RocketMQClusterJobService;
import help.lixin.starlink.plugin.rocketmq.job.service.RocketMQTopicJobService;
import help.lixin.starlink.plugin.rocketmq.mapper.*;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/29 12:14 下午
 * @Description
 */
@Configuration
public class RocketMQJobConfig {

    @Bean
    public RocketMQTopicJob rocketMQTopicJob(RocketMQTopicJobService rocketMQTopicJobService) {
        return new RocketMQTopicJob(rocketMQTopicJobService);
    }

    @Bean
    public RocketMQClusterJob rocketMQClusterJob(RocketMQClusterJobService rocketMQClusterJobService, //
        RocketMQTopicJobService rocketMQTopicJobService) {
        return new RocketMQClusterJob(rocketMQClusterJobService, rocketMQTopicJobService);
    }

    @Bean
    public RocketMQTopicJobService rocketMQTopicJobService(
        @Autowired @Qualifier("rocketMQPluginNamedContextFactory") PluginNamedContextFactory pluginNamedContextFactory, //
        RocketmqTopicMapper topicMapper, //
        RocketmqTopicRouteMapper topicRouteMapper, //
        RocketmqTopicStateMapper topicStateMapper, //
        RocketmqTopicSubGroupMapper topicSubGroupMapper, //
        RocketmqBrokerInfoMapper rocketmqBrokerInfoMapper) {
        return new RocketMQTopicJobService(pluginNamedContextFactory, topicMapper, topicRouteMapper, topicStateMapper,
            topicSubGroupMapper, rocketmqBrokerInfoMapper);
    }

    @Bean
    public RocketMQClusterJobService rocketMQClusterJobService(
        @Autowired @Qualifier("rocketMQPluginNamedContextFactory") PluginNamedContextFactory pluginNamedContextFactory, //
        RocketmqClusterInfoMapper clusterInfoMapper, //
        RocketmqBrokerInfoMapper brokerInfoMapper, //
        RocketmqClusterConfigMapper clusterConfigMapper) {
        return new RocketMQClusterJobService(pluginNamedContextFactory, clusterInfoMapper, brokerInfoMapper,
            clusterConfigMapper);
    }

}
