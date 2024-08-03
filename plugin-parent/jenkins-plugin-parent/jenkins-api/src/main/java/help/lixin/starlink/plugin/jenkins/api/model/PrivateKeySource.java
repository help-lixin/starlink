package help.lixin.starlink.plugin.jenkins.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/19 11:05 下午
 * @Description
 */
public class PrivateKeySource {

    private String value = "0";
    private String privateKey;
    @JsonProperty("stapler-class")
    private String staplerClass = "com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey$DirectEntryPrivateKeySource";
    @JsonProperty("$class")
    private String clazz = "com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey$DirectEntryPrivateKeySource";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getStaplerClass() {
        return staplerClass;
    }

    public void setStaplerClass(String staplerClass) {
        this.staplerClass = staplerClass;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
