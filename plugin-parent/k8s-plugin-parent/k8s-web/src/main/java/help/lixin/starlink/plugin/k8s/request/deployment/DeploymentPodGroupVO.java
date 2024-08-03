package help.lixin.starlink.plugin.k8s.request.deployment;

/**
 * @Author: 伍岳林
 * @Date: 2024/7/15 下午5:06
 * @Description
 */
public class DeploymentPodGroupVO {

    private String instanceCode;
    private String nameSpace;
    private String deploymentName;

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getDeploymentName() {
        return deploymentName;
    }

    public void setDeploymentName(String deploymentName) {
        this.deploymentName = deploymentName;
    }
}
