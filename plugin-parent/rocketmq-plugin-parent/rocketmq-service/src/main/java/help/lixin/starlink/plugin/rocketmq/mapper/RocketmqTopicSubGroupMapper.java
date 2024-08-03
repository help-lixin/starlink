package help.lixin.starlink.plugin.rocketmq.mapper;

import help.lixin.starlink.plugin.rocketmq.domain.RocketmqTopicSubGroup;

public interface RocketmqTopicSubGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RocketmqTopicSubGroup record);

    int insertSelective(RocketmqTopicSubGroup record);

    RocketmqTopicSubGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RocketmqTopicSubGroup record);

    int updateByPrimaryKey(RocketmqTopicSubGroup record);

    int truncateTable();
}