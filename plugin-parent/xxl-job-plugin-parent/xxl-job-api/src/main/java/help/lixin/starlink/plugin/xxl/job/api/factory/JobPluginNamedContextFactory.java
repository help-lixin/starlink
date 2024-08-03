package help.lixin.starlink.plugin.xxl.job.api.factory;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.plugin.Specification;
import help.lixin.starlink.plugin.xxl.job.api.config.XxlJobConfig;


public class JobPluginNamedContextFactory extends PluginNamedContextFactory<Specification> {
    public JobPluginNamedContextFactory() {
        super(XxlJobConfig.class, "_job", "xxl-job");
    }
}
