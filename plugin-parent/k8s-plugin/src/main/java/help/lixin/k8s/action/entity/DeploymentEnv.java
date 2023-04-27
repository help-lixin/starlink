package help.lixin.k8s.action.entity;

import java.io.Serializable;

public class DeploymentEnv implements Serializable {
    private String containerName;
    private String name;
    private String value;

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
