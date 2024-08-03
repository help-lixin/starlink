package help.lixin.starlink.plugin.gitlab.request.group;

import help.lixin.starlink.plugin.gitlab.dto.group.AccessLevel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/17 2:56 下午
 * @Description
 */
public class GroupUpdateMemberVO {

    @NotNull
    @ApiModelProperty(value = "中间表id")
    private Long userGroupId;

    @NotNull(message = "accessLevel不能为空")
    @ApiModelProperty(value = "角色（GUEST(10), REPORTER(20), DEVELOPER(30), MAINTAINER(40), OWNER(50))）")
    private AccessLevel accessLevel;

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }


    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }
}
