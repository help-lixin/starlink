package help.lixin.starlink.plugin.harbor.api.factory;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.plugin.Specification;
import help.lixin.starlink.plugin.harbor.api.config.HarborConfig;

public class HarborPluginNamedContextFactory extends PluginNamedContextFactory<Specification> {
    public HarborPluginNamedContextFactory() {
        super(HarborConfig.class, "_harbor", "harbor");
    }
}
