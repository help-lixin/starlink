package help.lixin.starlink.plugin.jenkins.dto.credential;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/19 11:31 下午
 * @Description
 */
public class SecretDTO {

    private String scope = "GLOBAL";
    private String secret;
    private String id;
    private String description;
    @JsonProperty("stapler-class")
    private String staplerClass = "org.jenkinsci.plugins.plaincredentials.impl.StringCredentialsImpl";

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStaplerClass() {
        return staplerClass;
    }

    public void setStaplerClass(String staplerClass) {
        this.staplerClass = staplerClass;
    }
}
