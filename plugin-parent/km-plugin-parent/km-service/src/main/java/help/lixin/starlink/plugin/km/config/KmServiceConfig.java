package help.lixin.starlink.plugin.km.config;

import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.km.service.IKafkaConnectorStateService;
import help.lixin.starlink.plugin.km.service.impl.KafkaConnectorStateServiceImpl;
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
public class KmServiceConfig {

    @Bean
    @ConditionalOnMissingBean
    public IKafkaConnectorStateService kafkaConnectorStateService(@Autowired @Qualifier("kmPluginNamedContextFactory") PluginNamedContextFactory pluginNamedContextFactory) {
        return new KafkaConnectorStateServiceImpl(pluginNamedContextFactory);
    }

}
