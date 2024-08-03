package help.lixin.starlink.plugin.jenkins.dto.setup;

import help.lixin.starlink.plugin.jenkins.domain.ToolsType;

import java.io.Serializable;

public class JenkinsPipelineSetupAntDTO extends JenkinsPipelineSetupCommonDTO implements Serializable {

    /**
     * ant命令
     *
     * Column:    targets
     * Nullable:  true
     */
    private String targets;

    private Long antId;

    public JenkinsPipelineSetupAntDTO() {
        setSetupType(ToolsType.ANT);
    }

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