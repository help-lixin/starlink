package help.lixin.starlink.plugin.k8s.service;

import java.util.List;

import help.lixin.starlink.plugin.k8s.dto.ContainerDTO;
import help.lixin.starlink.plugin.k8s.dto.pod.PodContainerGroupDTO;
import help.lixin.starlink.plugin.k8s.dto.pod.PodLogDTO;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/16 上午11:11
 * @Description
 */
public interface IK8sPodService extends IK8sBaseService {

    Boolean deletePod(Long id, String userName);

    String queryLog(PodLogDTO podLogDTO);

    List<ContainerDTO> queryContainerGroup(PodContainerGroupDTO podContainerGroupDTO);
}
