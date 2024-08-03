package help.lixin.starlink.plugin.gitlab.dto.project;

import help.lixin.starlink.core.dto.PageDTO;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/26 5:17 下午
 * @Description
 */
public class ProjectQueryDTO extends PageDTO {

    private String instanceCode;

    private String visibility;

    private String path;

    private String projectName;

    private Integer status;

    private String beginTime;

    private String endTime;

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }
}
