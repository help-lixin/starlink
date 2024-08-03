package help.lixin.starlink.plugin.rocketmq.request.consumer;

import java.util.List;

import help.lixin.starlink.plugin.rocketmq.api.dto.consumer.ConsumerSubscriptionGroupConfigDTO;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/31 3:24 下午
 * @Description
 */
public class ConsumerSubscriptionGroupInfoRequest {

    private List<String> clusterNameList;
    private List<String> brokerNameList;
    private ConsumerSubscriptionGroupConfigDTO subscriptionGroupConfig;

    public List<String> getClusterNameList() {
        return clusterNameList;
    }

    public void setClusterNameList(List<String> clusterNameList) {
        this.clusterNameList = clusterNameList;
    }

    public List<String> getBrokerNameList() {
        return brokerNameList;
    }

    public void setBrokerNameList(List<String> brokerNameList) {
        this.brokerNameList = brokerNameList;
    }

    public ConsumerSubscriptionGroupConfigDTO getSubscriptionGroupConfig() {
        return subscriptionGroupConfig;
    }

    public void setSubscriptionGroupConfig(ConsumerSubscriptionGroupConfigDTO subscriptionGroupConfig) {
        this.subscriptionGroupConfig = subscriptionGroupConfig;
    }
}
