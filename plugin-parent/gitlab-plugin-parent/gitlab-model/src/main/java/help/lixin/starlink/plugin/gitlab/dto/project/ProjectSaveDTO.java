package help.lixin.starlink.plugin.gitlab.dto.project;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/16 1:42 上午
 * @Description
 */
@Api(tags = "添加项目")
public class ProjectSaveDTO {

    private Long id;

    @NotBlank(message = "projectName不能为空")
    private String projectName;

    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty(value = "唯一标识符")
    private String path;

    @ApiModelProperty(value = "权限(PUBLIC, PRIVATE, INTERNAL)")
    @NotNull(message = "visibility不能为空")
    private Visibility visibility;

    @ApiModelProperty(value = "是否用readme初始化仓库 true:是 false:否")
    private Boolean initiallizeWithReadme;

    @ApiModelProperty(value = "使用用户id作为namespace")
    private Long namespaceByUser;

    @ApiModelProperty(value = "使用组id作为namespace")
    private Long namespaceByGroup;

    private Integer status;

    private String instanceCode;

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Boolean getInitiallizeWithReadme() {
        return initiallizeWithReadme == null ? false : initiallizeWithReadme;
    }

    public void setInitiallizeWithReadme(Boolean initiallizeWithReadme) {
        this.initiallizeWithReadme = initiallizeWithReadme;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getNamespaceByUser() {
        return namespaceByUser;
    }

    public void setNamespaceByUser(Long namespaceByUser) {
        this.namespaceByUser = namespaceByUser;
    }

    public Long getNamespaceByGroup() {
        return namespaceByGroup;
    }

    public void setNamespaceByGroup(Long namespaceByGroup) {
        this.namespaceByGroup = namespaceByGroup;
    }

}
