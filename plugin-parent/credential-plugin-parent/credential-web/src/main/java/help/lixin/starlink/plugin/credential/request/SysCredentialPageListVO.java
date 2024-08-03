package help.lixin.starlink.plugin.credential.request;

import help.lixin.starlink.request.PageRequest;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/25 4:10 下午
 * @Description
 */
public class SysCredentialPageListVO extends PageRequest {

    private String credentialKey;

    private String credentialName;

    private String credentialType;

    private String instanceCode;

    public String getCredentialType() {
        return credentialType;
    }

    public void setCredentialType(String credentialType) {
        this.credentialType = credentialType;
    }

    public String getCredentialKey() {
        return credentialKey;
    }

    public void setCredentialKey(String credentialKey) {
        this.credentialKey = credentialKey;
    }

    public String getCredentialName() {
        return credentialName;
    }

    public void setCredentialName(String credentialName) {
        this.credentialName = credentialName;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }
}
