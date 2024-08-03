package help.lixin.starlink.plugin.credential.domain;

import java.io.Serializable;

/**
 * Table: sys_credential_text
 */
public class SysCredentialText extends SysCredentialCommon implements Serializable {

    /**
     * 密码
     *
     * Column:    password
     * Nullable:  true
     */
    private String password;

    /**
     * 凭证文本
     *
     * Column:    secret
     * Nullable:  true
     */
    private String secret;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret == null ? null : secret.trim();
    }
}