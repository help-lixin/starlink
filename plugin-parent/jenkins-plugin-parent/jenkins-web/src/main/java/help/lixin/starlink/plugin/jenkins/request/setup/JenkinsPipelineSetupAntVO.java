package help.lixin.starlink.plugin.jenkins.request.setup;

import java.io.Serializable;

import help.lixin.starlink.plugin.jenkins.domain.ToolsType;

public class JenkinsPipelineSetupAntVO extends JenkinsPipelineSetupCommonVO implements Serializable {

    /**
     * ant命令
     *
     * Column: targets Nullable: true
     */
    private String targets;

    private Long antId;

    public JenkinsPipelineSetupAntVO() {
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