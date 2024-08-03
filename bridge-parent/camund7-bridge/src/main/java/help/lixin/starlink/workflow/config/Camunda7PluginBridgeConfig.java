package help.lixin.workflow.config;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.core.pipeline.interceptor.IActionExecuteInterceptor;
import help.lixin.core.pipeline.mediator.ActionMediator;
import help.lixin.workflow.bridge.Camunda7PluginBridge;
import help.lixin.workflow.service.IProcessExecuteFailCallback;

@Configuration
public class Camunda7PluginBridgeConfig {
    @Bean(name = "pluginBridge")
    public Camunda7PluginBridge pluginBridge(ActionMediator mediator, //
        @Autowired(required = false) List<IProcessExecuteFailCallback> processExecuteFailCallbacks, //
        @Autowired(required = false) List<IActionExecuteInterceptor> interceptors) {
        if (null != processExecuteFailCallbacks //
            && !processExecuteFailCallbacks.isEmpty()) {
            Collections.sort(processExecuteFailCallbacks, (i, j) -> {
                return i.getOrder() - j.getOrder();
            });
        }
        return new Camunda7PluginBridge(mediator, interceptors, processExecuteFailCallbacks);
    }
}
