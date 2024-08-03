package help.lixin.starlink.plugin.credential.domain.tls;

import help.lixin.starlink.plugin.credential.event.tls.ISysTLSCredentialEvent;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/24 下午7:36
 * @Description
 */
public class SysTLSCredentialUpdateEvent implements ISysTLSCredentialEvent {

    private Long id;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 证书
     */
    private String certificate;

    private String nameSpace;

    private String instanceCode;

    private String pluginCode;

    private String credentialKey;

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getCredentialKey() {
        return credentialKey;
    }

    public void setCredentialKey(String credentialKey) {
        this.credentialKey = credentialKey;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getPluginCode() {
        return pluginCode;
    }

    public void setPluginCode(String pluginCode) {
        this.pluginCode = pluginCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }
}
