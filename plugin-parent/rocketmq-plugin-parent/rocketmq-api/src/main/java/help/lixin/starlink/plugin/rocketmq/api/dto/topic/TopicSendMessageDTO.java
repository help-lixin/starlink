package help.lixin.starlink.plugin.rocketmq.api.dto.topic;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/23 6:23 下午
 * @Description
 */
public class TopicSendMessageDTO {

    private String topic;
    private String key;
    private String tag;
    private String messageBody;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
