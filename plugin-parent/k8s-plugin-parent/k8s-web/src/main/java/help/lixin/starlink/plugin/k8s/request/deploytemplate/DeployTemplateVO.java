package help.lixin.starlink.plugin.k8s.request.deploytemplate;

import help.lixin.starlink.request.PageRequest;

public class DeployTemplateVO extends PageRequest {
    private String deployName;
    private String remark;

    public String getDeployName() {
        return deployName;
    }

    public void setDeployName(String deployName) {
        this.deployName = deployName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
