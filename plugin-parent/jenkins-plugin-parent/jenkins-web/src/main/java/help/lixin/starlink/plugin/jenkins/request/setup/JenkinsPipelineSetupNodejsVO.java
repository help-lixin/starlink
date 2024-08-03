package help.lixin.starlink.plugin.jenkins.request.setup;

import java.io.Serializable;

import help.lixin.starlink.plugin.jenkins.domain.ToolsType;

public class JenkinsPipelineSetupNodejsVO extends JenkinsPipelineSetupCommonVO implements Serializable {

    private Long nodejsId;

    /**
     * 脚本命令
     *
     * Column: script Nullable: true
     */
    private String script;

    /**
     * 缓存路径
     *
     * Column: cache_location Nullable: true
     */
    private String cacheLocation;

    public JenkinsPipelineSetupNodejsVO() {
        setSetupType(ToolsType.NODE_JS);
    }

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

    public String getCacheLocation() {
        return cacheLocation;
    }

    public void setCacheLocation(String cacheLocation) {
        this.cacheLocation = cacheLocation;
    }
}