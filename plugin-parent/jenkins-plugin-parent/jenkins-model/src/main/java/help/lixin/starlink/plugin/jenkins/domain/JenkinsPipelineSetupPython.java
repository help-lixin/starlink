package help.lixin.starlink.plugin.jenkins.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

public class JenkinsPipelineSetupPython extends JenkinsPipelineSetupCommon implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long pythonId;

    /**
     * python脚本
     *
     * Column:    script
     * Nullable:  true
     */
    private String script;

    public Long getPythonId() {
        return pythonId;
    }

    public void setPythonId(Long pythonId) {
        this.pythonId = pythonId;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}