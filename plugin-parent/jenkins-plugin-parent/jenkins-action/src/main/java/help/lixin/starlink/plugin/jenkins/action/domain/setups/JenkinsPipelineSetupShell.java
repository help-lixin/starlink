package help.lixin.starlink.plugin.jenkins.action.domain.setups;

import java.io.Serializable;

public class JenkinsPipelineSetupShell extends JenkinsPipelineSetupCommon implements Serializable {
    private String shellScript;

    public String getShellScript() {
        return shellScript;
    }

    public void setShellScript(String shellScript) {
        this.shellScript = shellScript;
    }
}