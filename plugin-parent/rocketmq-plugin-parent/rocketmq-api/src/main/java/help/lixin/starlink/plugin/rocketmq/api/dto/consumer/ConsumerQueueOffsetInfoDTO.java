package help.lixin.starlink.plugin.rocketmq.api.dto.consumer;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/31 3:14 下午
 * @Description
 */
public class ConsumerQueueOffsetInfoDTO {
    private String brokerName;
    private Integer queueId;
    private Long brokerOffset;
    private Long consumerOffset;
    private String clientInfo;
    private Long lastTimestamp;

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public Integer getQueueId() {
        return queueId;
    }

    public void setQueueId(Integer queueId) {
        this.queueId = queueId;
    }

    public Long getBrokerOffset() {
        return brokerOffset;
    }

    public void setBrokerOffset(Long brokerOffset) {
        this.brokerOffset = brokerOffset;
    }

    public Long getConsumerOffset() {
        return consumerOffset;
    }

    public void setConsumerOffset(Long consumerOffset) {
        this.consumerOffset = consumerOffset;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public Long getLastTimestamp() {
        return lastTimestamp;
    }

    public void setLastTimestamp(Long lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
    }
}
