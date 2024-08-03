package help.lixin.starlink.plugin.rocketmq.api.service;

import help.lixin.starlink.plugin.rocketmq.api.dto.message.MessageInfoDTO;

import java.util.List;

public interface IMessageService {

    List<MessageInfoDTO> queryByTopic(Long begin, Long end, String topic);

    List<MessageInfoDTO> queryByTopicAndKey(String key , String topic);

    MessageInfoDTO queryByMessageId(String msgId, String topic);

}
