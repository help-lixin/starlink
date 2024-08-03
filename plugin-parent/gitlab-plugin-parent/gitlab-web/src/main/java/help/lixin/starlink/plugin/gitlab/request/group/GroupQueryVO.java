package help.lixin.starlink.plugin.gitlab.request.group;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/16 12:14 下午
 * @Description
 */
public class GroupQueryVO {

    @ApiModelProperty(value = "组名")
    @NotBlank(message = "groupName不能为空")
    private String groupName;

    @NotNull(message = "instanceCode不能为空")
    private String instanceCode;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }
}
