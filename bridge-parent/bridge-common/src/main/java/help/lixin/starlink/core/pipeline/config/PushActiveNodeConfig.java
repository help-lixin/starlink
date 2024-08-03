package help.lixin.core.pipeline.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import help.lixin.core.pipeline.interceptor.IActionExecuteInterceptor;
import help.lixin.starlink.core.pipeline.interceptor.impl.PushActiveNodeInterceptor;
import help.lixin.transport.client.Client;
import help.lixin.transport.client.config.MessageProcessConfig;
import help.lixin.transport.client.config.TransportClientConfig;

@Configuration
@ConditionalOnClass(name = { //
    "help.lixin.transport.client.Client", //
    "help.lixin.transport.client.config.MessageProcessConfig", //
    "help.lixin.transport.client.config.TransportClientConfig" //
})
public class PushActiveNodeConfig {

    @Configuration
    @Import(value = {MessageProcessConfig.class, TransportClientConfig.class})
    public class ImportClient {}

    @Bean
    @ConditionalOnMissingBean(name = "pushActiveNodeInterceptor")
    public IActionExecuteInterceptor pushActiveNodeInterceptor(Client client) {
        return new PushActiveNodeInterceptor(client);
    }
}
