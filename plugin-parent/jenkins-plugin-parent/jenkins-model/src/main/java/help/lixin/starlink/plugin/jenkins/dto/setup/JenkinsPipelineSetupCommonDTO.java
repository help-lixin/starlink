package help.lixin.starlink.plugin.jenkins.dto.setup;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import help.lixin.starlink.plugin.jenkins.domain.ToolsType;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "setupType")
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = JenkinsPipelineSetupAntDTO.class, name = "ANT"), //
        @JsonSubTypes.Type(value = JenkinsPipelineSetupGoDTO.class, name = "GO"), //
        @JsonSubTypes.Type(value = JenkinsPipelineSetupGradleDTO.class, name = "GRADLE"), //
        @JsonSubTypes.Type(value = JenkinsPipelineSetupMavenDTO.class, name = "MAVEN"), //
        @JsonSubTypes.Type(value = JenkinsPipelineSetupNodejsDTO.class, name = "NODE_JS"), //
        @JsonSubTypes.Type(value = JenkinsPipelineSetupPythonDTO.class, name = "PYTHON"), //
        @JsonSubTypes.Type(value = JenkinsPipelineSetupShellDTO.class, name = "SHELL"), //
})
public class JenkinsPipelineSetupCommonDTO implements Serializable {
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
    private Integer sequence = 1;

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
    private Integer status = 1;

    /**
     * 创建人
     *
     * Column:    create_by
     * Nullable:  true
     */
    private String createBy;

    private String updateBy;

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    private static final long serialVersionUID = 1L;

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


}