package help.lixin.starlink.plugin.credential.domain;

import java.io.Serializable;

/**
 * Table: sys_credential_ssh
 */
public class SysCredentialSsh extends SysCredentialCommon implements Serializable {

    /**
     * 用户名
     *
     * Column:    user_name
     * Nullable:  true
     */
    private String userName;

    /**
     * 私钥密码
     *
     * Column:    passphrase
     * Nullable:  true
     */
    private String passphrase;

    /**
     * 私钥
     *
     * Column:    private_key
     * Nullable:  true
     */
    private String privateKey;

    /**
     * 公钥
     *
     * Column:    public_key
     * Nullable:  true
     */
    private String publicKey;

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
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase == null ? null : passphrase.trim();
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey == null ? null : privateKey.trim();
    }
}