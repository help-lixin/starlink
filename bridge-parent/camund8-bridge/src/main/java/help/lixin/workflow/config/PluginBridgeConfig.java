package help.lixin.workflow.config;

import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.core.pipeline.mediator.ActionMediator;
import help.lixin.workflow.bridge.PluginBridge;
import help.lixin.workflow.bridge.ZeebeWorkSubscribe;
import io.camunda.zeebe.client.ZeebeClient;

@Configuration
public class PluginBridgeConfig {

    /**
     * zeebe work订阅
     *
     * @param zeebeClient
     * @param actionMediator
     * @param pluginBridge
     * @return
     */
    // @Bean
    // TODO lixin
    public SmartLifecycle zeebeWorkSubscribe(ZeebeClient zeebeClient, //
        ActionMediator actionMediator, //
        PluginBridge pluginBridge) {
        return new ZeebeWorkSubscribe(zeebeClient, actionMediator, pluginBridge);
    }

    @Bean
    public PluginBridge pluginBridge(ActionMediator mediator) {
        return new PluginBridge(mediator);
    }
}