package help.lixin.starlink.plugin.k8s.service;

import help.lixin.starlink.plugin.k8s.dto.PodGroupResultDTO;
import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import help.lixin.starlink.plugin.k8s.dto.deployment.DeploymentPodGroupDTO;
import help.lixin.starlink.plugin.k8s.dto.deployment.DeploymentUpdateReplicasDTO;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/16 上午11:11
 * @Description
 */
public interface IK8sDeploymentService extends IK8sBaseService {

    Boolean deleteDeployment(Long id, String userName);

    Boolean saveDeployment(K8sAppDTO k8sAppDTO);

    Boolean updateReplicas(DeploymentUpdateReplicasDTO deploymentUpdateReplicasDTO);

    PodGroupResultDTO queryDeploymentPodGroup(DeploymentPodGroupDTO deploymentPodGroupDTO);
}
