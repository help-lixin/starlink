package help.lixin.starlink.plugin.nacos.service.impl;

import help.lixin.starlink.core.plugin.InstanceService;
import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.nacos.api.dto.instance.InstanceDetailResponse;
import help.lixin.starlink.plugin.nacos.api.dto.instance.InstanceResponse;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceBeat;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceDetail;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceInfo;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceList;
import help.lixin.starlink.plugin.nacos.api.service.INacosInstanceService;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/12 11:36 上午
 * @Description
 */
public class NacosInstanceService extends InstanceService<INacosInstanceService> implements help.lixin.starlink.plugin.nacos.service.INacosInstanceService {


    public NacosInstanceService(PluginNamedContextFactory pluginNamedContextFactory) {
        this.pluginNamedContextFactory = pluginNamedContextFactory;
    }

    @Override
    public Boolean registerInstance(NacosInstanceInfo nacosInstanceInfo, String instanceName) {
        INacosInstanceService api = getApi(instanceName);
        return api.registerInstance(nacosInstanceInfo);
    }

    @Override
    public Boolean updateInstance(NacosInstanceInfo nacosInstanceInfo, String instanceName) {
        INacosInstanceService api = getApi(instanceName);
        return api.updateInstance(nacosInstanceInfo);
    }

    @Override
    public String beat(NacosInstanceBeat nacosInstanceBeat, String instanceName) {
        INacosInstanceService api = getApi(instanceName);
        return api.beat(nacosInstanceBeat);
    }

    @Override
    public String destoryInstance(NacosInstanceInfo nacosInstanceInfo, String instanceName) {
        INacosInstanceService api = getApi(instanceName);
        return api.destoryInstance(nacosInstanceInfo);
    }

    @Override
    public List<InstanceDetailResponse> cacheList(String instanceName) {
        INacosInstanceService api = getApi(instanceName);
        return api.cacheList();
    }

    @Override
    public InstanceDetailResponse cacheDetail(String nameSpaceId,String instanceName) {
        INacosInstanceService api = getApi(instanceName);
        return api.cacheDetail(nameSpaceId);
    }

    @Override
    public InstanceResponse instanceList(NacosInstanceList nacosInstanceList, String instanceName) {
        INacosInstanceService api = getApi(instanceName);
        return api.instanceList(nacosInstanceList);
    }

    @Override
    public InstanceDetailResponse instanceDetail(NacosInstanceDetail nacosInstanceDetail, String instanceName) {
        INacosInstanceService api = getApi(instanceName);
        return api.instanceDetail(nacosInstanceDetail);
    }
}
