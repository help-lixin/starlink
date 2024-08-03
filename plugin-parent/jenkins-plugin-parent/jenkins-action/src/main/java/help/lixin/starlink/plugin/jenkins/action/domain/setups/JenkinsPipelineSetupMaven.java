package help.lixin.starlink.plugin.jenkins.action.domain.setups;

import java.io.Serializable;

public class JenkinsPipelineSetupMaven extends JenkinsPipelineSetupCommon implements Serializable {
    private Long mavenId;
    private String goals;

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