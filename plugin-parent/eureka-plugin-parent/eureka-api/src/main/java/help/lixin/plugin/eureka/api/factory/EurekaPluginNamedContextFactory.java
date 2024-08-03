package help.lixin.plugin.eureka.api.factory;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.plugin.Specification;
import help.lixin.plugin.eureka.api.config.EurekaConfig;

public class EurekaPluginNamedContextFactory extends PluginNamedContextFactory<Specification> {
    public EurekaPluginNamedContextFactory() {
        super(EurekaConfig.class, "_eureka", "eureka");
    }
}
