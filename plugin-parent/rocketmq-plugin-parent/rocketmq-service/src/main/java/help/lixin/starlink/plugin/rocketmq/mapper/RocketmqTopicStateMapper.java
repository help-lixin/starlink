package help.lixin.starlink.plugin.rocketmq.mapper;

import help.lixin.starlink.plugin.rocketmq.domain.RocketmqTopicState;

public interface RocketmqTopicStateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RocketmqTopicState record);

    int insertSelective(RocketmqTopicState record);

    RocketmqTopicState selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RocketmqTopicState record);

    int updateByPrimaryKey(RocketmqTopicState record);

    int truncateTable();
}