package help.lixin.starlink.plugin.k8s.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * Table: kubernetes_deploy_template
 */
public class KubernetesDeployTemplate implements Serializable {
    /**
     * ID
     * <p>
     * Column:    deploy_id
     * Nullable:  false
     */
    @TableId(value = "deployment_id", type = IdType.AUTO)
    private Long deployId;

    /**
     * 部署别名
     * <p>
     * Column:    deploy_name
     * Nullable:  false
     */
    @TableField("deploy_name")
    private String deployName;
    /**
     * yaml内容
     * <p>
     * Column:    yaml_content
     * Nullable:  false
     */
    @TableField("yaml_content")
    private String yamlContent;
    /**
     * 备注
     * <p>
     * Column:    remark
     * Nullable:  false
     */
    @TableField("remark")
    private String remark;
    /**
     * 是否删除（删除为1）
     * <p>
     * Column:    is_del
     * Nullable:  true
     */
    @TableField("is_del")
    private Integer isDel;

    /**
     * 状态值
     * <p>
     * Column:    status
     * Nullable:  true
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建人
     * <p>
     * Column:    create_by
     * Nullable:  true
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 更新人
     * <p>
     * Column:    update_by
     * Nullable:  true
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 创建时间
     * <p>
     * Column:    create_time
     * Nullable:  true
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     * <p>
     * Column:    update_time
     * Nullable:  true
     */
    @TableField("update_time")
    private Date updateTime;

    public Long getDeployId() {
        return deployId;
    }

    public void setDeployId(Long deployId) {
        this.deployId = deployId;
    }

    public String getDeployName() {
        return deployName;
    }

    public void setDeployName(String deployName) {
        this.deployName = deployName;
    }

    public String getYamlContent() {
        return yamlContent;
    }

    public void setYamlContent(String yamlContent) {
        this.yamlContent = yamlContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
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

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}
