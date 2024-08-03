package help.lixin.starlink.plugin.gitlab.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * Table: gitlab_project
 */
public class GitlabProject implements Serializable {
    /**
     * project_id
     *
     * Column:    id
     * Nullable:  false
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * gitlabId
     *
     * Column:    gitlab_project_id
     * Nullable:  false
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long gitlabProjectId;

    /**
     * 实例code;
     *
     * Column:    instance_code
     * Nullable:  false
     */
    private String instanceCode;

    /**
     * 项目名
     *
     * Column:    project_name
     * Nullable:  false
     */
    private String projectName;

    /**
     * 是否用readme初始化仓库
     *
     * Column:    initiallize_with_readme
     * Nullable:  true
     */
    private Boolean initiallizeWithReadme;

    /**
     * 权限
     *
     * Column:    visibility
     * Nullable:  true
     */
    private String visibility;

    /**
     * 按用户划分空间
     *
     * Column:    namespace_by_user
     * Nullable:  true
     */
    private Long namespaceByUser;

    /**
     * 按组划分空间
     *
     * Column:    namespace_by_group
     * Nullable:  true
     */
    private Long namespaceByGroup;

    /**
     * 项目路径
     *
     * Column:    path
     * Nullable:  true
     */
    private String path;

    /**
     * 备注
     *
     * Column:    remark
     * Nullable:  true
     */
    private String remark;

    /**
     * ssh克隆地址
     *
     * Column:    ssh_url
     * Nullable:  true
     */
    private String sshUrl;

    /**
     * http克隆地址
     *
     * Column:    web_url
     * Nullable:  true
     */
    private String webUrl;

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

    public Long getGitlabProjectId() {
        return gitlabProjectId;
    }

    public void setGitlabProjectId(Long gitlabProjectId) {
        this.gitlabProjectId = gitlabProjectId;
    }


    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode == null ? null : instanceCode.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public Boolean getInitiallizeWithReadme() {
        return initiallizeWithReadme;
    }

    public void setInitiallizeWithReadme(Boolean initiallizeWithReadme) {
        this.initiallizeWithReadme = initiallizeWithReadme;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility == null ? null : visibility.trim();
    }

    public Long getNamespaceByUser() {
        return namespaceByUser;
    }

    public void setNamespaceByUser(Long namespaceByUser) {
        this.namespaceByUser = namespaceByUser;
    }

    public Long getNamespaceByGroup() {
        return namespaceByGroup;
    }

    public void setNamespaceByGroup(Long namespaceByGroup) {
        this.namespaceByGroup = namespaceByGroup;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSshUrl() {
        return sshUrl;
    }

    public void setSshUrl(String sshUrl) {
        this.sshUrl = sshUrl == null ? null : sshUrl.trim();
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl == null ? null : webUrl.trim();
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