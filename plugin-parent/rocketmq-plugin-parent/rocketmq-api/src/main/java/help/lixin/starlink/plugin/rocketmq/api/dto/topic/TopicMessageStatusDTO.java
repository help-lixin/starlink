package help.lixin.starlink.plugin.rocketmq.api.dto.topic;

import help.lixin.starlink.plugin.rocketmq.api.dto.queue.MessageQueueDTO;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/23 6:24 下午
 * @Description
 */
public class TopicMessageStatusDTO {

    private MessageQueueDTO messageQueue;

    private String sendStatus;

    private String msgId;

    private Integer queueOffset;

    private Long transactionId;

    private String offsetMsgId;

    private String regionId;

    private Boolean traceOn;


    public MessageQueueDTO getMessageQueue() {
        return messageQueue;
    }

    public void setMessageQueue(MessageQueueDTO messageQueue) {
        this.messageQueue = messageQueue;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Integer getQueueOffset() {
        return queueOffset;
    }

    public void setQueueOffset(Integer queueOffset) {
        this.queueOffset = queueOffset;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getOffsetMsgId() {
        return offsetMsgId;
    }

    public void setOffsetMsgId(String offsetMsgId) {
        this.offsetMsgId = offsetMsgId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public Boolean getTraceOn() {
        return traceOn;
    }

    public void setTraceOn(Boolean traceOn) {
        this.traceOn = traceOn;
    }
}
