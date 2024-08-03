package help.lixin.starlink.plugin.gitlab.request.group;

import javax.validation.constraints.NotNull;

import help.lixin.starlink.request.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/23 3:33 下午
 * @Description
 */
@Api(tags = "成员分页查询")
public class GroupPageListVO extends PageRequest {

    @NotNull(message = "instanceCode不能为空")
    private String instanceCode;

    @ApiModelProperty(value = "组名")
    private String groupName;

    @ApiModelProperty(value = "权限(PUBLIC, PRIVATE, INTERNAL)")
    private String visibility;

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
