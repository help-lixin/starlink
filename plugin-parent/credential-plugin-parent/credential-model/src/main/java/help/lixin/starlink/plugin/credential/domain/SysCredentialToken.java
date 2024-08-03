package help.lixin.starlink.plugin.credential.domain;

import java.io.Serializable;

/**
 * Table: sys_credential_token
 */
public class SysCredentialToken extends SysCredentialCommon implements Serializable {

    /**
     * Column:    user_name
     * Nullable:  true
     */
    private String userName;

    /**
     * Column:    token
     * Nullable:  true
     */
    private String token;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }
}