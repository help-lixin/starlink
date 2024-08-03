package help.lixin.starlink.plugin.gitlab.request.user;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/9 2:58 下午
 * @Description
 */
public class UserVO {
    @NotBlank(message = "电子邮件不能为空")
    @Email(message = "请确认邮件格式")
    private String email;

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "昵称不能为空")
    private String nickName;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度须在[6,20]")
    private String pwd;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
