package help.lixin.starlink.plugin.rocketmq.convert;

import org.mapstruct.Mapper;

import help.lixin.starlink.plugin.rocketmq.api.dto.topic.TopicInfoDTO;
import help.lixin.starlink.plugin.rocketmq.api.dto.topic.TopicSendMessageDTO;
import help.lixin.starlink.plugin.rocketmq.request.topic.TopicInfoRequest;
import help.lixin.starlink.plugin.rocketmq.request.topic.TopicSendMessageRequest;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/23 7:08 下午
 * @Description
 */
@Mapper(uses = TopicRule.class)
public interface TopicConvert {

    TopicInfoDTO convert(TopicInfoRequest topicInfoRequest);

    TopicSendMessageDTO convert(TopicSendMessageRequest topicSendMessageRequest);
}
