package help.lixin.starlink.plugin.jenkins.dto.setup;

import help.lixin.starlink.plugin.jenkins.domain.ToolsType;

import java.io.Serializable;

public class JenkinsPipelineSetupMavenDTO extends JenkinsPipelineSetupCommonDTO implements Serializable {

    private Long mavenId;

    /**
     * maven命令
     *
     * Column:    goals
     * Nullable:  true
     */
    private String goals;


    public JenkinsPipelineSetupMavenDTO() {
        setSetupType(ToolsType.MAVEN);
    }

    public Long getMavenId() {
        return mavenId;
    }

    public void setMavenId(Long mavenId) {
        this.mavenId = mavenId;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }
}