package help.lixin.starlink.plugin.k8s.convert;

import org.mapstruct.Mapper;

import help.lixin.starlink.plugin.k8s.dto.base.NameSpacePageListDTO;
import help.lixin.starlink.plugin.k8s.dto.namespace.SaveNameSpaceDTO;
import help.lixin.starlink.plugin.k8s.request.namespace.NameSpacePageListVO;
import help.lixin.starlink.plugin.k8s.request.namespace.SaveNameSpaceVO;

@Mapper
public interface K8sNameSpaceControllerConvert {

    NameSpacePageListDTO convert(NameSpacePageListVO nameSpacePageListVO);

    SaveNameSpaceDTO convert(SaveNameSpaceVO saveNameSpaceVO);

}
