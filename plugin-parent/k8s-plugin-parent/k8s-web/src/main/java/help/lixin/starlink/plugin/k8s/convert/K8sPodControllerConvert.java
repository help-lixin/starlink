package help.lixin.starlink.plugin.k8s.convert;

import org.mapstruct.Mapper;

import help.lixin.starlink.plugin.k8s.dto.base.BasePageListDTO;
import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import help.lixin.starlink.plugin.k8s.dto.deployment.DeploymentPodGroupDTO;
import help.lixin.starlink.plugin.k8s.dto.deployment.DeploymentUpdateReplicasDTO;
import help.lixin.starlink.plugin.k8s.dto.pod.PodContainerGroupDTO;
import help.lixin.starlink.plugin.k8s.dto.pod.PodLogDTO;
import help.lixin.starlink.plugin.k8s.request.base.BasePageListVO;
import help.lixin.starlink.plugin.k8s.request.cronjob.CreateCronJobVO;
import help.lixin.starlink.plugin.k8s.request.deployment.DeploymentPodGroupVO;
import help.lixin.starlink.plugin.k8s.request.deployment.DeploymentUpdateReplicasVO;
import help.lixin.starlink.plugin.k8s.request.pod.PodContainerGroupVO;
import help.lixin.starlink.plugin.k8s.request.pod.PodLogVO;

@Mapper
public interface K8sPodControllerConvert {

    BasePageListDTO convert(BasePageListVO basePageListVO);

    K8sAppDTO convert(CreateCronJobVO createCronJobVO);

    PodLogDTO convert(PodLogVO podLogVO);

    DeploymentPodGroupDTO convert(DeploymentPodGroupVO deploymentPodGroupVO);

    DeploymentUpdateReplicasDTO convert(DeploymentUpdateReplicasVO deploymentUpdateReplicasVO);

    PodContainerGroupDTO convert(PodContainerGroupVO podContainerGroupVO);
}
