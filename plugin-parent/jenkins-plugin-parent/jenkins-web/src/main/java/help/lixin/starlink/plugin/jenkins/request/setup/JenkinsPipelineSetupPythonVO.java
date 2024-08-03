package help.lixin.starlink.plugin.jenkins.request.setup;

import java.io.Serializable;

import help.lixin.starlink.plugin.jenkins.domain.ToolsType;

public class JenkinsPipelineSetupPythonVO extends JenkinsPipelineSetupCommonVO implements Serializable {

    private Long pythonId;

    /**
     * python脚本
     *
     * Column: script Nullable: true
     */
    private String script;

    public JenkinsPipelineSetupPythonVO() {
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