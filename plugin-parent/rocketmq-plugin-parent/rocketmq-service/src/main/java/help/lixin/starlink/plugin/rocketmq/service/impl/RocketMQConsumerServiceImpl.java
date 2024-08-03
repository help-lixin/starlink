package help.lixin.starlink.plugin.rocketmq.service.impl;

import java.util.List;
import java.util.Map;

import help.lixin.starlink.core.plugin.InstanceService;
import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.rocketmq.api.dto.consumer.*;
import help.lixin.starlink.plugin.rocketmq.api.dto.topic.TopicRollBackInfoDTO;
import help.lixin.starlink.plugin.rocketmq.api.service.IConsumerService;
import help.lixin.starlink.plugin.rocketmq.service.IRocketMQConsumerService;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/31 4:13 下午
 * @Description
 */
public class RocketMQConsumerServiceImpl extends InstanceService<IConsumerService> implements IRocketMQConsumerService {
    public RocketMQConsumerServiceImpl(PluginNamedContextFactory pluginNamedContextFactory) {
        this.pluginNamedContextFactory = pluginNamedContextFactory;
    }

    @Override
    public List<ConsumerListDTO> groupList(String instanceName) {
        return getApi(instanceName).groupList();
    }

    @Override
    public ConsumerConnectionInfoDTO consumerConnection(String consumerGroup, String instanceName) {
        return getApi(instanceName).consumerConnection(consumerGroup);
    }

    @Override
    public List<ConsumerOffsetInfoDTO> queryTopicByConsumer(String consumerGroup, String instanceName) {
        return getApi(instanceName).queryTopicByConsumer(consumerGroup);
    }

    @Override
    public List<ConsumerSubscriptionGroupDataDTO> examineSubscriptionGroupConfig(String consumerGroup,
        String instanceName) {
        return getApi(instanceName).examineSubscriptionGroupConfig(consumerGroup);
    }

    @Override
    public Boolean createOrUpdate(ConsumerSubscriptionGroupInfoDTO consumerSubscriptionGroupInfoDTO,
        String instanceName) {
        return getApi(instanceName).createOrUpdate(consumerSubscriptionGroupInfoDTO);
    }

    @Override
    public List<String> fetchBrokerNameList(String consumerGroup, String instanceName) {
        return getApi(instanceName).fetchBrokerNameList(consumerGroup);
    }

    @Override
    public Boolean deleteSubGroup(ConsumerDeleteDTO consumerDeleteDTO, String instanceName) {
        return getApi(instanceName).deleteSubGroup(consumerDeleteDTO);
    }

    @Override
    public Map<String, List<TopicRollBackInfoDTO>> resetOffset(String topicName, String instanceName) {
        return getApi(instanceName).resetOffset(topicName);
    }
}
