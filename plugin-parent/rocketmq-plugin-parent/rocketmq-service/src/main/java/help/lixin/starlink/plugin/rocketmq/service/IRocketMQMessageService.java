package help.lixin.starlink.plugin.rocketmq.service;

import java.util.List;

import help.lixin.starlink.plugin.rocketmq.api.dto.message.MessageInfoDTO;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/28 5:13 下午
 * @Description
 */
public interface IRocketMQMessageService {

    List<MessageInfoDTO> queryByTopic(Long begin, Long end, String topic, String instanceName);

    List<MessageInfoDTO> queryByTopicAndKey(String key, String topic, String instanceName);

    MessageInfoDTO queryByMessageId(String msgId, String topic, String instanceName);
}
