package help.lixin.starlink.plugin.rocketmq.api.dto.queue;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/23 4:54 下午
 * @Description
 */
public class QueueInfoDTO {

    private String brokerName;

    private Integer readQueueNums;

    private Integer writeQueueNums;

    private Integer perm;

    private Integer topicSynFlag;

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public Integer getReadQueueNums() {
        return readQueueNums;
    }

    public void setReadQueueNums(Integer readQueueNums) {
        this.readQueueNums = readQueueNums;
    }

    public Integer getWriteQueueNums() {
        return writeQueueNums;
    }

    public void setWriteQueueNums(Integer writeQueueNums) {
        this.writeQueueNums = writeQueueNums;
    }

    public Integer getPerm() {
        return perm;
    }

    public void setPerm(Integer perm) {
        this.perm = perm;
    }

    public Integer getTopicSynFlag() {
        return topicSynFlag;
    }

    public void setTopicSynFlag(Integer topicSynFlag) {
        this.topicSynFlag = topicSynFlag;
    }
}
