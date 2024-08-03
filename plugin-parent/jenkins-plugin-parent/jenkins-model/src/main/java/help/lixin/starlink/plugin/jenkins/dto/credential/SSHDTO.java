package help.lixin.starlink.plugin.jenkins.dto.credential;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/19 11:04 下午
 * @Description
 */
public class SSHDTO {

    private String scope = "GLOBAL";
    private String id;
    private String description;
    private String username;
    private boolean usernameSecret = true;
    @JsonProperty("privateKeySource")
    private PrivateKeySourceDTO privateKeySource;
    private String passphrase;
    @JsonProperty("stapler-class")
    private String staplerClass = "com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey";

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
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

    public PrivateKeySourceDTO getPrivateKeySource() {
        return privateKeySource;
    }

    public void setPrivateKeySource(PrivateKeySourceDTO privateKeySource) {
        this.privateKeySource = privateKeySource;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public String getStaplerClass() {
        return staplerClass;
    }

    public void setStaplerClass(String staplerClass) {
        this.staplerClass = staplerClass;
    }
}
