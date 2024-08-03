package help.lixin.starlink.plugin.eureka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.eureka.service.IEurekaClientService;
import help.lixin.starlink.plugin.eureka.service.impl.EurekaClientServiceImpl;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/18 4:01 下午
 * @Description
 */
@Configuration
public class EurekaServiceCnofig {

    @Bean
    @ConditionalOnMissingBean(name = "eurekaClientService")
    public IEurekaClientService eurekaClientService(
        @Autowired @Qualifier("eurekaPluginNamedContextFactory") PluginNamedContextFactory pluginNamedContextFactory) {
        return new EurekaClientServiceImpl(pluginNamedContextFactory);
    }

}
