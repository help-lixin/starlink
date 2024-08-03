package help.lixin.starlink.core.plugin.service;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;

/**
 * 通过工厂/实例code/Class,从子容器里获取Class
 */
public abstract class AbstractServiceFactory {

    protected final PluginNamedContextFactory pluginNamedContextFactory;

    public AbstractServiceFactory(PluginNamedContextFactory pluginNamedContextFactory) {
        this.pluginNamedContextFactory = pluginNamedContextFactory;
    }

    public PluginNamedContextFactory getPluginNamedContextFactory() {
        return pluginNamedContextFactory;
    }

    public <T> T getInstance(String instance, //
        Class<T> clazz) {
        return (T)pluginNamedContextFactory.getInstance(instance, clazz);
    }
}