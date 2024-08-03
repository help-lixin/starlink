package help.lixin.starlink.plugin.gitlab.dto.group;

import help.lixin.starlink.core.dto.PageDTO;
import io.swagger.annotations.Api;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/23 3:33 下午
 * @Description
 */
@Api(tags = "成员分页查询")
public class GroupMemberPageListDTO extends PageDTO{

    private String userName;

    private String email;

    private String instanceCode;

    private Integer accessLevel;

    private Long groupId;

    private Integer status;

    private String beginTime;

    private String endTime;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public Integer getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(Integer accessLevel) {
        this.accessLevel = accessLevel;
    }
}
