package help.lixin.starlink.plugin.rocketmq.api.dto.consumer;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/30 5:10 下午
 * @Description
 */
public class ConsumerListDTO {

    private String group;
    private String version;
    private Integer count;
    private String consumeType;
    private String messageModel;
    private Integer consumeTps;
    private Integer diffTotal;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(String consumeType) {
        this.consumeType = consumeType;
    }

    public String getMessageModel() {
        return messageModel;
    }

    public void setMessageModel(String messageModel) {
        this.messageModel = messageModel;
    }

    public Integer getConsumeTps() {
        return consumeTps;
    }

    public void setConsumeTps(Integer consumeTps) {
        this.consumeTps = consumeTps;
    }

    public Integer getDiffTotal() {
        return diffTotal;
    }

    public void setDiffTotal(Integer diffTotal) {
        this.diffTotal = diffTotal;
    }
}
