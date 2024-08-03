package help.lixin.starlink.plugin.gitlab.request.group;

import io.swagger.annotations.Api;

/**
 * @Author: 伍岳林
 * @Date: 2023/11/3 6:48 下午
 * @Description
 */
@Api(tags = "修改组状态")
public class GroupChangeStatusVO{

    private Long groupId;

    private Integer status;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
