package help.lixin.starlink.plugin.jenkins.action.domain.setups;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import help.lixin.starlink.plugin.jenkins.domain.ToolsType;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "setupType")
@JsonSubTypes(value = {@JsonSubTypes.Type(value = JenkinsPipelineSetupAnt.class, name = "ANT"), //
        @JsonSubTypes.Type(value = JenkinsPipelineSetupGo.class, name = "GO"), //
        @JsonSubTypes.Type(value = JenkinsPipelineSetupGradle.class, name = "GRADLE"), //
        @JsonSubTypes.Type(value = JenkinsPipelineSetupMaven.class, name = "MAVEN"), //
        @JsonSubTypes.Type(value = JenkinsPipelineSetupNodejs.class, name = "NODE_JS"), //
        @JsonSubTypes.Type(value = JenkinsPipelineSetupPython.class, name = "PYTHON"), //
        @JsonSubTypes.Type(value = JenkinsPipelineSetupShell.class, name = "SHELL"), //
})
public class JenkinsPipelineSetupCommon implements Serializable {
    private ToolsType setupType;

    private Integer sequence = 1;

    public ToolsType getSetupType() {
        return setupType;
    }

    public void setSetupType(ToolsType setupType) {
        this.setupType = setupType;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
