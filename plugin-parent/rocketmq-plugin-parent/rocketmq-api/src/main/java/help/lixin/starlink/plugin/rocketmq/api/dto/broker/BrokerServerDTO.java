package help.lixin.starlink.plugin.rocketmq.api.dto.broker;

import help.lixin.starlink.plugin.rocketmq.api.dto.cluster.ClusterInfoDTO;

import java.util.Map;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/24 5:14 下午
 * @Description
 */
public class BrokerServerDTO {

    private Map<String, Map<String, BrokerDetailDTO>> brokerServer;
    private ClusterInfoDTO clusterInfo;

    public Map<String, Map<String, BrokerDetailDTO>> getBrokerServer() {
        return brokerServer;
    }

    public void setBrokerServer(Map<String, Map<String, BrokerDetailDTO>> brokerServer) {
        this.brokerServer = brokerServer;
    }

    public ClusterInfoDTO getClusterInfo() {
        return clusterInfo;
    }

    public void setClusterInfo(ClusterInfoDTO clusterInfo) {
        this.clusterInfo = clusterInfo;
    }
}
