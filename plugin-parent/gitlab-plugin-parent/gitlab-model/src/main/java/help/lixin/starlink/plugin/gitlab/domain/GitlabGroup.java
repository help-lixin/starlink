package help.lixin.starlink.plugin.gitlab.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * Table: gitlab_group
 */
public class GitlabGroup implements Serializable {
    /**
     * ID
     *
     * Column:    id
     * Nullable:  false
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * gitlab_group_id
     *
     * Column:    gitlab_group_id
     * Nullable:  false
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long gitlabGroupId;

    /**
     * 组名
     *
     * Column:    gitlab_group_name
     * Nullable:  false
     */
    private String gitlabGroupName;

    /**
     * 路径名（基本与组名一致）
     *
     * Column:    path
     * Nullable:  false
     */
    private String path;

    /**
     * 权限
     *
     * Column:    visibility
     * Nullable:  false
     */
    private String visibility;


    /**
     * 实例code;
     *
     * Column:    instance_code
     * Nullable:  false
     */
    private String instanceCode;

    /**
     * 备注
     *
     * Column:    remark
     * Nullable:  true
     */
    private String remark;

    /**
     * 是否删除（删除为1）
     *
     * Column:    status
     * Nullable:  false
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
     * Column:    created_by
     * Nullable:  true
     */
    private String createBy;

    /**
     * 更新人
     *
     * Column:    updated_by
     * Nullable:  true
     */
    private String updateBy;

    /**
     * 创建时间
     *
     * Column:    created_time
     * Nullable:  true
     */
    private Date createTime;

    /**
     * Column:    updated_time
     * Nullable:  true
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGitlabGroupId() {
        return gitlabGroupId;
    }

    public void setGitlabGroupId(Long gitlabGroupId) {
        this.gitlabGroupId = gitlabGroupId;
    }

    public String getGitlabGroupName() {
        return gitlabGroupName;
    }

    public void setGitlabGroupName(String gitlabGroupName) {
        this.gitlabGroupName = gitlabGroupName == null ? null : gitlabGroupName.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility == null ? null : visibility.trim();
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode == null ? null : instanceCode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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