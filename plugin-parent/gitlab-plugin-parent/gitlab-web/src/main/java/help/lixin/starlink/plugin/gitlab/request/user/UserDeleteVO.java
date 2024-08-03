package help.lixin.starlink.plugin.gitlab.request.user;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/13 7:46 下午
 * @Description
 */
public class UserDeleteVO {

    @NotNull(message = "userId不能为空")
    @ApiModelProperty(value = "userId")
    private Long userId;

    @NotNull(message = "instanceCode不能为空")
    @ApiModelProperty(value = "instanceCode")
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
}
