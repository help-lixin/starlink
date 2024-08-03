package help.lixin.starlink.plugin.credential.request;

import help.lixin.starlink.plugin.credential.enums.CredentialEnum;

public class SSHCredentialVO extends CredentialVO {
    // 用户名
    private String userName;
    // 私钥
    private String privateKey;
    // 私钥对应的密钥
    private String passphrase;

    private String publicKey;

    public SSHCredentialVO() {
        setCredentialType(CredentialEnum.SSH);
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
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
                "username='" + userName + '\'' +
                ", privatekey='" + privateKey + '\'' +
                ", passphrase='" + passphrase + '\'' +
                ", pluginCode='" + pluginCode + '\'' +
                ", instanceCode='" + instanceCode + '\'' +
                ", credentialName='" + credentialName + '\'' +
                ", credentialKey='" + credentialKey + '\'' +
                ", desc='" + remark + '\'' +
                '}';
    }
}
