package help.lixin.starlink.plugin.gitlab.dto.group;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/16 12:14 下午
 * @Description
 */
public class GroupQueryDTO {

    private String groupName;

    private String instanceCode;


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
