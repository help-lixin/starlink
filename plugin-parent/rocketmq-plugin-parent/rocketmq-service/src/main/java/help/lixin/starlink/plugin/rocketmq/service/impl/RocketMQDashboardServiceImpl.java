package help.lixin.starlink.plugin.rocketmq.service.impl;

import help.lixin.starlink.core.plugin.InstanceService;
import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.rocketmq.api.service.IDashboardService;
import help.lixin.starlink.plugin.rocketmq.service.IRocketMQDashboardService;

import java.util.List;
import java.util.Map;

/**
 * @Author: 伍岳林
 * @Date: 2023/9/1 5:46 下午
 * @Description
 */
public class RocketMQDashboardServiceImpl extends InstanceService<IDashboardService> implements IRocketMQDashboardService {

    @Override
    public List<String> topicCurrent(String instanceName) {
        return getApi(instanceName).topicCurrent();
    }

    @Override
    public Map<String, List<String>> queryBroker(String queryDate, String instanceName) {
        return getApi(instanceName).queryBroker(queryDate);
    }

    @Override
    public List<String> queryTopic(String queryDate,String topicName, String instanceName) {
        return getApi(instanceName).queryTopic(queryDate, topicName);
    }

    public RocketMQDashboardServiceImpl(PluginNamedContextFactory pluginNamedContextFactory) {
        this.pluginNamedContextFactory = pluginNamedContextFactory;
    }
}
