package help.lixin.starlink.plugin.rocketmq.api.dto.consumer;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/31 3:14 下午
 * @Description
 */
public class ConsumerOffsetInfoDTO {
    private String topic;
    private Integer diffTotal;
    private Long lastTimestamp;
    private List<ConsumerQueueOffsetInfoDTO> queueStatInfoList;

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

    public List<ConsumerQueueOffsetInfoDTO> getQueueStatInfoList() {
        return queueStatInfoList;
    }

    public void setQueueStatInfoList(List<ConsumerQueueOffsetInfoDTO> queueStatInfoList) {
        this.queueStatInfoList = queueStatInfoList;
    }
}
