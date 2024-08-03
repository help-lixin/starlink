package help.lixin.starlink.plugin.jenkins.dto.setup;

import help.lixin.starlink.plugin.jenkins.domain.ToolsType;

import java.io.Serializable;

/**
 * Table: jenkins_pipeline_setup_go
 */
public class JenkinsPipelineSetupGoDTO extends JenkinsPipelineSetupCommonDTO implements Serializable {

    private Long goId;

    /**
     * go脚本
     *
     * Column:    script
     * Nullable:  true
     */
    private String script;

    public JenkinsPipelineSetupGoDTO() {
        setSetupType(ToolsType.GO);
    }


    public Long getGoId() {
        return goId;
    }

    public void setGoId(Long goId) {
        this.goId = goId;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}