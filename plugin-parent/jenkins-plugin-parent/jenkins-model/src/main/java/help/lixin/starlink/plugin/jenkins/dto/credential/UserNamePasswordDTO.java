package help.lixin.starlink.plugin.jenkins.dto.credential;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/19 10:35 下午
 * @Description
 */
public class UserNamePasswordDTO {

    private String scope = "GLOBAL";
    private String username;
    private boolean usernameSecret = false;
    private String password;
    private String id;
    private String description;
    @JsonProperty("stapler-class")
    private String staplerClass = "com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl";

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isUsernameSecret() {
        return usernameSecret;
    }

    public void setUsernameSecret(boolean usernameSecret) {
        this.usernameSecret = usernameSecret;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
