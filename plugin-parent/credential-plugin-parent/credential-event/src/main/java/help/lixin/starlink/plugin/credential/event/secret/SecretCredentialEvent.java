package help.lixin.starlink.plugin.credential.event.secret;

import help.lixin.starlink.plugin.credential.event.AbstractEvent;

public class SecretCredentialEvent extends AbstractEvent {
    private String secret;
    private String password;

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
