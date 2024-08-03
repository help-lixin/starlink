package help.lixin.starlink.plugin.ansible.request;

import javax.validation.constraints.NotBlank;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/29 下午4:56
 * @Description
 */
public class CreateHostVO {

    private Long id;

    @NotBlank(message = "服务名称不能为空")
    private String serverName;

    @NotBlank(message = "实例编码不能为空")
    private String sshInstanceCode;

    @NotBlank(message = "inventory目录不能为空")
    private String ansibleInventoryDir;

    private Integer status = 1;

    private String createBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getSshInstanceCode() {
        return sshInstanceCode;
    }

    public void setSshInstanceCode(String sshInstanceCode) {
        this.sshInstanceCode = sshInstanceCode;
    }

    public String getAnsibleInventoryDir() {
        return ansibleInventoryDir;
    }

    public void setAnsibleInventoryDir(String ansibleInventoryDir) {
        this.ansibleInventoryDir = ansibleInventoryDir;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
