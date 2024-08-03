package help.lixin.starlink.plugin.gitlab.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * Table: gitlab_user
 */
public class GitlabUser implements Serializable {
    /**
     * user_id
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
     * Column:    gitlab_user_id
     * Nullable:  false
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long gitlabUserId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long sysUserId;

    /**
     * 用户名
     *
     * Column:    user_name
     * Nullable:  false
     */
    private String userName;

    /**
     * Column:    nick_name
     * Nullable:  true
     */
    private String nickName;

    /**
     * Column:    email
     * Nullable:  true
     */
    private String email;

    /**
     * 密码
     *
     * Column:    password
     * Nullable:  true
     */
    private String password;

    /**
     * Column:    instance_code
     * Nullable:  true
     */
    private String instanceCode;

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

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGitlabUserId() {
        return gitlabUserId;
    }

    public void setGitlabUserId(Long gitlabUserId) {
        this.gitlabUserId = gitlabUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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