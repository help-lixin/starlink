package help.lixin.starlink.plugin.jenkins.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * Table: jenkins_system_config
 */
public class JenkinsSystemConfig implements Serializable {
    /**
     * ID
     *
     * Column: id Nullable: false
     */
    @TableId(type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 插件名称
     *
     * Column: name Nullable: true
     */
    private String name;

    /**
     * 插件路径
     *
     * Column: value Nullable: true
     */
    private String value;

    /**
     * 插件类型
     * <p>
     * Column: plugin_type Nullable: true
     */
    private JenkinsPluginTypeEnum pluginType;

    /**
     * Column: instance_code Nullable: true
     */
    private String instanceCode;

    /**
     * 状态值
     *
     * Column: status Nullable: true
     */
    private Integer status;

    private Integer isDel;

    /**
     * 创建人
     *
     * Column: create_by Nullable: true
     */
    private String createBy;

    /**
     * 更新人
     *
     * Column: update_by Nullable: true
     */
    private String updateBy;

    /**
     * 创建时间
     *
     * Column: create_time Nullable: true
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     *
     * Column: update_time Nullable: true
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public JenkinsPluginTypeEnum getPluginType() {
        return pluginType;
    }

    public void setPluginType(JenkinsPluginTypeEnum pluginType) {
        this.pluginType = pluginType;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode == null ? null : instanceCode.trim();
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
}