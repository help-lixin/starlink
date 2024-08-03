package help.lixin.starlink.plugin.gitlab.dto.project;

import help.lixin.starlink.core.dto.PageDTO;
import io.swagger.annotations.Api;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/23 3:33 下午
 * @Description
 */
@Api(tags = "项目组查询")
public class ProjectGroupListDTO extends PageDTO {

    private Long projectId;

    private String projectName;

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
