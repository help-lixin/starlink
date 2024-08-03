package help.lixin.starlink.plugin.ansible.dto;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/29 下午4:56
 * @Description
 */
public class CreateHostDTO {

    private Long id;

    private String serverName;

    private String sshInstanceCode;

    private String ansibleInventoryDir;

    private Integer status;

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
