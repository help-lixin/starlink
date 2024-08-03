package help.lixin.starlink.plugin.k8s.convert;

import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import org.mapstruct.Mapper;

import help.lixin.starlink.plugin.k8s.dto.base.BasePageListDTO;
import help.lixin.starlink.plugin.k8s.request.base.BasePageListVO;
import help.lixin.starlink.plugin.k8s.request.daemonset.CreateDaemonSetVO;

/**
 * @Author: 伍岳林
 * @Date: 2024/6/21 下午5:23
 * @Description
 */
@Mapper
public interface K8sDaemonSetControllerConvert {

    BasePageListDTO convert(BasePageListVO basePageListVO);

    K8sAppDTO convert(CreateDaemonSetVO createDaemonSetVO);
}
