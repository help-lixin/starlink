package help.lixin.starlink.plugin.nacos.service.impl;

import help.lixin.starlink.core.plugin.InstanceService;
import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServiceDetailInfo;
import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServiceForm;
import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServiceInfo;
import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServicePageList;
import help.lixin.starlink.plugin.nacos.api.service.INacosServiceManageService;
import help.lixin.response.PageResponse;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/24 10:41 上午
 * @Description
 */
public class NacosServiceManageService extends InstanceService<INacosServiceManageService> implements help.lixin.starlink.plugin.nacos.service.INacosServiceManageService {



    public NacosServiceManageService(PluginNamedContextFactory pluginNamedContextFactory) {
        this.pluginNamedContextFactory = pluginNamedContextFactory;
    }

    @Override
    public PageResponse<NacosServiceDetailInfo> pageList(NacosServicePageList nacosServicePageList, String instanceName) {
        INacosServiceManageService api = getApi(instanceName);
        return api.pageList(nacosServicePageList);
    }

    @Override
    public Boolean create(NacosServiceForm nacosServiceForm, String instanceName) {
        INacosServiceManageService api = getApi(instanceName);
        return api.create(nacosServiceForm);
    }

    @Override
    public Boolean update(NacosServiceForm nacosServiceForm, String instanceName) {
        INacosServiceManageService api = getApi(instanceName);
        return api.update(nacosServiceForm);
    }

    @Override
    public Boolean remove(NacosServiceInfo nacosServiceInfo, String instanceName) {
        INacosServiceManageService api = getApi(instanceName);
        return api.remove(nacosServiceInfo);
    }

    @Override
    public String detail(NacosServiceInfo nacosServiceInfo, String instanceName) {
        INacosServiceManageService api = getApi(instanceName);
        return api.detail(nacosServiceInfo);
    }
}
