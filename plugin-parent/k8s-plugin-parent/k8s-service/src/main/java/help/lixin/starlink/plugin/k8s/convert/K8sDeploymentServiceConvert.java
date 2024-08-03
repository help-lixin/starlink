package help.lixin.starlink.plugin.k8s.convert;

import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import org.mapstruct.Mapper;

import help.lixin.starlink.plugin.k8s.dto.base.BasePageListDTO;

/**
 * @Author: 伍岳林
 * @Date: 2024/6/5 下午5:45
 * @Description
 */
@Mapper
public interface K8sDeploymentServiceConvert {

    BasePageListDTO convert(K8sAppDTO k8sAppDTO);

}
