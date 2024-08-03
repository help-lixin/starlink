package help.lixin.starlink.plugin.k8s.service;

import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;

public interface IK8sJobService extends IK8sBaseService {

    Boolean saveJob(K8sAppDTO k8sAppDTO);

    Boolean deleteJob(Long id, String userName);

    Boolean restart(Long id, String userName);

}
