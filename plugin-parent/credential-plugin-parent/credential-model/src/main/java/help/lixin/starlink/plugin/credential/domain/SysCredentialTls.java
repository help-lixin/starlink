package help.lixin.starlink.plugin.credential.domain;

/**
 * Table: sys_credential_tls
 */
public class SysCredentialTls extends SysCredentialCommon {

    /**
     * 私钥
     *
     * Column:    private_key
     * Nullable:  true
     */
    private String privateKey;

    /**
     * 证书
     *
     * Column:    certificate
     * Nullable:  true
     */
    private String certificate;

    private static final long serialVersionUID = 1L;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey == null ? null : privateKey.trim();
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate == null ? null : certificate.trim();
    }
}