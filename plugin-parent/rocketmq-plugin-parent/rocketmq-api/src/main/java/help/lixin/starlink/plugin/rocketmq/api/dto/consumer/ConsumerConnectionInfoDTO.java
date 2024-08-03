package help.lixin.starlink.plugin.rocketmq.api.dto.consumer;

import java.util.List;
import java.util.Map;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/31 2:52 下午
 * @Description
 */
public class ConsumerConnectionInfoDTO {

    private List<ConsumerClientInfoDTO> connectionSet;
    private Map<String, ConsumerSubscriptionInfoDTO> subscriptionTable;
    private String consumeType;
    private String messageModel;
    private String consumeFromWhere;


    public List<ConsumerClientInfoDTO> getConnectionSet() {
        return connectionSet;
    }

    public void setConnectionSet(List<ConsumerClientInfoDTO> connectionSet) {
        this.connectionSet = connectionSet;
    }

    public Map<String, ConsumerSubscriptionInfoDTO> getSubscriptionTable() {
        return subscriptionTable;
    }

    public void setSubscriptionTable(Map<String, ConsumerSubscriptionInfoDTO> subscriptionTable) {
        this.subscriptionTable = subscriptionTable;
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

    public String getConsumeFromWhere() {
        return consumeFromWhere;
    }

    public void setConsumeFromWhere(String consumeFromWhere) {
        this.consumeFromWhere = consumeFromWhere;
    }
}
