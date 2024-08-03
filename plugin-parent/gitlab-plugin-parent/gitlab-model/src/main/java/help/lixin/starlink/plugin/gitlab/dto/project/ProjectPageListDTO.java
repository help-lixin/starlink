package help.lixin.starlink.plugin.gitlab.dto.project;

import help.lixin.starlink.core.dto.PageDTO;
import io.swagger.annotations.Api;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/23 3:33 下午
 * @Description
 */
@Api(tags = "项目分页查询")
public class ProjectPageListDTO extends PageDTO {

    private Long projectId;

    private String userName;

    private String instanceCode;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

}
