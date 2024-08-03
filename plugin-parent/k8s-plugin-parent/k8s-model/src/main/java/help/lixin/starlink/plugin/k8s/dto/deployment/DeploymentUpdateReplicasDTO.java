package help.lixin.starlink.plugin.k8s.dto.deployment;

/**
 * @Author: 伍岳林
 * @Date: 2024/7/17 下午8:35
 * @Description
 */
public class DeploymentUpdateReplicasDTO {

    private String instanceCode;
    private String nameSpace;
    private String deployName;
    private Integer replicas;

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

    public String getDeployName() {
        return deployName;
    }

    public void setDeployName(String deployName) {
        this.deployName = deployName;
    }

    public Integer getReplicas() {
        return replicas;
    }

    public void setReplicas(Integer replicas) {
        this.replicas = replicas;
    }
}
