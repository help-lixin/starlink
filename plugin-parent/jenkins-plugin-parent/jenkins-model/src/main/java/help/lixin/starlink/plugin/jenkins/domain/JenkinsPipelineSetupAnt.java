package help.lixin.starlink.plugin.jenkins.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

public class JenkinsPipelineSetupAnt extends JenkinsPipelineSetupCommon implements Serializable {

    /**
     * ant命令
     *
     * Column:    targets
     * Nullable:  true
     */
    private String targets;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long antId;

    public Long getAntId() {
        return antId;
    }

    public void setAntId(Long antId) {
        this.antId = antId;
    }

    public String getTargets() {
        return targets;
    }

    public void setTargets(String targets) {
        this.targets = targets;
    }
}