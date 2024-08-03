package help.lixin.starlink.plugin.gitlab.request.project;

import help.lixin.starlink.plugin.gitlab.dto.group.AccessLevel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/17 2:56 下午
 * @Description
 */
public class ProjectUpdateMemberVO {

    @NotNull
    @ApiModelProperty(value = "中间表id")
    private Long userProjectId;

    @NotNull(message = "accessLevel不能为空")
    @ApiModelProperty(value = "角色（GUEST(10), REPORTER(20), DEVELOPER(30), MAINTAINER(40), OWNER(50))）")
    private AccessLevel accessLevel;


    public Long getUserProjectId() {
        return userProjectId;
    }

    public void setUserProjectId(Long userProjectId) {
        this.userProjectId = userProjectId;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }
}
