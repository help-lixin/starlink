package help.lixin.starlink.plugin.jenkins.request.setup;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import help.lixin.starlink.plugin.jenkins.domain.ToolsType;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "setupType")
@JsonSubTypes(value = {@JsonSubTypes.Type(value = JenkinsPipelineSetupAntVO.class, name = "ANT"), //
    @JsonSubTypes.Type(value = JenkinsPipelineSetupGoVO.class, name = "GO"), //
    @JsonSubTypes.Type(value = JenkinsPipelineSetupGradleVO.class, name = "GRADLE"), //
    @JsonSubTypes.Type(value = JenkinsPipelineSetupMavenVO.class, name = "MAVEN"), //
    @JsonSubTypes.Type(value = JenkinsPipelineSetupNodejsVO.class, name = "NODE_JS"), //
    @JsonSubTypes.Type(value = JenkinsPipelineSetupPythonVO.class, name = "PYTHON"), //
    @JsonSubTypes.Type(value = JenkinsPipelineSetupShellVO.class, name = "SHELL"), //
})
public class JenkinsPipelineSetupCommonVO implements Serializable {
    private Long id;

    /**
     * 执行步骤类型
     *
     * Column: setup_type Nullable: true
     */
    private ToolsType setupType;

    /**
     * 执行顺序
     *
     * Column: sequence Nullable: true
     */
    private Integer sequence = 1;

    /**
     * Column: instance_code Nullable: true
     */
    private String instanceCode;

    /**
     * 状态值
     *
     * Column: status Nullable: true
     */
    private Integer status = 1;

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

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ToolsType getSetupType() {
        return setupType;
    }

    public void setSetupType(ToolsType setupType) {
        this.setupType = setupType;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

}