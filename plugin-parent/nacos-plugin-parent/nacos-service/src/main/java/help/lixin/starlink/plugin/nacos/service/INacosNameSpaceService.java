package help.lixin.starlink.plugin.nacos.service;

import help.lixin.starlink.plugin.nacos.api.model.namespace.NacosNameSpaceSave;
import help.lixin.starlink.plugin.nacos.api.model.namespace.NamespaceDetail;

import java.util.List;

public interface INacosNameSpaceService {

    public List<NamespaceDetail> nameSpaseList(String nameSpaceId, String instanceName);

    public Boolean createNamespace(NacosNameSpaceSave nameSpaceCreate, String instanceName);

    public Boolean updateNamespace(NacosNameSpaceSave nacosNameSpaceSave, String instanceName);

    public Boolean deleteNamespace(String nameSpaceId, String instanceName);
}
