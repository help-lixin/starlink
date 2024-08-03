package help.lixin.starlink.plugin.jenkins.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

public class JenkinsPipelineSetupNodejs extends JenkinsPipelineSetupCommon implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long nodejsId;

    /**
     * 脚本命令
     *
     * Column:    script
     * Nullable:  true
     */
    private String script;

    /**
     * 缓存路径
     *
     * Column:    cache_location
     * Nullable:  true
     */
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

    public String getCacheLocation() {
        return cacheLocation;
    }

    public void setCacheLocation(String cacheLocation) {
        this.cacheLocation = cacheLocation;
    }
}