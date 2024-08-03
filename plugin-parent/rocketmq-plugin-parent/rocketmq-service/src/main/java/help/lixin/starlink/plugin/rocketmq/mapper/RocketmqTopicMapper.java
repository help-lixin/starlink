package help.lixin.starlink.plugin.rocketmq.mapper;

import help.lixin.starlink.plugin.rocketmq.domain.RocketmqTopic;

import java.util.List;

public interface RocketmqTopicMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RocketmqTopic record);

    int insertSelective(RocketmqTopic record);

    RocketmqTopic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RocketmqTopic record);

    int updateByPrimaryKey(RocketmqTopic record);

    List<RocketmqTopic> queryTopicList(List<String> topicNames);

    int truncateTable();
}