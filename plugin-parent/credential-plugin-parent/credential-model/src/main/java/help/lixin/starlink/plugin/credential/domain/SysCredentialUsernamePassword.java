package help.lixin.starlink.plugin.credential.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * Table: sys_credential_username_password
 */
public class SysCredentialUsernamePassword extends SysCredentialCommon implements Serializable {

    /**
     * 用户名
     *
     * Column:    user_name
     * Nullable:  true
     */
    private String userName;

    /**
     * 密码
     *
     * Column:    password
     * Nullable:  true
     */
    private String password;

    /**
     * 镜像域名
     *
     * Column:    img_domain
     * Nullable:  true
     */
    private String imgDomain;

    public String getImgDomain() {
        return imgDomain;
    }

    public void setImgDomain(String imgDomain) {
        this.imgDomain = imgDomain;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}