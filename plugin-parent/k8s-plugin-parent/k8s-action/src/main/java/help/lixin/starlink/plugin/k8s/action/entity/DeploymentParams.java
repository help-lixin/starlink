package help.lixin.starlink.plugin.k8s.action.entity;

import help.lixin.starlink.plugin.k8s.constants.Constant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeploymentParams implements Serializable {
    // K8S实例
    private String instanceCode;
    // Demployment 模板id
    private Long deployTemplateId;
    // 凭证name
    private String credentialName;
    // 命名空间
    private String namespace = Constant.DEFAULT_NAMESPACE;
    // 自定义变量
    private List<DeploymentVar> vars = new ArrayList<>(0);
    // 跳过模板校验
    private boolean skipTemplateValidate = false;

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public Long getDeployTemplateId() {
        return deployTemplateId;
    }

    public void setDeployTemplateId(Long deployTemplateId) {
        this.deployTemplateId = deployTemplateId;
    }

    public String getCredentialName() {
        return credentialName;
    }

    public void setCredentialName(String credentialName) {
        this.credentialName = credentialName;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public List<DeploymentVar> getVars() {
        return vars;
    }

    public void setVars(List<DeploymentVar> vars) {
        this.vars = vars;
    }

    public boolean isSkipTemplateValidate() {
        return skipTemplateValidate;
    }

    public void setSkipTemplateValidate(boolean skipTemplateValidate) {
        this.skipTemplateValidate = skipTemplateValidate;
    }

    @Override
    public String toString() {
        return "DeploymentParams{" + "instanceCode='" + instanceCode + '\'' + ", deployTemplateId=" + deployTemplateId + ", credentialName='" + credentialName + '\'' + ", namespace='" + namespace + '\'' + ", vars=" + vars + ", skipTemplateValidate=" + skipTemplateValidate + '}';
    }
}