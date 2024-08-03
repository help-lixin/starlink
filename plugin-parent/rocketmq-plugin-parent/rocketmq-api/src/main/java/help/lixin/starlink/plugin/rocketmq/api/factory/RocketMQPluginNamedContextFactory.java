package help.lixin.starlink.plugin.rocketmq.api.factory;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.plugin.Specification;
import help.lixin.starlink.plugin.rocketmq.api.config.RocketMQConfig;


public class RocketMQPluginNamedContextFactory extends PluginNamedContextFactory<Specification> {
    public RocketMQPluginNamedContextFactory() {
        super(RocketMQConfig.class, "_rocketmq", "rocketmq");
    }
}
