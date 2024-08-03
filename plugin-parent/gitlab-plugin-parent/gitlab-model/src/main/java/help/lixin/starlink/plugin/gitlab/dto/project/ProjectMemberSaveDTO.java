package help.lixin.starlink.plugin.gitlab.dto.project;

import java.util.Date;
import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/11/28 4:23 下午
 * @Description
 */
public class ProjectMemberSaveDTO {

    private Long userId;

    private Long projectId;

    private Integer accessLevel;

    private String instanceCode;

    private Date expiresAt;

    private String createBy;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
