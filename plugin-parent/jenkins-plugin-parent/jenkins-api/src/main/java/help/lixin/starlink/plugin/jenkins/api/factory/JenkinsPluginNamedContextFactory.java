package help.lixin.starlink.plugin.jenkins.api.factory;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.plugin.Specification;
import help.lixin.starlink.plugin.jenkins.api.config.JenkinsConfig;

public class JenkinsPluginNamedContextFactory extends PluginNamedContextFactory<Specification> {
    public JenkinsPluginNamedContextFactory() {
        super(JenkinsConfig.class, "_jenkins", "jenkins");
    }
}
