package help.lixin.starlink.plugin.rocketmq.api.dto.cluster;

import help.lixin.starlink.plugin.rocketmq.api.dto.broker.BrokerInfoDTO;

import java.util.List;
import java.util.Map;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/24 5:15 下午
 * @Description
 */
public class ClusterInfoDTO {

    private Map<String, BrokerInfoDTO> brokerAddrTable;
    private Map<String, List<String>> clusterAddrTable;

    public Map<String, BrokerInfoDTO> getBrokerAddrTable() {
        return brokerAddrTable;
    }

    public void setBrokerAddrTable(Map<String, BrokerInfoDTO> brokerAddrTable) {
        this.brokerAddrTable = brokerAddrTable;
    }

    public Map<String, List<String>> getClusterAddrTable() {
        return clusterAddrTable;
    }

    public void setClusterAddrTable(Map<String, List<String>> clusterAddrTable) {
        this.clusterAddrTable = clusterAddrTable;
    }
}
