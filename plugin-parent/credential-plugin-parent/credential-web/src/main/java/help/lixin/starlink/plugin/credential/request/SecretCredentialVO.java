package help.lixin.starlink.plugin.credential.request;

import help.lixin.starlink.plugin.credential.enums.CredentialEnum;

public class SecretCredentialVO extends CredentialVO {
    private String secret;
    private String password;

    public SecretCredentialVO() {
        setCredentialType(CredentialEnum.SECRET);
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "SecretCredentialEvent{" +
                "secretText='" + secret + '\'' +
                ", password='" + password + '\'' +
                ", pluginCode='" + pluginCode + '\'' +
                ", instanceCode='" + instanceCode + '\'' +
                ", credentialName='" + credentialName + '\'' +
                ", credentialKey='" + credentialKey + '\'' +
                ", desc='" + remark + '\'' +
                '}';
    }
}
