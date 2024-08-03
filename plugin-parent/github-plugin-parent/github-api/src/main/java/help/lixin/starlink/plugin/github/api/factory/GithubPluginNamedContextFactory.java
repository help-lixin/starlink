package help.lixin.starlink.plugin.github.api.factory;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.plugin.Specification;
import help.lixin.starlink.plugin.github.api.config.GithubConfig;

public class GithubPluginNamedContextFactory extends PluginNamedContextFactory<Specification> {
    public GithubPluginNamedContextFactory() {
        super(GithubConfig.class, "_github", "github");
    }
}
