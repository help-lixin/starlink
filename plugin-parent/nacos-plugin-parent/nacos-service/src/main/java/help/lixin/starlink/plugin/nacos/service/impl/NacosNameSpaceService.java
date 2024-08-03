package help.lixin.starlink.plugin.nacos.service.impl;

import help.lixin.starlink.core.plugin.InstanceService;
import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.nacos.api.model.namespace.NacosNameSpaceSave;
import help.lixin.starlink.plugin.nacos.api.model.namespace.NamespaceDetail;
import help.lixin.starlink.plugin.nacos.api.service.INacosNameSpaceService;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/12 11:37 上午
 * @Description
 */
public class NacosNameSpaceService extends InstanceService<INacosNameSpaceService> implements help.lixin.starlink.plugin.nacos.service.INacosNameSpaceService {


    public NacosNameSpaceService(PluginNamedContextFactory pluginNamedContextFactory) {
        this.pluginNamedContextFactory = pluginNamedContextFactory;
    }

    @Override
    public List<NamespaceDetail> nameSpaseList(String nameSpaceId, String instanceName) {
        INacosNameSpaceService nameSpaceService = getApi(instanceName);
        return nameSpaceService.nameSpaseList(nameSpaceId);
    }

    @Override
    public Boolean createNamespace(NacosNameSpaceSave nameSpaceCreate, String instanceName) {
        INacosNameSpaceService nameSpaceService = getApi(instanceName);
        return nameSpaceService.createNamespace(nameSpaceCreate);
    }

    @Override
    public Boolean updateNamespace(NacosNameSpaceSave nacosNameSpaceSave, String instanceName) {
        INacosNameSpaceService nameSpaceService = getApi(instanceName);
        return nameSpaceService.updateNamespace(nacosNameSpaceSave);
    }

    @Override
    public Boolean deleteNamespace(String nameSpaceId, String instanceName) {
        INacosNameSpaceService nameSpaceService = getApi(instanceName);
        return nameSpaceService.deleteNamespace(nameSpaceId);
    }
}
