package help.lixin.starlink.plugin.k8s.convert;

import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import org.mapstruct.Mapper;

import help.lixin.starlink.plugin.k8s.dto.base.BasePageListDTO;
import help.lixin.starlink.plugin.k8s.request.base.BasePageListVO;
import help.lixin.starlink.plugin.k8s.request.cronjob.CreateCronJobVO;

@Mapper
public interface K8sCronJobControllerConvert {

    BasePageListDTO convert(BasePageListVO basePageListVO);

    K8sAppDTO convert(CreateCronJobVO createCronJobVO);
}
