package help.lixin.starlink.plugin.rocketmq.mapper;

import org.apache.ibatis.annotations.Param;

import help.lixin.starlink.plugin.rocketmq.domain.RocketmqClusterConfig;

public interface RocketmqClusterConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RocketmqClusterConfig record);

    int insertSelective(RocketmqClusterConfig record);

    RocketmqClusterConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RocketmqClusterConfig record);

    int updateByPrimaryKey(RocketmqClusterConfig record);

    RocketmqClusterConfig selectByBrokerNameAndClusterName(@Param("brokerName") String brokerName,
        @Param("clusterName") String clusterName);

    RocketmqClusterConfig selectByAddr(@Param("brokerAddr") String brokerAddr, @Param("listenPort") String listenPort);

    int truncateTable();
}