package help.lixin.starlink.plugin.k8s.convert;

import help.lixin.starlink.plugin.k8s.dto.deploytemplate.CreateDeployTemplateDTO;
import help.lixin.starlink.plugin.k8s.dto.deploytemplate.DeployTemplateDTO;
import help.lixin.starlink.plugin.k8s.request.deploytemplate.CreateDeployTemplateVO;
import help.lixin.starlink.plugin.k8s.request.deploytemplate.DeployTemplateVO;
import org.mapstruct.Mapper;

@Mapper
public interface K8sDeploymentTemplateControllerConvert {

    DeployTemplateDTO convert(DeployTemplateVO vo);

    CreateDeployTemplateDTO convert(CreateDeployTemplateVO vo);
}
