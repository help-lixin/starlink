package help.lixin.starlink.plugin.harbor.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/25 2:29 下午
 * @Description
 */
@Api(tags = "创建harbor项目对象")
public class CreateProjectVO {

    /**
     * 项目名
     */
    @NotBlank(message = "projectName不能为空")
    @ApiModelProperty(value = "项目名")
    private String projectName;

    /**
     * 容量
     */
    @NotBlank(message = "capacity不能为空")
    @ApiModelProperty(value = "容量(以字节为单位)")
    private String capacity;

    @ApiModelProperty(value = "是否为public")
    private Integer isPublic = 0;

    @ApiModelProperty(value = "镜像目标id")
    private Long registryId;

    @NotBlank(message = "实例编码不能为空")
    private String instanceCode;

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public Long getRegistryId() {
        return registryId;
    }

    public void setRegistryId(Long registryId) {
        this.registryId = registryId;
    }
}
