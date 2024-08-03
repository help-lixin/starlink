package help.lixin.starlink.plugin.jenkins.action.domain.setups;

import java.io.Serializable;

public class JenkinsPipelineSetupGo extends JenkinsPipelineSetupCommon implements Serializable {

    private Long goId;
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