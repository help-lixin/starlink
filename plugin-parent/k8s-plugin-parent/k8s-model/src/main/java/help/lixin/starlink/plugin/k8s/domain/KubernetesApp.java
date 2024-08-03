package help.lixin.starlink.plugin.k8s.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Table: kubernetes_app
 */
public class KubernetesApp implements Serializable {
    /**
     * ID
     *
     * Column: id Nullable: false
     */
    private Long id;

    /**
     * 命名空间id
     *
     * Column: name_space_id Nullable: false
     */
    private Long nameSpaceId;

    /**
     * 部署种类
     *
     * Column: kind Nullable: false
     */
    private String kind;

    /**
     * 应用名称
     *
     * Column: name Nullable: false
     */
    private String name;

    /**
     * 实例编码
     *
     * Column: instance_code Nullable: false
     */
    private String instanceCode;

    /**
     * 是否删除（删除为1）
     *
     * Column: is_del Nullable: true
     */
    private Integer isDel;

    /**
     * 状态值
     *
     * Column: status Nullable: true
     */
    private Integer status;

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
    private Date createTime;

    /**
     * 更新时间
     *
     * Column: update_time Nullable: true
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNameSpaceId() {
        return nameSpaceId;
    }

    public void setNameSpaceId(Long nameSpaceId) {
        this.nameSpaceId = nameSpaceId;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind == null ? null : kind.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode == null ? null : instanceCode.trim();
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
}