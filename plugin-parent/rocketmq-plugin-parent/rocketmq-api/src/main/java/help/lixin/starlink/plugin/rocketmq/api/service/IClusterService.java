package help.lixin.starlink.plugin.rocketmq.api.service;

import help.lixin.starlink.plugin.rocketmq.api.dto.broker.BrokerServerDTO;
import help.lixin.starlink.plugin.rocketmq.api.dto.cluster.ClusterConfigInfoDTO;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/24 5:48 下午
 * @Description
 */
public interface IClusterService {

    /**
     * @Author: 伍岳林
     * @Date: 2023/8/25 10:50 上午
     * @Return: help.lixin.rocketmq.api.dto.broker.BrokerServerDTO
     * @Description 查询集群列表
    */
    BrokerServerDTO list();

    /**
     * @Param brokerAddr :
     * @Author: 伍岳林
     * @Date: 2023/8/25 10:50 上午
     * @Return: help.lixin.rocketmq.api.dto.cluster.ClusterConfigInfoDTO
     * @Description 查询集群配置信息
    */
    ClusterConfigInfoDTO queryConfig(String brokerAddr);
}
