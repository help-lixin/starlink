package help.lixin.starlink.plugin.gitlab.request.project;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/11/28 4:23 下午
 * @Description
 */
public class ProjectMemberSaveVO {

    @NotNull(message = "用户id不能为空")
    private List<Long> userIds;

    @NotNull(message = "项目id不能为空")
    private Long projectId;

    @NotNull(message = "权限不能为空")
    private Integer accessLevel;

    @NotBlank(message = "instanceCode不能为空")
    private String instanceCode;

    @ApiModelProperty(value = "过期时间（可选）")
    private Date expiresAt;

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(Integer accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

}
