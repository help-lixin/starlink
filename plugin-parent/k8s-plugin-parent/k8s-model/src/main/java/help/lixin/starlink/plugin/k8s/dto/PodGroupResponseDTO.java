package help.lixin.starlink.plugin.k8s.dto;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2024/7/15 下午5:06
 * @Description
 */
public class PodGroupResponseDTO {

    private String nameSpace;
    private String podName;
    private String status;
    private String nodeName;
    private String ipAddress;
    private List<ContainerDTO> containers;
    private Integer containerAmount;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getContainerAmount() {
        return containerAmount;
    }

    public void setContainerAmount(Integer containerAmount) {
        this.containerAmount = containerAmount;
    }

    public List<ContainerDTO> getContainers() {
        return containers;
    }

    public void setContainers(List<ContainerDTO> containers) {
        this.containers = containers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getPodName() {
        return podName;
    }

    public void setPodName(String podName) {
        this.podName = podName;
    }
}
