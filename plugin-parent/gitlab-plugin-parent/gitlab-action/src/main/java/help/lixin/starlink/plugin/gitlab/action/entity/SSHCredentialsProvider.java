package help.lixin.starlink.plugin.gitlab.action.entity;

/**
 * ssh
 */
public class SSHCredentialsProvider implements CredentialsProvider {
    private String type = "ssh";

    // 公钥
    private String publicKey;
    // 私钥
    private String privateKey;
    // 私钥的密钥
    private String privateKeyPassphrase;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPrivateKeyPassphrase() {
        return privateKeyPassphrase;
    }

    public void setPrivateKeyPassphrase(String privateKeyPassphrase) {
        this.privateKeyPassphrase = privateKeyPassphrase;
    }
}
