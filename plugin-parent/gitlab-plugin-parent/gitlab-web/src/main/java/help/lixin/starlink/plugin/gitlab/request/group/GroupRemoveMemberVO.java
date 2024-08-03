package help.lixin.starlink.plugin.gitlab.request.group;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/15 11:28 上午
 * @Description
 */
public class GroupRemoveMemberVO {

    @ApiModelProperty(value = "userId")
    @NotNull(message = "userId不能为空")
    private Long userId;

    @ApiModelProperty(value = "groupId")
    @NotNull(message = "groupId不能为空")
    private Long groupId;

    @ApiModelProperty(value = "instanceCode")
    @NotNull(message = "instanceCode不能为空")
    private String instanceCode;

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
