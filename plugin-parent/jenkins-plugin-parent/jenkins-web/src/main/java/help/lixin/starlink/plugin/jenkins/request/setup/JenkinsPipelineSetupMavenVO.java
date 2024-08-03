package help.lixin.starlink.plugin.jenkins.request.setup;

import java.io.Serializable;

import help.lixin.starlink.plugin.jenkins.domain.ToolsType;

public class JenkinsPipelineSetupMavenVO extends JenkinsPipelineSetupCommonVO implements Serializable {

    private Long mavenId;

    /**
     * maven命令
     *
     * Column: goals Nullable: true
     */
    private String goals;

    public JenkinsPipelineSetupMavenVO() {
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