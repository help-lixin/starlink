package help.lixin.starlink.plugin.k8s.dto.deploytemplate;

import help.lixin.starlink.core.dto.PageDTO;

public class DeployTemplateDTO extends PageDTO {
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
