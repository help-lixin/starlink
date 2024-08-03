package help.lixin.starlink.plugin.harbor.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * Table: harbor_project
 */
public class HarborProject implements Serializable {
    /**
     * harbor_config_id
     *
     * Column: id Nullable: false
     */
    @TableId(value = "id", type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * harbor项目表id
     *
     * Column: harbor_project_id Nullable: false
     */
    private Long harborProjectId;

    /**
     * 实例code
     *
     * Column: instance_Code Nullable: false
     */
    private String instanceCode;

    private Integer isPublic;

    /**
     * 项目名
     *
     * Column: project_name Nullable: false
     */
    private String projectName;

    /**
     * 容量
     *
     * Column: capacity Nullable: false
     */
    private String capacity;

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

    public Long getHarborProjectId() {
        return harborProjectId;
    }

    public void setHarborProjectId(Long harborProjectId) {
        this.harborProjectId = harborProjectId;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity == null ? null : capacity.trim();
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