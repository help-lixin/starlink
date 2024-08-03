package help.lixin.starlink.plugin.rocketmq.job.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.core.plugin.InstanceService;
import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.rocketmq.api.dto.broker.BrokerInfoDTO;
import help.lixin.starlink.plugin.rocketmq.api.dto.queue.QueueInfoDTO;
import help.lixin.starlink.plugin.rocketmq.api.dto.topic.TopicConsumerDTO;
import help.lixin.starlink.plugin.rocketmq.api.dto.topic.TopicInfoDTO;
import help.lixin.starlink.plugin.rocketmq.api.dto.topic.TopicRouteDTO;
import help.lixin.starlink.plugin.rocketmq.api.dto.topic.TopicStatusMessageQueueDTO;
import help.lixin.starlink.plugin.rocketmq.api.service.ITopicService;
import help.lixin.starlink.plugin.rocketmq.convert.TopicServiceConvert;
import help.lixin.starlink.plugin.rocketmq.domain.*;
import help.lixin.starlink.plugin.rocketmq.job.convert.TopicJobConvert;
import help.lixin.starlink.plugin.rocketmq.mapper.*;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/26 9:50 下午
 * @Description 查询gitlab的Group对比数据库的数据，不存在的插入到数据库中
 */
public class RocketMQTopicJobService extends InstanceService<ITopicService> {

    private RocketmqTopicMapper topicMapper;
    private RocketmqTopicRouteMapper topicRouteMapper;
    private RocketmqTopicStateMapper topicStateMapper;
    private RocketmqTopicSubGroupMapper topicSubGroupMapper;
    private RocketmqBrokerInfoMapper brokerInfoMapper;

    public void pullTopic() {
        // 获取插件列表
        Set<String> contextNames = pluginNamedContextFactory.getContextNames();
        if (CollectionUtils.isEmpty(contextNames)) {
            return;
        }

        // 遍历插件列表
        contextNames.forEach(instanceName -> {
            try {
                ITopicService topicApi = getApi(instanceName);

                List<String> brokerNames = topicApi.queryList();

                // 没有检测到插件退出任务
                if (CollectionUtils.isEmpty(brokerNames)) {
                    return;
                }

                topicMapper.truncateTable();
                topicRouteMapper.truncateTable();
                topicStateMapper.truncateTable();
                topicSubGroupMapper.truncateTable();

                // 查询库中已存在的broker列表
                List<RocketmqBrokerInfo> rocketmqBrokerList = brokerInfoMapper.selectByBrokerNames(brokerNames);
                Map<String, Long> brokerInfoMap = rocketmqBrokerList.stream()
                    .collect(Collectors.toMap(RocketmqBrokerInfo::getBrokerName, RocketmqBrokerInfo::getId));

                // 对已存在的broker信息检测并插入新的信息
                for (String topicName : brokerNames) {

                    // 新增topic
                    RocketmqTopic topic = createTopic(topicApi, brokerInfoMap, topicName);

                    // 新增topic状态记录
                    createTopicState(topicApi, topicName, topic);

                    // 新增topic路由信息
                    createRouteInfo(topicApi, topic);

                    // 新增topic订阅信息
                    Map<String, TopicConsumerDTO> topicConsumerDTOMap =
                        topicApi.queryConsumerByTopic(topic.getTopicName());
                    topicConsumerDTOMap.forEach((k, v) -> {
                        TopicJobConvert topicConvert = Mappers.getMapper(TopicJobConvert.class);
                        v.getQueueStatInfoList().forEach(i -> {

                            RocketmqTopicSubGroup topicSubGroup = topicConvert.convert(i);
                            topicSubGroup.setTopicId(topic.getId());
                            topicSubGroup.setDiffTotal(v.getDiffTotal());

                            Date date = null;
                            try {
                                String formatStr = "yyyy-MM-dd HH:mm:ss";
                                SimpleDateFormat sd = new SimpleDateFormat(formatStr);
                                // long转Date
                                date = new SimpleDateFormat(formatStr).parse(sd.format(new Date(v.getLastTimestamp())));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            topicSubGroup.setLastTime(date);
                            topicSubGroup.setSubGroupName(v.getTopic());
                            topicSubGroupMapper.insertSelective(topicSubGroup);
                        });

                    });

                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException("定时任务发生异常");
            }

        });

    }

    private void createRouteInfo(ITopicService topicApi, RocketmqTopic topic) {
        TopicRouteDTO topicRouteDTO = topicApi.queryRoute(topic.getTopicName());
        TopicJobConvert routeConvert = Mappers.getMapper(TopicJobConvert.class);
        List<QueueInfoDTO> queueDatas = topicRouteDTO.getQueueDatas();

        for (int i = 0; i < queueDatas.size(); i++) {
            BrokerInfoDTO brokerInfoDTO = topicRouteDTO.getBrokerDatas().get(i);
            RocketmqTopicRoute rocketmqTopicRoute = routeConvert.convert(queueDatas.get(i));
            rocketmqTopicRoute.setCluster(brokerInfoDTO.getCluster());
            rocketmqTopicRoute.setBrokerAddrs(brokerInfoDTO.getBrokerAddrs().get("0"));
            rocketmqTopicRoute.setTopicId(topic.getId());
            topicRouteMapper.insertSelective(rocketmqTopicRoute);
        }
    }

    private void createTopicState(ITopicService topicApi, String brokerName, RocketmqTopic topic) {
        Map<String, TopicStatusMessageQueueDTO> statusMap = topicApi.queryStatus(brokerName);

        statusMap.forEach((k, v) -> {
            TopicServiceConvert topicServiceConvert = Mappers.getMapper(TopicServiceConvert.class);
            RocketmqTopicState topicState = topicServiceConvert.convert(v);
            topicState.setQueue(k);
            topicState.setTopicId(topic.getId());
            topicStateMapper.insertSelective(topicState);
        });
    }

    private RocketmqTopic createTopic(ITopicService topicApi, Map<String, Long> brokerInfoMap, String brokerName) {
        // 列表中很可能只有一个对象，不能完全确保，需要对这个接口深入研究
        List<TopicInfoDTO> topicInfoDTOS = topicApi.queryTopicInfo(brokerName);
        TopicInfoDTO topicInfoDTO = topicInfoDTOS.get(0);

        TopicServiceConvert mapper = Mappers.getMapper(TopicServiceConvert.class);
        RocketmqTopic topic = mapper.convert(topicInfoDTO);
        topic.setBrokerId(brokerInfoMap.get(topicInfoDTO.getBrokerNameList().get(0)));
        topicMapper.insertSelective(topic);
        return topic;
    }

    public RocketMQTopicJobService(PluginNamedContextFactory pluginNamedContextFactory, RocketmqTopicMapper topicMapper,
        RocketmqTopicRouteMapper topicRouteMapper, RocketmqTopicStateMapper topicStateMapper,
        RocketmqTopicSubGroupMapper topicSubGroupMapper, RocketmqBrokerInfoMapper brokerInfoMapper) {
        this.pluginNamedContextFactory = pluginNamedContextFactory;
        this.topicMapper = topicMapper;
        this.topicRouteMapper = topicRouteMapper;
        this.topicStateMapper = topicStateMapper;
        this.topicSubGroupMapper = topicSubGroupMapper;
        this.brokerInfoMapper = brokerInfoMapper;
    }
}
