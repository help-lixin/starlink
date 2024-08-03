package help.lixin.starlink.plugin.jenkins.request.setup;

import java.io.Serializable;

import help.lixin.starlink.plugin.jenkins.domain.ToolsType;

/**
 * Table: jenkins_pipeline_setup_go
 */
public class JenkinsPipelineSetupGoVO extends JenkinsPipelineSetupCommonVO implements Serializable {

    private Long goId;

    /**
     * go脚本
     *
     * Column: script Nullable: true
     */
    private String script;

    public JenkinsPipelineSetupGoVO() {
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