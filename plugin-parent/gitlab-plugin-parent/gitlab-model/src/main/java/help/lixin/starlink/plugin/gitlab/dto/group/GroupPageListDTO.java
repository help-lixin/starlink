package help.lixin.starlink.plugin.gitlab.dto.group;

import help.lixin.starlink.core.dto.PageDTO;
import io.swagger.annotations.Api;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/23 3:33 下午
 * @Description
 */
@Api(tags = "组分页查询")
public class GroupPageListDTO extends PageDTO {

    private Long groupId;

    private String instanceCode;

    private String groupName;

    private String path;

    private String visibility;

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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
