package help.lixin.starlink.plugin.k8s.dto;

/**
 * @Author: 伍岳林
 * @Date: 2024/7/15 下午9:28
 * @Description
 */
public class ContainerDTO {

    private String containerName;

    private String imageName;

    private Boolean status;

    private Integer restartCount;

    public Integer getRestartCount() {
        return restartCount;
    }

    public void setRestartCount(Integer restartCount) {
        this.restartCount = restartCount;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
