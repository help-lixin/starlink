package help.lixin.starlink.plugin.rocketmq.api.dto.topic;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/30 4:53 下午
 * @Description
 */
public class TopicRollBackInfoDTO {

    private String brokerName;
    private Integer queueId;
    private Integer brokerOffset;
    private Integer consumerOffset;
    private Integer timestampOffset;
    private Integer rollbackOffset;

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

    public Integer getTimestampOffset() {
        return timestampOffset;
    }

    public void setTimestampOffset(Integer timestampOffset) {
        this.timestampOffset = timestampOffset;
    }

    public Integer getRollbackOffset() {
        return rollbackOffset;
    }

    public void setRollbackOffset(Integer rollbackOffset) {
        this.rollbackOffset = rollbackOffset;
    }
}
