package help.lixin.starlink.plugin.nacos.api.factory;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.plugin.Specification;
import help.lixin.starlink.plugin.nacos.api.config.NacosConfig;


public class NacosPluginNamedContextFactory extends PluginNamedContextFactory<Specification> {
    public NacosPluginNamedContextFactory() {
        super(NacosConfig.class, "_nacos", "nacos");
    }
}
