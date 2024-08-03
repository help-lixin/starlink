package help.lixin.starlink.plugin.jsch.api.factory;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.plugin.Specification;
import help.lixin.starlink.plugin.jsch.api.config.JschConfig;

public class JschPluginNamedContextFactory extends PluginNamedContextFactory<Specification> {
    public JschPluginNamedContextFactory() {
        super(JschConfig.class, "_jsch", "jsch");
    }
}
