package help.lixin.starlink.plugin.harbor.request;

import javax.validation.constraints.NotBlank;

import help.lixin.starlink.request.PageRequest;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/15 下午6:23
 * @Description
 */
public class PageListVO extends PageRequest {

    private String projectName;

    private Integer isPublic;

    @NotBlank(message = "实例编码不能为空")
    private String instanceCode;

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
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
