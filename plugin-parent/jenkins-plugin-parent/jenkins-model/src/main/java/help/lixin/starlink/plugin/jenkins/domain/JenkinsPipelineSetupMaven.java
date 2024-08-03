package help.lixin.starlink.plugin.jenkins.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

public class JenkinsPipelineSetupMaven extends JenkinsPipelineSetupCommon implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long mavenId;

    /**
     * maven命令
     *
     * Column:    goals
     * Nullable:  true
     */
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