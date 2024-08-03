package help.lixin.starlink.plugin.k8s.factory;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.plugin.Specification;
import help.lixin.starlink.plugin.k8s.config.K8SConfig;

public class K8SPluginNamedContextFactory extends PluginNamedContextFactory<Specification> {
    public K8SPluginNamedContextFactory() {
        super(K8SConfig.class, "_k8s", "k8s");
    }
}
