package help.lixin.starlink.plugin.eureka.service.impl;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;

import help.lixin.starlink.core.plugin.InstanceService;
import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.plugin.eureka.api.model.EurekaInstanceStatus;
import help.lixin.plugin.eureka.api.service.IEurekaClient;
import help.lixin.starlink.plugin.eureka.service.IEurekaClientService;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/26 5:00 下午
 * @Description
 */
public class EurekaClientServiceImpl extends InstanceService<IEurekaClient> implements IEurekaClientService {

    @Override
    public Boolean registerInstance(InstanceInfo instanceInfo, String instanceName) {
        IEurekaClient api = getApi(instanceName);
        return api.registerInstance(instanceInfo);
    }

    @Override
    public Boolean deleteInstance(String appName, String instanceId, String instanceName) {
        IEurekaClient api = getApi(instanceName);
        return api.deleteInstance(appName, instanceId);
    }

    @Override
    public Boolean heartbeat(String appName, String instanceId, String instanceName) {
        IEurekaClient api = getApi(instanceName);
        return api.heartbeat(appName, instanceId);
    }

    @Override
    public Applications applications(String instanceName) {
        IEurekaClient api = getApi(instanceName);
        return api.applications();
    }

    @Override
    public Application application(String appName, String instanceName) {
        IEurekaClient api = getApi(instanceName);
        return api.application(appName);
    }

    @Override
    public InstanceInfo instance(String appName, String instanceId, String instanceName) {
        IEurekaClient api = getApi(instanceName);
        return api.instance(appName, instanceId);
    }

    @Override
    public InstanceInfo instance(String instanceId, String instanceName) {
        IEurekaClient api = getApi(instanceName);
        return api.instance(instanceId);
    }

    @Override
    public Boolean outOfService(String appName, String instanceId, String instanceName) {
        IEurekaClient api = getApi(instanceName);
        return api.outOfService(appName, instanceId);
    }

    @Override
    public Boolean backInService(String appName, String instanceId, EurekaInstanceStatus status, String instanceName) {
        IEurekaClient api = getApi(instanceName);
        return api.backInService(appName, instanceId, status);
    }

    @Override
    public Boolean updateMetadata(String appName, String instanceId, String key, String value, String instanceName) {
        IEurekaClient api = getApi(instanceName);
        return api.updateMetadata(appName, instanceId, key, value);
    }

    @Override
    public Applications vips(String vipAddress, String instanceName) {
        IEurekaClient api = getApi(instanceName);
        return api.vips(vipAddress);
    }

    @Override
    public Applications svips(String svipAddress, String instanceName) {
        IEurekaClient api = getApi(instanceName);
        return api.svips(svipAddress);
    }

    public EurekaClientServiceImpl(PluginNamedContextFactory pluginNamedContextFactory) {
        this.pluginNamedContextFactory = pluginNamedContextFactory;
    }
}
