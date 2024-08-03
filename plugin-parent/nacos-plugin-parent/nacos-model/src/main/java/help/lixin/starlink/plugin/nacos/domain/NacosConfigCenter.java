package help.lixin.starlink.plugin.nacos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Table: nacos_config_center
 */
public class NacosConfigCenter implements Serializable {
    /**
     * ID
     *
     * Column:    id
     * Nullable:  false
     */
    private Long id;

    /**
     * Column:    env_code
     * Nullable:  true
     */
    private String envCode;

    /**
     * Column:    group_code
     * Nullable:  true
     */
    private String groupCode;

    /**
     * Column:    instance_code
     * Nullable:  true
     */
    private String instanceCode;

    /**
     * 配置名称
     *
     * Column:    data_id
     * Nullable:  true
     */
    private String dataId;

    /**
     * 组名称
     *
     * Column:    group_name
     * Nullable:  true
     */
    private String groupName;

    /**
     * 租户id
     *
     * Column:    tenant
     * Nullable:  true
     */
    private String tenant;

    /**
     * Column:    md5
     * Nullable:  true
     */
    private String md5;

    /**
     * 归属应用
     *
     * Column:    app_name
     * Nullable:  true
     */
    private String appName;

    /**
     * 标签
     *
     * Column:    config_tags
     * Nullable:  true
     */
    private String configTags;

    /**
     * 描述
     *
     * Column:    remark
     * Nullable:  true
     */
    private String remark;

    /**
     * 配置格式
     *
     * Column:    type
     * Nullable:  true
     */
    private String type;

    /**
     * 状态值
     *
     * Column:    status
     * Nullable:  true
     */
    private Byte status;

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
     * Column:    created_time
     * Nullable:  true
     */
    private Date createdTime;

    /**
     * 更新时间
     *
     * Column:    updated_time
     * Nullable:  true
     */
    private Date updatedTime;

    /**
     * 配置信息
     *
     * Column:    content
     * Nullable:  true
     */
    private String content;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnvCode() {
        return envCode;
    }

    public void setEnvCode(String envCode) {
        this.envCode = envCode == null ? null : envCode.trim();
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode == null ? null : groupCode.trim();
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode == null ? null : instanceCode.trim();
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId == null ? null : dataId.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant == null ? null : tenant.trim();
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5 == null ? null : md5.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getConfigTags() {
        return configTags;
    }

    public void setConfigTags(String configTags) {
        this.configTags = configTags == null ? null : configTags.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}