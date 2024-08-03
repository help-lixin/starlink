package help.lixin.starlink.plugin.credential.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import help.lixin.starlink.plugin.credential.enums.CredentialEnum;

import javax.validation.constraints.NotBlank;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "credentialType")
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = SecretCredentialVO.class, name = "SECRET"), //
        @JsonSubTypes.Type(value = SSHCredentialVO.class, name = "SSH"), //
        @JsonSubTypes.Type(value = UserNamePasswordCredentialVO.class, name = "USERNAME_PASSWORD"),
        @JsonSubTypes.Type(value = TokenCredentialVO.class, name = "TOKEN"),
        @JsonSubTypes.Type(value = SysCredentialOpaqueVO.class, name = "OPAQUE"),
        @JsonSubTypes.Type(value = SysCredentialTlsVO.class, name = "TLS")
})
public class CredentialVO {
    private Long id;
    @NotBlank(message = "插件编码不能为空")
    protected String pluginCode;
    @NotBlank(message = "实例编码不能为空")
    protected String instanceCode;
    @NotBlank(message = "凭证名称不能为空")
    protected String credentialName;
    @NotBlank(message = "凭证id不能为空")
    protected String credentialKey;
    protected CredentialEnum credentialType;
    protected String remark;
    protected String nameSpace;
    protected Integer status = 1;

    protected transient String createBy;

    public CredentialEnum getCredentialType() {
        return credentialType;
    }

    public void setCredentialType(CredentialEnum credentialType) {
        this.credentialType = credentialType;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getPluginCode() {
        return pluginCode;
    }

    public void setPluginCode(String pluginCode) {
        this.pluginCode = pluginCode;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getCredentialKey() {
        return credentialKey;
    }

    public void setCredentialKey(String credentialKey) {
        this.credentialKey = credentialKey;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCredentialName() {
        return credentialName;
    }

    public void setCredentialName(String credentialName) {
        this.credentialName = credentialName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
