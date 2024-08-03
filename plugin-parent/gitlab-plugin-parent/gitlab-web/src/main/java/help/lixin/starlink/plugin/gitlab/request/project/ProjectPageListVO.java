package help.lixin.starlink.plugin.gitlab.request.project;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.gitlab4j.api.models.Visibility;

import help.lixin.starlink.request.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/6/5 4:50 下午
 * @Description
 */
@Api(tags = "项目分页查询")
public class ProjectPageListVO extends PageRequest {
    @NotBlank(message = "instanceCode不能为空")
    @ApiModelProperty(value = "实例配置")
    private String instanceCode;

    @ApiModelProperty(value = "权限(PUBLIC, PRIVATE, INTERNAL)")
    private Visibility visibility;

    @ApiModelProperty(value = "唯一标识符")
    @Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "只能包含大小写字母、数字、下划线")
    private String path;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "开始时间")
    private String beginTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
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
