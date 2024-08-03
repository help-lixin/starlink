package help.lixin.starlink.plugin.gitlab.request.project;

import io.swagger.annotations.Api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/16 1:36 上午
 * @Description
 */
@Api(tags = "删除项目")
public class ProjectRemoveVO {

    @NotNull(message = "projectId不能为空")
    private Long projectId;

    @NotNull(message = "userId不能为空")
    private Long userId;

    @NotBlank(message = "instanceCode不能为空")
    private String instanceCode;

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
