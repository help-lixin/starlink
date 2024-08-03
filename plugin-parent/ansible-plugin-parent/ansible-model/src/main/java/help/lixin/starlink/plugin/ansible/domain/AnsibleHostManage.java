package help.lixin.starlink.plugin.ansible.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Table: ansible_host_manage
 */
public class AnsibleHostManage implements Serializable {
    /**
     * ID
     *
     * Column:    id
     * Nullable:  false
     */
    private Long id;

    /**
     * 主机名称
     *
     * Column:    server_name
     * Nullable:  false
     */
    private String serverName;

    /**
     * Column:    ssh_instance_code
     * Nullable:  false
     */
    private String sshInstanceCode;

    /**
     * ansibleInventory目录
     *
     * Column:    ansible_inventory_dir
     * Nullable:  true
     */
    private String ansibleInventoryDir;

    /**
     * 状态值
     *
     * Column:    is_del
     * Nullable:  true
     */
    private Integer isDel;

    /**
     * 状态值
     *
     * Column:    status
     * Nullable:  true
     */
    private Integer status;

    /**
     * 创建人
     *
     * Column:    create_by
     * Nullable:  true
     */
    private String createBy;

    /**
     * 更新人
     *
     * Column:    update_by
     * Nullable:  true
     */
    private String updateBy;

    /**
     * 创建时间
     *
     * Column:    create_time
     * Nullable:  true
     */
    private Date createTime;

    /**
     * 更新时间
     *
     * Column:    update_time
     * Nullable:  true
     */
    private Date updateTime;

    /**
     * ansible的know_host文本
     *
     * Column:    know_host
     * Nullable:  true
     */
    private String knowHost;

    private static final long serialVersionUID = 1L;

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
        this.serverName = serverName == null ? null : serverName.trim();
    }

    public String getSshInstanceCode() {
        return sshInstanceCode;
    }

    public void setSshInstanceCode(String sshInstanceCode) {
        this.sshInstanceCode = sshInstanceCode == null ? null : sshInstanceCode.trim();
    }

    public String getAnsibleInventoryDir() {
        return ansibleInventoryDir;
    }

    public void setAnsibleInventoryDir(String ansibleInventoryDir) {
        this.ansibleInventoryDir = ansibleInventoryDir == null ? null : ansibleInventoryDir.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
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
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getKnowHost() {
        return knowHost;
    }

    public void setKnowHost(String knowHost) {
        this.knowHost = knowHost == null ? null : knowHost.trim();
    }
}