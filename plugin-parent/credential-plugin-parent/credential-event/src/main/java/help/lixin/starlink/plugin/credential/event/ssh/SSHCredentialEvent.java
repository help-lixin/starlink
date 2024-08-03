package help.lixin.starlink.plugin.credential.event.ssh;

import help.lixin.starlink.plugin.credential.event.AbstractEvent;

public class SSHCredentialEvent extends AbstractEvent {
    // 用户名
    private String username;
    // 私钥
    private String privatekey;
    // 私钥对应的密钥
    private String passphrase;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrivatekey() {
        return privatekey;
    }

    public void setPrivatekey(String privatekey) {
        this.privatekey = privatekey;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    @Override
    public String toString() {
        return "SSHCredentialEvent{" +
                "username='" + username + '\'' +
                ", privatekey='" + privatekey + '\'' +
                ", passphrase='" + passphrase + '\'' +
                ", pluginCode='" + pluginCode + '\'' +
                ", instanceCode='" + instanceCode + '\'' +
                ", credentialName='" + credentialName + '\'' +
                ", credentialKey='" + credentialKey + '\'' +
                ", desc='" + remark + '\'' +
                '}';
    }
}
