package help.lixin.starlink.plugin.xxl.job.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.xxl.job.service.IXxlJobGroupService;
import help.lixin.starlink.plugin.xxl.job.service.IXxlJobInfoService;
import help.lixin.starlink.plugin.xxl.job.service.IXxlJobLogService;
import help.lixin.starlink.plugin.xxl.job.service.IXxlLoginService;
import help.lixin.starlink.plugin.xxl.job.service.impl.XxlJobGroupServiceImpl;
import help.lixin.starlink.plugin.xxl.job.service.impl.XxlJobInfoServiceImpl;
import help.lixin.starlink.plugin.xxl.job.service.impl.XxlJobLogServiceImpl;
import help.lixin.starlink.plugin.xxl.job.service.impl.XxlLoginServiceImpl;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/18 4:01 下午
 * @Description
 */
@Configuration
public class XxlJobServiceCnofig {

    @Bean
    @ConditionalOnMissingBean
    public IXxlJobInfoService xxlJobInfoService(
        @Autowired @Qualifier("jobPluginNamedContextFactory") PluginNamedContextFactory pluginNamedContextFactory) {
        return new XxlJobInfoServiceImpl(pluginNamedContextFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public IXxlJobGroupService xxlJobGroupService(
        @Autowired @Qualifier("jobPluginNamedContextFactory") PluginNamedContextFactory pluginNamedContextFactory) {
        return new XxlJobGroupServiceImpl(pluginNamedContextFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public IXxlJobLogService xxlJobLogService(
        @Autowired @Qualifier("jobPluginNamedContextFactory") PluginNamedContextFactory pluginNamedContextFactory) {
        return new XxlJobLogServiceImpl(pluginNamedContextFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public IXxlLoginService xxlLoginService(
        @Autowired @Qualifier("jobPluginNamedContextFactory") PluginNamedContextFactory pluginNamedContextFactory) {
        return new XxlLoginServiceImpl(pluginNamedContextFactory);
    }
}
