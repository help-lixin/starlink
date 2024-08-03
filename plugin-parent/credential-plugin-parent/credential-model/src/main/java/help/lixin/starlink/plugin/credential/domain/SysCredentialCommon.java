package help.lixin.starlink.plugin.credential.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * Table: sys_credential_common
 */
public class SysCredentialCommon implements Serializable {
    /**
     * ID
     *
     * Column:    id
     * Nullable:  false
     */
    @TableId(type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 凭证唯一名称
     *
     * Column:    credential_key
     * Nullable:  true
     */
    private String credentialKey;

    /**
     * 凭证别名
     *
     * Column:    credential_name
     * Nullable:  true
     */
    private String credentialName;

    /**
     * 凭证类型
     *
     * Column:    credential_type
     * Nullable:  true
     */
    private String credentialType;

    /**
     * 描述
     *
     * Column:    remark
     * Nullable:  true
     */
    private String remark;

    /**
     * 命名空间
     *
     * Column:    name_space
     * Nullable:  true
     */
    private String nameSpace;

    /**
     * 实例编码
     *
     * Column:    instance_code
     * Nullable:  true
     */
    private String instanceCode;

    /**
     * 插件编码
     *
     * Column:    plugin_code
     * Nullable:  true
     */
    private String pluginCode;

    /**
     * 是否删除（删除为1）
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
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     *
     * Column:    update_time
     * Nullable:  true
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;


    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCredentialKey() {
        return credentialKey;
    }

    public void setCredentialKey(String credentialKey) {
        this.credentialKey = credentialKey == null ? null : credentialKey.trim();
    }

    public String getCredentialName() {
        return credentialName;
    }

    public void setCredentialName(String credentialName) {
        this.credentialName = credentialName == null ? null : credentialName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode == null ? null : instanceCode.trim();
    }

    public String getPluginCode() {
        return pluginCode;
    }

    public void setPluginCode(String pluginCode) {
        this.pluginCode = pluginCode == null ? null : pluginCode.trim();
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

    public String getCredentialType() {
        return credentialType;
    }

    public void setCredentialType(String credentialType) {
        this.credentialType = credentialType;
    }
}