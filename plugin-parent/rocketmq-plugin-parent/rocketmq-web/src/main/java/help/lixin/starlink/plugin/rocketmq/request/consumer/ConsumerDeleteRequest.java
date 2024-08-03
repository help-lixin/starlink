package help.lixin.starlink.plugin.rocketmq.request.consumer;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/31 3:49 下午
 * @Description
 */
public class ConsumerDeleteRequest {

    @ApiModelProperty(value = "订阅组名称")
    @NotBlank(message = "订阅组名称不能为空")
    private String groupName;

    @ApiModelProperty(value = "需要删除的broker列表")
    @NotNull(message = "broker列表不能为空")
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
