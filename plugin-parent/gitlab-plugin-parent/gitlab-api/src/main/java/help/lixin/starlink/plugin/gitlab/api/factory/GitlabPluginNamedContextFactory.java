package help.lixin.starlink.plugin.gitlab.api.factory;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.plugin.Specification;
import help.lixin.starlink.plugin.gitlab.api.config.GitlabConfig;

public class GitlabPluginNamedContextFactory extends PluginNamedContextFactory<Specification> {
    public GitlabPluginNamedContextFactory() {
        super(GitlabConfig.class, "_gitlab", "gitlab");
    }
}
