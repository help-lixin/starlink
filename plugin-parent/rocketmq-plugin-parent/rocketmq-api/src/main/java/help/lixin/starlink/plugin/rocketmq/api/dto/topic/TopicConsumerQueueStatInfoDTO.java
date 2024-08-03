package help.lixin.starlink.plugin.rocketmq.api.dto.topic;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/30 11:49 上午
 * @Description topic中consumer管理的订阅组列表信息
 */
public class TopicConsumerQueueStatInfoDTO {

    private String brokerName;
    private Integer queueId;
    private Integer brokerOffset;
    private Integer consumerOffset;
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

    public Integer getBrokerOffset() {
        return brokerOffset;
    }

    public void setBrokerOffset(Integer brokerOffset) {
        this.brokerOffset = brokerOffset;
    }

    public Integer getConsumerOffset() {
        return consumerOffset;
    }

    public void setConsumerOffset(Integer consumerOffset) {
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
