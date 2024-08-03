package help.lixin.starlink.plugin.gitlab.request.user;

/**
 * @Author: 伍岳林
 * @Date: 2023/11/29 2:42 下午
 * @Description
 */
public class UserChangeStatusVO {

    private Integer status;

    private Long userId;

    private String instanceCode;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }
}
