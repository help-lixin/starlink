package help.lixin.starlink.plugin.credential.request;

import help.lixin.starlink.plugin.credential.enums.CredentialEnum;

import java.util.HashMap;
import java.util.Map;

public class SysCredentialTlsVO extends CredentialVO {

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 证书
     */
    private String certificate;

    private Map<String,String> dataMap = new HashMap<>();

    public Map<String, String> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, String> dataMap) {
        this.dataMap = dataMap;
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

    public SysCredentialTlsVO() {
        setCredentialType(CredentialEnum.TLS);
    }
}