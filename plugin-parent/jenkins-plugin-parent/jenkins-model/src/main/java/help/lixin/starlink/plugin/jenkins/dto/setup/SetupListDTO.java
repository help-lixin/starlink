package help.lixin.starlink.plugin.jenkins.dto.setup;

import help.lixin.starlink.plugin.jenkins.domain.ToolsType;

import java.util.Date;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/19 11:41 上午
 * @Description
 */
public class SetupListDTO {
    private Long id;

    /**
     * Column:    job_id
     * Nullable:  true
     */
    private Long jobId;

    /**
     * 执行步骤类型
     *
     * Column:    setup_type
     * Nullable:  true
     */
    private ToolsType setupType;

    /**
     * 执行顺序
     *
     * Column:    order
     * Nullable:  true
     */
    private Integer sequence;

    /**
     * Column:    instance_code
     * Nullable:  true
     */
    private String instanceCode;

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

    private String updateBy;

    private Date createTime;

    private Date updateTime;

    private String setupName;

    private String script;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public ToolsType getSetupType() {
        return setupType;
    }

    public void setSetupType(ToolsType setupType) {
        this.setupType = setupType;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
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

    public String getSetupName() {
        return setupName;
    }

    public void setSetupName(String setupName) {
        this.setupName = setupName;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
