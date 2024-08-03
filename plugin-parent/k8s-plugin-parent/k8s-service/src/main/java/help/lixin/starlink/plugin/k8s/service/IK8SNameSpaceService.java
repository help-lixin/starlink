package help.lixin.starlink.plugin.k8s.service;

import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.k8s.domain.KubernetesNameSpace;
import help.lixin.starlink.plugin.k8s.dto.base.NameSpacePageListDTO;
import help.lixin.starlink.plugin.k8s.dto.namespace.SaveNameSpaceDTO;

public interface IK8SNameSpaceService {

    Boolean save(SaveNameSpaceDTO saveNameSpaceDTO);

    Boolean deleteNameSpace(Long id, String userName);

    PageResponse<KubernetesNameSpace> queryPageList(NameSpacePageListDTO pageDTO);

    Boolean changeStatus(Integer status, Long id, String userName);

    Boolean nameIsExist(String instanceCode, String name);
}
