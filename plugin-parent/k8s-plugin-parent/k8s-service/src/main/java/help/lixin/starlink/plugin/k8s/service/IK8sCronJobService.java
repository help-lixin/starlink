package help.lixin.starlink.plugin.k8s.service;

import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;

public interface IK8sCronJobService extends IK8sBaseService {

    Boolean saveCronJob(K8sAppDTO k8sAppDTO);

    Boolean pause(Long id);

    Boolean start(Long id);

    Boolean deleteCronJob(Long id, String userName);

    Boolean changeRunStatus(Integer status, Long id, String userName);
}
