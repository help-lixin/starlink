package help.lixin.starlink.plugin.gitlab.dto.group;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/25 7:30 下午
 * @Description
 */
public class GroupMemberUpdateDTO {

    private Long userGroupId;

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

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }
}
