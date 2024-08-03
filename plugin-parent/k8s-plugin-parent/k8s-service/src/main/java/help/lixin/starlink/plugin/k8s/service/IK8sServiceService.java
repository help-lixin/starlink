package help.lixin.starlink.plugin.k8s.service;

import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;

public interface IK8sServiceService extends IK8sBaseService {

    Boolean saveService(K8sAppDTO k8sAppDTO);

    Boolean deleteService(Long id, String userName);
}
