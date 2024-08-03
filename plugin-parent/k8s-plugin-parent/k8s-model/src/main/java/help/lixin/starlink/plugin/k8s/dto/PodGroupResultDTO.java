package help.lixin.starlink.plugin.k8s.dto;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2024/7/15 下午5:06
 * @Description
 */
public class PodGroupResultDTO {

    private List<PodGroupResponseDTO> pods;
    // 期望副本数
    private Integer replicas;
    // 当前副本数
    private Integer curReplicas;

    public Integer getCurReplicas() {
        return curReplicas;
    }

    public void setCurReplicas(Integer curReplicas) {
        this.curReplicas = curReplicas;
    }

    public List<PodGroupResponseDTO> getPods() {
        return pods;
    }

    public void setPods(List<PodGroupResponseDTO> pods) {
        this.pods = pods;
    }

    public Integer getReplicas() {
        return replicas;
    }

    public void setReplicas(Integer replicas) {
        this.replicas = replicas;
    }
}
