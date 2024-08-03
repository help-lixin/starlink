package help.lixin.starlink.plugin.rocketmq.api.service;

import java.util.List;
import java.util.Map;

import help.lixin.starlink.plugin.rocketmq.api.dto.consumer.*;
import help.lixin.starlink.plugin.rocketmq.api.dto.topic.TopicRollBackInfoDTO;

public interface IConsumerService {

    /**
     * @Author: 伍岳林
     * @Date: 2023/8/31 3:31 下午
     * @Return: java.util.List<help.lixin.rocketmq.api.dto.consumer.ConsumerListDTO>
     * @Description 消费者列表
     */
    List<ConsumerListDTO> groupList();

    /**
     * @Param consumerGroup :
     * @Author: 伍岳林
     * @Date: 2023/8/31 3:31 下午
     * @Return: help.lixin.rocketmq.api.dto.consumer.ConsumerConnectionInfoDTO
     * @Description 消费者终端信息
     */
    ConsumerConnectionInfoDTO consumerConnection(String consumerGroup);

    /**
     * @Param consumerGroup :
     * @Author: 伍岳林
     * @Date: 2023/8/31 3:32 下午
     * @Return: java.util.List<help.lixin.rocketmq.api.dto.consumer.ConsumerOffsetInfoDTO>
     * @Description 消费者详情
     */
    List<ConsumerOffsetInfoDTO> queryTopicByConsumer(String consumerGroup);

    /**
     * @Param consumerGroup :
     * @Author: 伍岳林
     * @Date: 2023/8/31 3:33 下午
     * @Return: java.util.List<help.lixin.rocketmq.api.dto.consumer.ConsumerSubscriptionGroupDataDTO>
     * @Description 消费者配置信息
     */
    List<ConsumerSubscriptionGroupDataDTO> examineSubscriptionGroupConfig(String consumerGroup);

    /**
     * @Param consumerSubscriptionGroupInfoDTO :
     * @Author: 伍岳林
     * @Date: 2023/8/31 3:33 下午
     * @Return: java.lang.Boolean
     * @Description 创建、更新消费者信息
     */
    Boolean createOrUpdate(ConsumerSubscriptionGroupInfoDTO consumerSubscriptionGroupInfoDTO);

    /**
     * @Param consumerGroup :
     * @Author: 伍岳林
     * @Date: 2023/8/31 3:35 下午
     * @Return: java.util.List<java.lang.String>
     * @Description 查询broker列表
     */
    List<String> fetchBrokerNameList(String consumerGroup);

    /**
     * @Param groupName :
     * @Param brokerNameList :
     * @Author: 伍岳林
     * @Date: 2023/8/31 3:36 下午
     * @Return: java.lang.Boolean
     * @Description 删除消费者
     * @param consumerDeleteDTO
     */
    Boolean deleteSubGroup(ConsumerDeleteDTO consumerDeleteDTO);

    /**
     * @Param topicName :
     * @Author: 伍岳林
     * @Date: 2023/8/30 4:50 下午
     * @Return: java.util.List<java.lang.String>
     * @Description 重置消费点位
     * @return
     */
    Map<String, List<TopicRollBackInfoDTO>> resetOffset(String topicName);
}
