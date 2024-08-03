package help.lixin.starlink.plugin.jenkins.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/19 11:04 下午
 * @Description
 */
public class SshModel {

    private String scope = "GLOBAL";
    private String id;
    private String description;
    private String username;
    private boolean usernameSecret = true;
    @JsonProperty("privateKeySource")
    private PrivateKeySource privateKeySource;
    private String passphrase;
    @JsonProperty("stapler-class")
    private String staplerClass = "com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey";
    @JsonProperty("$redact")
    private String readct = "passphrase";
    @JsonProperty("Submit")
    private String submit = "";

    public String getReadct() {
        return readct;
    }

    public void setReadct(String readct) {
        this.readct = readct;
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }

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

    public PrivateKeySource getPrivateKeySource() {
        return privateKeySource;
    }

    public void setPrivateKeySource(PrivateKeySource privateKeySource) {
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
