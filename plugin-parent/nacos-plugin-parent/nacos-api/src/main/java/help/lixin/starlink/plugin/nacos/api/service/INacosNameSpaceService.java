package help.lixin.starlink.plugin.nacos.api.service;

import help.lixin.starlink.plugin.nacos.api.model.namespace.NacosNameSpaceSave;
import help.lixin.starlink.plugin.nacos.api.model.namespace.NamespaceDetail;

import java.util.List;

public interface INacosNameSpaceService {

    public List<NamespaceDetail> nameSpaseList(String nameSpaceId);

    public Boolean createNamespace(NacosNameSpaceSave nameSpaceCreate);

    public Boolean updateNamespace(NacosNameSpaceSave nacosNameSpaceSave);

    public Boolean deleteNamespace(String nameSpaceId);

}
