package help.lixin.starlink.plugin.k8s.service;

import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.k8s.domain.KubernetesDeployTemplate;
import help.lixin.starlink.plugin.k8s.dto.DeployTemplateOption;
import help.lixin.starlink.plugin.k8s.dto.deploytemplate.CreateDeployTemplateDTO;
import help.lixin.starlink.plugin.k8s.dto.deploytemplate.DeployTemplateDTO;

import java.util.List;

public interface IK8sDeployTemplateService {
    KubernetesDeployTemplate get(Long deployId);

    List<DeployTemplateOption> querydeployTemplateOptions();

    PageResponse<KubernetesDeployTemplate> queryPageList(DeployTemplateDTO dto);

    Boolean delete(Long id);

    Boolean changeStatus(Long id, Integer status, String createBy);

    Integer create(CreateDeployTemplateDTO dto);

    Boolean checkDeployNameUnique(CreateDeployTemplateDTO dto);
}
