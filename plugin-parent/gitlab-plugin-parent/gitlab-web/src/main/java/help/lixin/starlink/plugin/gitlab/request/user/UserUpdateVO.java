package help.lixin.starlink.plugin.gitlab.request.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/12 4:29 下午
 * @Description
 */
public class UserUpdateVO {

    @NotNull(message = "id不能为空")
    private Long id;

    @NotBlank(message = "名称不能为空")
    private String userName;

    private String nickName;

    private String email;

    private String pwd;

    private String token;

    @NotBlank(message = "instanceCode不能为空")
    private String instanceCode;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
