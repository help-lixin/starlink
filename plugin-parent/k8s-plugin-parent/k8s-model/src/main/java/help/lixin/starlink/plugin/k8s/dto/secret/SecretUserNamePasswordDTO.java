package help.lixin.starlink.plugin.k8s.dto.secret;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/31 下午3:35
 * @Description
 */
public class SecretUserNamePasswordDTO {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
