package help.lixin.starlink.plugin.k8s.convert;

import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import help.lixin.starlink.plugin.k8s.request.deployment.CreateDeploymentVO;
import org.mapstruct.Mapper;

import help.lixin.starlink.plugin.k8s.dto.base.BasePageListDTO;
import help.lixin.starlink.plugin.k8s.request.base.BasePageListVO;

/**
 * @Author: 伍岳林
 * @Date: 2024/6/5 下午5:45
 * @Description
 */
@Mapper
public interface K8sDeploymentControllerConvert {

    BasePageListDTO convert(BasePageListVO basePageListVO);

    K8sAppDTO convert(CreateDeploymentVO createDeploymentVO);

}
