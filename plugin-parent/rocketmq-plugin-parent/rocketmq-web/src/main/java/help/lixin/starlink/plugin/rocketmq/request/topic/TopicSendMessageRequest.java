package help.lixin.starlink.plugin.rocketmq.request.topic;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/23 6:23 下午
 * @Description
 */
public class TopicSendMessageRequest {

    @NotBlank(message = "topic不能为空")
    @ApiModelProperty(value = "topic名称")
    private String topic;

    @NotBlank(message = "键名不能为空")
    @ApiModelProperty(value = "键名")
    private String key;

    @NotBlank(message = "标签不能为空")
    @ApiModelProperty(value = "标签")
    private String tag;

    @NotBlank(message = "消息体不能为空")
    @ApiModelProperty(value = "消息体")
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
