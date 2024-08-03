package help.lixin.starlink.plugin.rocketmq.service.impl;

import help.lixin.starlink.core.plugin.InstanceService;
import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.rocketmq.api.dto.producer.ProducerInfoDTO;
import help.lixin.starlink.plugin.rocketmq.api.service.IProducerService;
import help.lixin.starlink.plugin.rocketmq.service.IRocketMQProducerService;

import java.util.List;


/**
 * @Author: 伍岳林
 * @Date: 2023/9/11 4:21 下午
 * @Description
 */
public class RocketMQProducerServiceImpl extends InstanceService<IProducerService> implements IRocketMQProducerService {


    @Override
    public List<ProducerInfoDTO> producerConnection(String producerGroup, String topic, String instanceName) {
        return getApi(instanceName).producerConnection(producerGroup, topic);
    }

    public RocketMQProducerServiceImpl(PluginNamedContextFactory pluginNamedContextFactory) {
        this.pluginNamedContextFactory = pluginNamedContextFactory;
    }
}
