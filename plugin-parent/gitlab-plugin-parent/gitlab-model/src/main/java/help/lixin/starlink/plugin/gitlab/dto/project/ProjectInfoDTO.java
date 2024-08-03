package help.lixin.starlink.plugin.gitlab.dto.project;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/26 5:17 下午
 * @Description
 */
public class ProjectInfoDTO {
    private String instanceCode;

    private Long projectId;

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
