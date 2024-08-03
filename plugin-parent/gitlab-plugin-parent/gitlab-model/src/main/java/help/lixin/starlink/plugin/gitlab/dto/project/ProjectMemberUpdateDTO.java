package help.lixin.starlink.plugin.gitlab.dto.project;

import help.lixin.starlink.plugin.gitlab.dto.group.AccessLevel;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/25 7:30 下午
 * @Description
 */
public class ProjectMemberUpdateDTO {

    private Long userProjectId;

    private AccessLevel accessLevel;

    private String updateBy;

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public Long getUserProjectId() {
        return userProjectId;
    }

    public void setUserProjectId(Long userProjectId) {
        this.userProjectId = userProjectId;
    }
}
