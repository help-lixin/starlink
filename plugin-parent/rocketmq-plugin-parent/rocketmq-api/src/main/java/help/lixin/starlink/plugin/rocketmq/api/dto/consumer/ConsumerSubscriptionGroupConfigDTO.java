package help.lixin.starlink.plugin.rocketmq.api.dto.consumer;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/31 3:20 下午
 * @Description
 */
public class ConsumerSubscriptionGroupConfigDTO {
    private String groupName;
    private Boolean consumeEnable;
    private Boolean consumeFromMinEnable;
    private Boolean consumeBroadcastEnable;
    private Integer retryQueueNums;
    private Integer retryMaxTimes;
    private Integer brokerId;
    private Integer whichBrokerWhenConsumeSlowly;
    private Boolean notifyConsumerIdsChangedEnable;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Boolean getConsumeEnable() {
        return consumeEnable;
    }

    public void setConsumeEnable(Boolean consumeEnable) {
        this.consumeEnable = consumeEnable;
    }

    public Boolean getConsumeFromMinEnable() {
        return consumeFromMinEnable;
    }

    public void setConsumeFromMinEnable(Boolean consumeFromMinEnable) {
        this.consumeFromMinEnable = consumeFromMinEnable;
    }

    public Boolean getConsumeBroadcastEnable() {
        return consumeBroadcastEnable;
    }

    public void setConsumeBroadcastEnable(Boolean consumeBroadcastEnable) {
        this.consumeBroadcastEnable = consumeBroadcastEnable;
    }

    public Integer getRetryQueueNums() {
        return retryQueueNums;
    }

    public void setRetryQueueNums(Integer retryQueueNums) {
        this.retryQueueNums = retryQueueNums;
    }

    public Integer getRetryMaxTimes() {
        return retryMaxTimes;
    }

    public void setRetryMaxTimes(Integer retryMaxTimes) {
        this.retryMaxTimes = retryMaxTimes;
    }

    public Integer getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Integer brokerId) {
        this.brokerId = brokerId;
    }

    public Integer getWhichBrokerWhenConsumeSlowly() {
        return whichBrokerWhenConsumeSlowly;
    }

    public void setWhichBrokerWhenConsumeSlowly(Integer whichBrokerWhenConsumeSlowly) {
        this.whichBrokerWhenConsumeSlowly = whichBrokerWhenConsumeSlowly;
    }

    public Boolean getNotifyConsumerIdsChangedEnable() {
        return notifyConsumerIdsChangedEnable;
    }

    public void setNotifyConsumerIdsChangedEnable(Boolean notifyConsumerIdsChangedEnable) {
        this.notifyConsumerIdsChangedEnable = notifyConsumerIdsChangedEnable;
    }
}
