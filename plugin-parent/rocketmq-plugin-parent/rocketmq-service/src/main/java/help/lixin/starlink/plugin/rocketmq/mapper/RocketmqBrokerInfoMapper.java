package help.lixin.starlink.plugin.rocketmq.mapper;

import java.util.List;

import help.lixin.starlink.plugin.rocketmq.domain.RocketmqBrokerInfo;

public interface RocketmqBrokerInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RocketmqBrokerInfo record);

    int insertSelective(RocketmqBrokerInfo record);

    RocketmqBrokerInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RocketmqBrokerInfo record);

    int updateByPrimaryKey(RocketmqBrokerInfo record);

    RocketmqBrokerInfo selectByClusterId(Long clusterId);

    RocketmqBrokerInfo selectByBrokerName(String brokerName);

    List<RocketmqBrokerInfo> selectByBrokerNames(List<String> brokerName);

    int truncateTable();
}