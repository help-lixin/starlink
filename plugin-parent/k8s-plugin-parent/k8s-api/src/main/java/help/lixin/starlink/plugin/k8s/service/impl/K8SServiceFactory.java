package help.lixin.starlink.plugin.k8s.service.impl;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;

public class K8SServiceFactory extends AbstractServiceFactory {

    public K8SServiceFactory(PluginNamedContextFactory pluginNamedContextFactory) {
        super(pluginNamedContextFactory);
    }
}
