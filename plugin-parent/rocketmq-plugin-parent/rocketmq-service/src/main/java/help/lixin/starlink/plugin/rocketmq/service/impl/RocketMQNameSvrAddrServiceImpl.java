package help.lixin.starlink.plugin.rocketmq.service.impl;

import help.lixin.starlink.core.plugin.InstanceService;
import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.rocketmq.api.dto.namesvr.NameSvrInfoDTO;
import help.lixin.starlink.plugin.rocketmq.api.service.INameSvrAddrService;
import help.lixin.starlink.plugin.rocketmq.service.IRocketMQNameSvrAddrService;


/**
 * @Author: 伍岳林
 * @Date: 2023/9/1 5:47 下午
 * @Description
 */
public class RocketMQNameSvrAddrServiceImpl extends InstanceService<INameSvrAddrService> implements IRocketMQNameSvrAddrService {
    @Override
    public NameSvrInfoDTO list(String instanceName) {
        return getApi(instanceName).list();
    }

    @Override
    public Boolean updateIsVIPChannel(Boolean useVIPChannel, String instanceName) {
        return getApi(instanceName).updateIsVIPChannel(useVIPChannel);
    }

    @Override
    public Boolean updateNameSvrAddr(String nameSvrAddrList, String instanceName) {
        return getApi(instanceName).updateNameSvrAddr(nameSvrAddrList);
    }

    public RocketMQNameSvrAddrServiceImpl(PluginNamedContextFactory pluginNamedContextFactory) {
        this.pluginNamedContextFactory = pluginNamedContextFactory;
    }
}
