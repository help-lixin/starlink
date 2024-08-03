package help.lixin.starlink.plugin.jenkins.domain;

import java.io.Serializable;

public class JenkinsPipelineSetupShell extends JenkinsPipelineSetupCommon implements Serializable {

    /**
     * shell命令脚本
     *
     * Column:    shell_script
     * Nullable:  true
     */
    private String shellScript;


    public String getShellScript() {
        return shellScript;
    }

    public void setShellScript(String shellScript) {
        this.shellScript = shellScript;
    }
}