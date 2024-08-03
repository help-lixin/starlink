package help.lixin.starlink.plugin.jenkins.dto.setup;

import help.lixin.starlink.plugin.jenkins.domain.ToolsType;

import java.io.Serializable;

public class JenkinsPipelineSetupPythonDTO extends JenkinsPipelineSetupCommonDTO implements Serializable {

    private Long pythonId;

    /**
     * python脚本
     *
     * Column:    script
     * Nullable:  true
     */
    private String script;

    public JenkinsPipelineSetupPythonDTO() {
        setSetupType(ToolsType.PYTHON);
    }

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