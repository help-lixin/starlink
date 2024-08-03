package help.lixin.starlink.plugin.rocketmq.api.dto.broker;

import java.util.Map;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/23 5:19 下午
 * @Description
 */
public class BrokerInfoDTO {
    private String cluster;

    private String brokerName;

    private Map<String,String> brokerAddrs;

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public Map<String, String> getBrokerAddrs() {
        return brokerAddrs;
    }

    public void setBrokerAddrs(Map<String, String> brokerAddrs) {
        this.brokerAddrs = brokerAddrs;
    }
}
