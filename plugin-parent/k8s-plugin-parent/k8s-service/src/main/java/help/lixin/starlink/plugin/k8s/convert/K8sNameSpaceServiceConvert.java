package help.lixin.starlink.plugin.k8s.convert;

import org.mapstruct.Mapper;

import help.lixin.starlink.plugin.k8s.domain.KubernetesNameSpace;
import help.lixin.starlink.plugin.k8s.dto.namespace.SaveNameSpaceDTO;

/**
 * @Author: 伍岳林
 * @Date: 2024/7/9 下午5:12
 * @Description
 */
@Mapper
public interface K8sNameSpaceServiceConvert {

    KubernetesNameSpace convert(SaveNameSpaceDTO saveNameSpaceDTO);

}
