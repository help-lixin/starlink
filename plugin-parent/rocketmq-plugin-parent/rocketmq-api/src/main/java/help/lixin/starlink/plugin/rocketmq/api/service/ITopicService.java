package help.lixin.starlink.plugin.rocketmq.api.service;

import java.util.List;
import java.util.Map;

import help.lixin.starlink.plugin.rocketmq.api.dto.topic.*;

public interface ITopicService {

    /**
     * @Author: 伍岳林
     * @Date: 2023/8/23 6:07 下午
     * @Return: java.util.List<java.lang.String>
     * @Description 查看topic列表
     */
    List<String> queryList();

    /**
     * @Param topicName :
     * @Author: 伍岳林
     * @Date: 2023/8/23 6:10 下午
     * @Return: java.util.Map<java.lang.String,help.lixin.starlink.rocketmq.api.dto.topic.TopicStatusMessageQueueDTO>
     * @Description 查看topic当前状态信息
     */
    Map<String, TopicStatusMessageQueueDTO> queryStatus(String topicName);

    /**
     * @Param topicName :
     * @Author: 伍岳林
     * @Date: 2023/8/23 6:07 下午
     * @Return: help.lixin.starlink.rocketmq.api.dto.topic.TopicRouteDTO
     * @Description 查看路由信息
     */
    TopicRouteDTO queryRoute(String topicName);

    /**
     * @Param topicName :
     * @Author: 伍岳林
     * @Date: 2023/8/30 3:33 下午
     * @Return: java.util.Map<java.lang.String,help.lixin.starlink.rocketmq.api.dto.topic.TopicConsumerDTO>
     * @Description 查看consumer管理页面
     */
    Map<String, TopicConsumerDTO> queryConsumerByTopic(String topicName);

    /**
     * @Param topicName :
     * @Author: 伍岳林
     * @Date: 2023/8/30 4:37 下午
     * @Return: java.util.List<java.lang.String>
     * @Description 查询订阅组列表
     */
    List<String> queryTopicConsumerInfo(String topicName);

    /**
     * @Param topicName :
     * @Author: 伍岳林
     * @Date: 2023/8/23 6:10 下午
     * @Return: help.lixin.starlink.rocketmq.api.dto.topic.TopicInfoDTO
     * @Description 查看topic详情
     * @return
     */
    List<TopicInfoDTO> queryTopicInfo(String topicName);

    /**
     * @Param topicInfoDTO :
     * @Author: 伍岳林
     * @Date: 2023/8/23 6:11 下午
     * @Return: java.lang.Boolean
     * @Description 更新topic信息
     */
    Boolean saveOrUpdate(TopicInfoDTO topicInfoDTO);

    /**
     * @Param topicSendMessageDTO :
     * @Author: 伍岳林
     * @Date: 2023/8/23 6:36 下午
     * @Return: help.lixin.starlink.rocketmq.api.dto.queue.MessageQueueDTO
     * @Description 发送信息
     */
    TopicMessageStatusDTO sendMessage(TopicSendMessageDTO topicSendMessageDTO);

    /**
     * @Param topicName :
     * @Author: 伍岳林
     * @Date: 2023/8/24 4:55 下午
     * @Return: java.lang.Boolean
     * @Description 删除topic
     */
    Boolean deleteTopic(String topicName);
}
