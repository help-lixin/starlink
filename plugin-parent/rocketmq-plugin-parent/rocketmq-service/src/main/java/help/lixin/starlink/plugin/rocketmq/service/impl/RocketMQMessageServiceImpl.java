package help.lixin.starlink.plugin.rocketmq.service.impl;

import help.lixin.starlink.core.plugin.InstanceService;
import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.rocketmq.api.dto.message.MessageInfoDTO;
import help.lixin.starlink.plugin.rocketmq.api.service.IMessageService;
import help.lixin.starlink.plugin.rocketmq.service.IRocketMQMessageService;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/28 5:14 下午
 * @Description
 */
public class RocketMQMessageServiceImpl extends InstanceService<IMessageService> implements IRocketMQMessageService {

    public RocketMQMessageServiceImpl(PluginNamedContextFactory pluginNamedContextFactory) {
        this.pluginNamedContextFactory = pluginNamedContextFactory;
    }

    @Override
    public List<MessageInfoDTO> queryByTopic(Long begin, Long end, String topic, String instanceName) {
        return getApi(instanceName).queryByTopic(begin,end,topic);
    }

    @Override
    public List<MessageInfoDTO> queryByTopicAndKey(String key, String topic, String instanceName) {
        return getApi(instanceName).queryByTopicAndKey(key,topic);
    }

    @Override
    public MessageInfoDTO queryByMessageId(String msgId, String topic, String instanceName) {
        return getApi(instanceName).queryByMessageId(msgId,topic);
    }
}
