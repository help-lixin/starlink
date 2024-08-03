package help.lixin.starlink.plugin.rocketmq.service.impl;

import java.util.List;
import java.util.Map;

import help.lixin.starlink.core.plugin.InstanceService;
import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.rocketmq.api.dto.topic.*;
import help.lixin.starlink.plugin.rocketmq.api.service.ITopicService;
import help.lixin.starlink.plugin.rocketmq.service.IRocketMQTopicService;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/23 10:49 上午
 * @Description
 */
public class RocketMQTopicServiceImpl extends InstanceService<ITopicService> implements IRocketMQTopicService {

    @Override
    public List<String> queryList(String instanceName) {
        return getApi(instanceName).queryList();
    }

    @Override
    public Map<String, TopicStatusMessageQueueDTO> queryStatus(String topicName, String instanceName) {
        return getApi(instanceName).queryStatus(topicName);
    }

    @Override
    public TopicRouteDTO queryRoute(String topicName, String instanceName) {
        return getApi(instanceName).queryRoute(topicName);
    }

    @Override
    public List<TopicInfoDTO> queryTopicInfo(String topicName, String instanceName) {
        return getApi(instanceName).queryTopicInfo(topicName);
    }

    @Override
    public Map<String, TopicConsumerDTO> queryConsumerByTopic(String topicName, String instanceName) {
        return getApi(instanceName).queryConsumerByTopic(topicName);
    }

    @Override
    public List<String> queryTopicConsumerList(String topicName, String instanceName) {
        return getApi(instanceName).queryTopicConsumerInfo(topicName);
    }

    @Override
    public Boolean saveOrUpdate(TopicInfoDTO topicInfoDTO, String instanceName) {
        return getApi(instanceName).saveOrUpdate(topicInfoDTO);
    }

    @Override
    public TopicMessageStatusDTO sendMessage(TopicSendMessageDTO topicSendMessageDTO, String instanceName) {
        return getApi(instanceName).sendMessage(topicSendMessageDTO);
    }

    @Override
    public Boolean deleteTopic(String topicName, String instanceName) {
        return getApi(instanceName).deleteTopic(topicName);
    }

    public RocketMQTopicServiceImpl(PluginNamedContextFactory pluginNamedContextFactory) {
        this.pluginNamedContextFactory = pluginNamedContextFactory;
    }
}
