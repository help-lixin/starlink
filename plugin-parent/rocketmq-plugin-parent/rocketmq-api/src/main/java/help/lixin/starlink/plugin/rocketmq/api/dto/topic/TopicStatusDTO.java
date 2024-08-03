package help.lixin.starlink.plugin.rocketmq.api.dto.topic;

import java.util.Map;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/23 3:21 下午
 * @Description
 */
public class TopicStatusDTO {

    private Map<String,TopicStatusMessageQueueDTO> offsetTable;

    public Map<String, TopicStatusMessageQueueDTO> getOffsetTable() {
        return offsetTable;
    }

    public void setOffsetTable(Map<String, TopicStatusMessageQueueDTO> offsetTable) {
        this.offsetTable = offsetTable;
    }
}
