package help.lixin.starlink.plugin.km.api.factory;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.plugin.Specification;
import help.lixin.starlink.plugin.km.api.config.KmConfig;


public class KmPluginNamedContextFactory extends PluginNamedContextFactory<Specification> {
    public KmPluginNamedContextFactory() {
        super(KmConfig.class, "_km", "km");
    }
}
