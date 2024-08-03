package help.lixin.starlink.plugin.rocketmq.mapper;

import java.util.List;

import help.lixin.starlink.plugin.rocketmq.domain.RocketmqClusterInfo;

public interface RocketmqClusterInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RocketmqClusterInfo record);

    int insertSelective(RocketmqClusterInfo record);

    RocketmqClusterInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RocketmqClusterInfo record);

    int updateByPrimaryKey(RocketmqClusterInfo record);

    RocketmqClusterInfo selectByClusterName(String clusterName);

    List<RocketmqClusterInfo> selectClusterList();

    int truncateTable();
}