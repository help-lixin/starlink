package help.lixin.starlink.plugin.rocketmq.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import help.lixin.starlink.plugin.rocketmq.api.dto.topic.TopicInfoDTO;
import help.lixin.starlink.plugin.rocketmq.api.dto.topic.TopicStatusMessageQueueDTO;
import help.lixin.starlink.plugin.rocketmq.domain.RocketmqTopic;
import help.lixin.starlink.plugin.rocketmq.domain.RocketmqTopicState;

@Mapper(uses = TopicRule.class)
public interface TopicServiceConvert {

    @Mapping(source = "order", target = "order")
    RocketmqTopic convert(TopicInfoDTO topicInfoDTO);

    @Mapping(source = "minOffset", target = "minPoint")
    @Mapping(source = "maxOffset", target = "maxPoint")
    @Mapping(source = "lastUpdateTimestamp", target = "createdTime")
    RocketmqTopicState convert(TopicStatusMessageQueueDTO topicStatusMessageQueueDTO);
}
