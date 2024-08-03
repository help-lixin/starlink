package help.lixin.starlink.plugin.jenkins.dto.setup;

import help.lixin.starlink.plugin.jenkins.domain.ToolsType;

import java.io.Serializable;

public class JenkinsPipelineSetupShellDTO extends JenkinsPipelineSetupCommonDTO implements Serializable {

    /**
     * shell命令脚本
     *
     * Column:    shell_script
     * Nullable:  true
     */
    private String shellScript;

    public JenkinsPipelineSetupShellDTO() {
        setSetupType(ToolsType.SHELL);
    }

    public String getShellScript() {
        return shellScript;
    }

    public void setShellScript(String shellScript) {
        this.shellScript = shellScript;
    }
}