package help.lixin.starlink.plugin.rocketmq.mapper;

import help.lixin.starlink.plugin.rocketmq.domain.RocketmqTopicRoute;

public interface RocketmqTopicRouteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RocketmqTopicRoute record);

    int insertSelective(RocketmqTopicRoute record);

    RocketmqTopicRoute selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RocketmqTopicRoute record);

    int updateByPrimaryKey(RocketmqTopicRoute record);

    int truncateTable();
}