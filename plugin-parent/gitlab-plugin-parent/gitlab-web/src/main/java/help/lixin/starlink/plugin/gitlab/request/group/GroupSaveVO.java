package help.lixin.starlink.plugin.gitlab.request.group;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import help.lixin.starlink.plugin.gitlab.dto.project.Visibility;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/12 3:29 下午
 * @Description
 */
public class GroupSaveVO {

    @ApiModelProperty(value = "更新时需要给组表id")
    private Long groupId;

    @ApiModelProperty(value = "组名称")
    @Pattern(regexp = "^[-_a-zA-Z0-9|\\u4e00-\\u9fa5]*$", message = "只可以输入字母、数字、下划线、中划线和中文字符")
    @NotBlank(message = "groupName不能为空")
    private String groupName;

    @ApiModelProperty(value = "权限(PUBLIC, PRIVATE, INTERNAL)")
    @NotNull(message = "visibility不能为空")
    private Visibility visibility;

    @NotBlank(message = "path不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "只可以输入数字和字母")
    private String path;

    @NotNull(message = "状态不能为空")
    private Integer status;

    @NotNull(message = "instanceCode不能为空")
    private String instanceCode;

    private String remark;

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
