package help.lixin.starlink.plugin.gitlab.request.user;

import javax.validation.constraints.NotBlank;

import help.lixin.starlink.request.PageRequest;

/**
 * @Author: 伍岳林
 * @Date: 2023/11/17 3:47 下午
 * @Description
 */
public class UserPageListVO extends PageRequest {

    private String userName;

    private String email;

    @NotBlank(message = "instanceCode不能为空")
    private String instanceCode;

    private Integer status;

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
