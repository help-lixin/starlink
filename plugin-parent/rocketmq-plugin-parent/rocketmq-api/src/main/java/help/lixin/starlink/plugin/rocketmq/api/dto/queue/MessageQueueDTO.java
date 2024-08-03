package help.lixin.starlink.plugin.rocketmq.api.dto.queue;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/23 6:25 下午
 * @Description
 */
public class MessageQueueDTO {

    private String topic;

    private String brokerName;

    private Long queueId;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public Long getQueueId() {
        return queueId;
    }

    public void setQueueId(Long queueId) {
        this.queueId = queueId;
    }
}
