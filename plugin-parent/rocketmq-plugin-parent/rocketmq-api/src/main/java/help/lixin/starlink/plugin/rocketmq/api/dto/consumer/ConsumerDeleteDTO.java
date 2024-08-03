package help.lixin.starlink.plugin.rocketmq.api.dto.consumer;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/31 3:49 下午
 * @Description
 */
public class ConsumerDeleteDTO {

    private String groupName;
    private List<String> brokerNameList;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<String> getBrokerNameList() {
        return brokerNameList;
    }

    public void setBrokerNameList(List<String> brokerNameList) {
        this.brokerNameList = brokerNameList;
    }
}
