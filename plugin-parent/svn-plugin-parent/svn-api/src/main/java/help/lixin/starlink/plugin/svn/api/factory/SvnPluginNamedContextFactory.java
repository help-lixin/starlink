package help.lixin.starlink.plugin.svn.api.factory;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.plugin.Specification;
import help.lixin.starlink.plugin.svn.api.config.SvnConfig;


public class SvnPluginNamedContextFactory extends PluginNamedContextFactory<Specification> {
    public SvnPluginNamedContextFactory() {
        super(SvnConfig.class, "_svn", "svn");
    }
}
