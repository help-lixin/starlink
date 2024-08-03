package help.lixin.starlink.plugin.k8s.service;

import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;

public interface IK8sDaemonSetService extends IK8sBaseService {

    Boolean saveDaemonSet(K8sAppDTO k8sAppDTO);

    Boolean deleteDaemonSet(Long id, String userName);

}
