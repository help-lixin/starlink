package help.lixin.starlink.plugin.jenkins.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * Table: jenkins_pipeline_setup_go
 */
public class JenkinsPipelineSetupGo extends JenkinsPipelineSetupCommon implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long goId;

    /**
     * go脚本
     *
     * Column:    script
     * Nullable:  true
     */
    private String script;

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