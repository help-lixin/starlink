package help.lixin.starlink.plugin.jenkins.request.setup;

import java.io.Serializable;

import help.lixin.starlink.plugin.jenkins.domain.ToolsType;

public class JenkinsPipelineSetupShellVO extends JenkinsPipelineSetupCommonVO implements Serializable {

    /**
     * shell命令脚本
     *
     * Column: shell_script Nullable: true
     */
    private String shellScript;

    public JenkinsPipelineSetupShellVO() {
        setSetupType(ToolsType.SHELL);
    }

    public String getShellScript() {
        return shellScript;
    }

    public void setShellScript(String shellScript) {
        this.shellScript = shellScript;
    }
}