package help.lixin.starlink.plugin.nacos.config;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.nacos.mapper.NacosConfigCenterMapper;
import help.lixin.starlink.plugin.nacos.service.INacosConfigService;
import help.lixin.starlink.plugin.nacos.service.INacosInstanceService;
import help.lixin.starlink.plugin.nacos.service.INacosNameSpaceService;
import help.lixin.starlink.plugin.nacos.service.INacosServiceManageService;
import help.lixin.starlink.plugin.nacos.service.impl.NacosConfigService;
import help.lixin.starlink.plugin.nacos.service.impl.NacosInstanceService;
import help.lixin.starlink.plugin.nacos.service.impl.NacosNameSpaceService;
import help.lixin.starlink.plugin.nacos.service.impl.NacosServiceManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/18 4:01 下午
 * @Description
 */
@Configuration
public class NacosServiceCnofig {

    @Bean
    @ConditionalOnMissingBean
    public INacosConfigService nacosConfigService(@Autowired @Qualifier("nacosPluginNamedContextFactory") PluginNamedContextFactory pluginNamedContextFactory,
                                                  NacosConfigCenterMapper nacosConfigCenterMapper,//
                                                  QueryTemplate queryTemplate){
        return new NacosConfigService(pluginNamedContextFactory, nacosConfigCenterMapper, queryTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public INacosInstanceService nacosInstanceService(@Autowired @Qualifier("nacosPluginNamedContextFactory") PluginNamedContextFactory pluginNamedContextFactory){
        return new NacosInstanceService(pluginNamedContextFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public INacosNameSpaceService nacosNameSpaceService(@Autowired @Qualifier("nacosPluginNamedContextFactory") PluginNamedContextFactory pluginNamedContextFactory){
        return new NacosNameSpaceService(pluginNamedContextFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public INacosServiceManageService nacosServiceManageService(@Autowired @Qualifier("nacosPluginNamedContextFactory") PluginNamedContextFactory pluginNamedContextFactory){
        return new NacosServiceManageService(pluginNamedContextFactory);
    }

}
