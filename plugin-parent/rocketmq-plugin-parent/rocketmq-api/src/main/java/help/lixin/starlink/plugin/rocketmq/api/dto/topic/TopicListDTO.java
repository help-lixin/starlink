package help.lixin.starlink.plugin.rocketmq.api.dto.topic;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/22 4:40 下午
 * @Description
 */
public class TopicListDTO {

    private List<String> topicList;

    private String brokerAddr;

    public List<String> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<String> topicList) {
        this.topicList = topicList;
    }

    public String getBrokerAddr() {
        return brokerAddr;
    }

    public void setBrokerAddr(String brokerAddr) {
        this.brokerAddr = brokerAddr;
    }
}
