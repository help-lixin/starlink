package help.lixin.starlink.plugin.rocketmq.service;

import java.util.List;

import help.lixin.response.PageResponse;
import help.lixin.starlink.core.dto.PageDTO;
import help.lixin.starlink.plugin.rocketmq.api.dto.broker.BrokerServerDTO;
import help.lixin.starlink.plugin.rocketmq.domain.RocketmqClusterConfig;
import help.lixin.starlink.plugin.rocketmq.domain.RocketmqClusterInfo;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/24 5:48 下午
 * @Description
 */
public interface IRocketMQClusterService {

    /**
     * @Author: 伍岳林
     * @Date: 2023/8/25 10:50 上午
     * @Description 查询集群列表
     */
    PageResponse<BrokerServerDTO> pageList(String clusterName, PageDTO pageDTO, String instanceName);

    List<RocketmqClusterInfo> clusterNameList();

    /**
     * @Param brokerAddr :
     * @Author: 伍岳林
     * @Date: 2023/8/25 10:50 上午
     * @Description 查询集群配置信息
     */
    RocketmqClusterConfig queryConfig(String brokerAddr, String instanceName);
}
