package help.lixin.starlink.plugin.k8s.convert;

import org.mapstruct.Mapper;

import help.lixin.starlink.plugin.k8s.dto.base.BasePageListDTO;
import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import help.lixin.starlink.plugin.k8s.request.base.BasePageListVO;
import help.lixin.starlink.plugin.k8s.request.job.CreateJobVO;

@Mapper
public interface K8sJobControllerConvert {

    BasePageListDTO convert(BasePageListVO basePageListVO);

    K8sAppDTO convert(CreateJobVO createJobVO);
}
