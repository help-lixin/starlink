package help.lixin.starlink.plugin.rocketmq.api.dto.topic;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/30 11:47 上午
 * @Description
 */
public class TopicConsumerDTO {

    private String topic;

    private Integer diffTotal;

    private Long lastTimestamp;

    private List<TopicConsumerQueueStatInfoDTO> queueStatInfoList;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getDiffTotal() {
        return diffTotal;
    }

    public void setDiffTotal(Integer diffTotal) {
        this.diffTotal = diffTotal;
    }

    public Long getLastTimestamp() {
        return lastTimestamp;
    }

    public void setLastTimestamp(Long lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
    }

    public List<TopicConsumerQueueStatInfoDTO> getQueueStatInfoList() {
        return queueStatInfoList;
    }

    public void setQueueStatInfoList(List<TopicConsumerQueueStatInfoDTO> queueStatInfoList) {
        this.queueStatInfoList = queueStatInfoList;
    }
}
