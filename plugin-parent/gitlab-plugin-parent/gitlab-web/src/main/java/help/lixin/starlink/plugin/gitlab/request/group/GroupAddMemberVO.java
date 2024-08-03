package help.lixin.starlink.plugin.gitlab.request.group;

import help.lixin.starlink.plugin.gitlab.dto.group.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/15 11:48 上午
 * @Description
 */
@Api(tags = "添加组模型")
public class GroupAddMemberVO {

    @NotNull(message = "userId不能为空")
    @ApiModelProperty(value = "用户表id")
    private List<Long> userIds;

    @NotNull(message = "groupId不能为空")
    @ApiModelProperty(value = "组表id")
    private Long groupId;

    @NotNull(message = "accessLevel不能为空")
    @ApiModelProperty(value = "角色（ GUEST(10), REPORTER(20), DEVELOPER(30), MAINTAINER(40), OWNER(50) ）")
    private AccessLevel accessLevel;

    @ApiModelProperty(value = "权限到期时间（可选）")
    private Date expiresAt;

    @NotNull(message = "instanceCode不能为空")
    private String instanceCode;

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }
}
