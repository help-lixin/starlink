package help.lixin.starlink.plugin.credential.dto;

public class SysCredentialTlsDTO extends SysCredentialCommonDTO {
    private Long id;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 证书
     */
    private String certificate;

    private static final long serialVersionUID = 1L;

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
        this.privateKey = privateKey == null ? null : privateKey.trim();
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate == null ? null : certificate.trim();
    }
}