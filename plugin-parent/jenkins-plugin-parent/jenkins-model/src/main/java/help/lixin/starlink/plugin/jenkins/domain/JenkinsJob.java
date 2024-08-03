package help.lixin.starlink.plugin.jenkins.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Table: jenkins_job
 */
public class JenkinsJob implements Serializable {
    /**
     * ID
     *
     * Column:    id
     * Nullable:  false
     */
    private Long id;

    /**
     * Column:    instance_code
     * Nullable:  true
     */
    private String instanceCode;

    /**
     * 任务名
     *
     * Column:    job_name
     * Nullable:  true
     */
    private String jobName;

    /**
     * 备注
     *
     * Column:    remark
     * Nullable:  true
     */
    private String remark;

    /**
     * 最后成功时间
     *
     * Column:    last_success
     * Nullable:  true
     */
    private String lastSuccess;

    /**
     * 最后失败时间
     *
     * Column:    last_failure
     * Nullable:  true
     */
    private String lastFailure;

    /**
     * 最后构建所需时间
     *
     * Column:    last_duration
     * Nullable:  true
     */
    private String lastDuration;

    /**
     * 聚合状态（聚合百分比）
     *
     * Column:    aggregated_status
     * Nullable:  true
     */
    private String aggregatedStatus;

    /**
     * 仓库类型
     *
     * Column:    scm
     * Nullable:  true
     */
    private String scm;

    /**
     * 工具类型
     *
     * Column:    tools
     * Nullable:  true
     */
    private String tools;

    /**
     * Column:    jdk
     * Nullable:  true
     */
    private String jdk;

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
     * xml内容
     *
     * Column:    xml_content
     * Nullable:  true
     */
    private String xmlContent;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode == null ? null : instanceCode.trim();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getLastSuccess() {
        return lastSuccess;
    }

    public void setLastSuccess(String lastSuccess) {
        this.lastSuccess = lastSuccess == null ? null : lastSuccess.trim();
    }

    public String getLastFailure() {
        return lastFailure;
    }

    public void setLastFailure(String lastFailure) {
        this.lastFailure = lastFailure == null ? null : lastFailure.trim();
    }

    public String getLastDuration() {
        return lastDuration;
    }

    public void setLastDuration(String lastDuration) {
        this.lastDuration = lastDuration == null ? null : lastDuration.trim();
    }

    public String getAggregatedStatus() {
        return aggregatedStatus;
    }

    public void setAggregatedStatus(String aggregatedStatus) {
        this.aggregatedStatus = aggregatedStatus == null ? null : aggregatedStatus.trim();
    }

    public String getScm() {
        return scm;
    }

    public void setScm(String scm) {
        this.scm = scm == null ? null : scm.trim();
    }

    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools == null ? null : tools.trim();
    }

    public String getJdk() {
        return jdk;
    }

    public void setJdk(String jdk) {
        this.jdk = jdk == null ? null : jdk.trim();
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

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getXmlContent() {
        return xmlContent;
    }

    public void setXmlContent(String xmlContent) {
        this.xmlContent = xmlContent == null ? null : xmlContent.trim();
    }
}