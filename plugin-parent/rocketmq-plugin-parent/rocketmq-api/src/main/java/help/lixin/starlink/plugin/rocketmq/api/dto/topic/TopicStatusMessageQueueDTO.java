package help.lixin.starlink.plugin.rocketmq.api.dto.topic;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/23 5:45 下午
 * @Description
 */
public class TopicStatusMessageQueueDTO {

    private Integer minOffset;

    private Integer maxOffset;

    private Long lastUpdateTimestamp;

    public Integer getMinOffset() {
        return minOffset;
    }

    public void setMinOffset(Integer minOffset) {
        this.minOffset = minOffset;
    }

    public Integer getMaxOffset() {
        return maxOffset;
    }

    public void setMaxOffset(Integer maxOffset) {
        this.maxOffset = maxOffset;
    }

    public Long getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(Long lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }
}
