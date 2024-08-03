package help.lixin.starlink.plugin.credential.domain.secret;

import help.lixin.starlink.plugin.credential.event.secret.ISysSecretCredentialEvent;

/**
 * @Author: 伍岳林
 * @Date: 2024/6/7 上午10:37
 * @Description
 */
public class SysSecretCredentialDeleteEvent implements ISysSecretCredentialEvent {

    private String credentialKey;

    private String instanceCode;

    private String pluginCode;

    private String nameSpace;

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getPluginCode() {
        return pluginCode;
    }

    public void setPluginCode(String pluginCode) {
        this.pluginCode = pluginCode;
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
}
