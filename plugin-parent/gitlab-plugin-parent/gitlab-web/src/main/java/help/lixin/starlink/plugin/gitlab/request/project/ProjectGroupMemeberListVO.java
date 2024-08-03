package help.lixin.starlink.plugin.gitlab.request.project;

import javax.validation.constraints.NotBlank;

import help.lixin.starlink.request.PageRequest;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/11/28 2:15 下午
 * @Description
 */
public class ProjectGroupMemeberListVO extends PageRequest {

    @ApiModelProperty(value = "项目id")
    private Long projectId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @NotBlank(message = "instanceCode不能为空")
    @ApiModelProperty(value = "instanceCode")
    private String instanceCode;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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
