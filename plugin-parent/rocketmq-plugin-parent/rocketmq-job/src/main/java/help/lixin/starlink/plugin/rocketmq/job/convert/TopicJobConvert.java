package help.lixin.starlink.plugin.rocketmq.job.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import help.lixin.starlink.plugin.rocketmq.api.dto.queue.QueueInfoDTO;
import help.lixin.starlink.plugin.rocketmq.api.dto.topic.TopicConsumerQueueStatInfoDTO;
import help.lixin.starlink.plugin.rocketmq.convert.TopicRule;
import help.lixin.starlink.plugin.rocketmq.domain.RocketmqTopicRoute;
import help.lixin.starlink.plugin.rocketmq.domain.RocketmqTopicSubGroup;

@Mapper(uses = TopicRule.class)
public interface TopicJobConvert {
    @Mapping(source = "brokerName", target = "broker")
    RocketmqTopicRoute convert(QueueInfoDTO queueInfoDTO);

    @Mapping(source = "lastTimestamp", target = "consumerLastTime")
    RocketmqTopicSubGroup convert(TopicConsumerQueueStatInfoDTO topicConsumerQueueStatInfoDTO);
}
