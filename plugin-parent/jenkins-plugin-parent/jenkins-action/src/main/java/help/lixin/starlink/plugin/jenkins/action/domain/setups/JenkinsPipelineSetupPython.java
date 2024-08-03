package help.lixin.starlink.plugin.jenkins.action.domain.setups;

import java.io.Serializable;

public class JenkinsPipelineSetupPython extends JenkinsPipelineSetupCommon implements Serializable {

    private Long pythonId;

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