package help.lixin.starlink.plugin.rocketmq.api.dto.message;

import java.util.Map;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/28 10:39 上午
 * @Description
 */
public class MessageInfoDTO {
    private Integer queueId;
    private Integer storeSize;
    private Integer queueOffset;
    private Integer sysFlag;
    private Long bornTimestamp;
    private String bornHost;
    private Long storeTimestamp;
    private String storeHost;
    private String msgId;
    private Long commitLogOffset;
    private Integer bodyCRC;
    private Integer reconsumeTimes;
    private Long preparedTransactionOffset;
    private String topic;
    private Integer flag;
    private Map<String, String> properties;
    private String messageBody;

    public Integer getQueueId() {
        return queueId;
    }

    public void setQueueId(Integer queueId) {
        this.queueId = queueId;
    }

    public Integer getStoreSize() {
        return storeSize;
    }

    public void setStoreSize(Integer storeSize) {
        this.storeSize = storeSize;
    }

    public Integer getQueueOffset() {
        return queueOffset;
    }

    public void setQueueOffset(Integer queueOffset) {
        this.queueOffset = queueOffset;
    }

    public Integer getSysFlag() {
        return sysFlag;
    }

    public void setSysFlag(Integer sysFlag) {
        this.sysFlag = sysFlag;
    }

    public Long getBornTimestamp() {
        return bornTimestamp;
    }

    public void setBornTimestamp(Long bornTimestamp) {
        this.bornTimestamp = bornTimestamp;
    }

    public String getBornHost() {
        return bornHost;
    }

    public void setBornHost(String bornHost) {
        this.bornHost = bornHost;
    }

    public Long getStoreTimestamp() {
        return storeTimestamp;
    }

    public void setStoreTimestamp(Long storeTimestamp) {
        this.storeTimestamp = storeTimestamp;
    }

    public String getStoreHost() {
        return storeHost;
    }

    public void setStoreHost(String storeHost) {
        this.storeHost = storeHost;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Long getCommitLogOffset() {
        return commitLogOffset;
    }

    public void setCommitLogOffset(Long commitLogOffset) {
        this.commitLogOffset = commitLogOffset;
    }

    public Integer getBodyCRC() {
        return bodyCRC;
    }

    public void setBodyCRC(Integer bodyCRC) {
        this.bodyCRC = bodyCRC;
    }

    public Integer getReconsumeTimes() {
        return reconsumeTimes;
    }

    public void setReconsumeTimes(Integer reconsumeTimes) {
        this.reconsumeTimes = reconsumeTimes;
    }

    public Long getPreparedTransactionOffset() {
        return preparedTransactionOffset;
    }

    public void setPreparedTransactionOffset(Long preparedTransactionOffset) {
        this.preparedTransactionOffset = preparedTransactionOffset;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
