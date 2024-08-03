package help.lixin.starlink.plugin.jenkins.action.domain.setups;

import java.io.Serializable;

public class JenkinsPipelineSetupNodejs extends JenkinsPipelineSetupCommon implements Serializable {

    private Long nodejsId;

    private String script;

    private String npmName;

    private String cacheLocation;

    public Long getNodejsId() {
        return nodejsId;
    }

    public void setNodejsId(Long nodejsId) {
        this.nodejsId = nodejsId;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getNpmName() {
        return npmName;
    }

    public void setNpmName(String npmName) {
        this.npmName = npmName;
    }

    public String getCacheLocation() {
        return cacheLocation;
    }

    public void setCacheLocation(String cacheLocation) {
        this.cacheLocation = cacheLocation;
    }
}