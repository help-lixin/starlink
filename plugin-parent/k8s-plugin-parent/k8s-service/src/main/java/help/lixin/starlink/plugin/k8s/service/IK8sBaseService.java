package help.lixin.starlink.plugin.k8s.service;

import java.util.List;

import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.k8s.domain.KubernetesApp;
import help.lixin.starlink.plugin.k8s.domain.KubernetesNameSpace;
import help.lixin.starlink.plugin.k8s.dto.base.BasePageListDTO;
import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import help.lixin.starlink.plugin.k8s.dto.base.KubernetesAppDTO;
import help.lixin.starlink.plugin.k8s.dto.namespace.NameSpaceOptionDTO;

public interface IK8sBaseService {

    List<KubernetesNameSpace> queryNameSpaces(String instanceCode);

    List<NameSpaceOptionDTO> queryNameSpaceOption(String instanceCode);

    KubernetesNameSpace queryNameSpace(String instanceCode, String nameSpace);

    PageResponse<KubernetesAppDTO> queryPageList(BasePageListDTO basePageListDTO);

    Boolean saveKubernetesApp(K8sAppDTO k8sAppDTO);

    Boolean nameIsExist(String instanceCode, String name);

    Boolean changeStatus(Integer status, Long id, String userName);

    String queryById(Long id);

    KubernetesApp deleteKubernetesApp(Long id, String userName);

    Boolean deleteKubernetesAppByName(String instanceCode, String name);
}
