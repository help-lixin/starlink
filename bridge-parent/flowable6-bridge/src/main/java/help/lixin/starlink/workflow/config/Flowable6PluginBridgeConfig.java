package help.lixin.workflow.config;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.core.pipeline.interceptor.IActionExecuteInterceptor;
import help.lixin.core.pipeline.mediator.ActionMediator;
import help.lixin.workflow.bridge.Flowable6PluginBridge;
import help.lixin.workflow.service.IProcessExecuteFailCallback;

@Configuration
public class Flowable6PluginBridgeConfig {

    @Bean
    public Flowable6PluginBridge pluginBridge(ActionMediator mediator, //
        @Autowired(required = false) List<IProcessExecuteFailCallback> processExecuteFailCallbacks, //
        @Autowired(required = false) List<IActionExecuteInterceptor> interceptors) {
        if (null != processExecuteFailCallbacks //
            && !processExecuteFailCallbacks.isEmpty()) {
            Collections.sort(processExecuteFailCallbacks, (i, j) -> {
                return i.getOrder() - j.getOrder();
            });
        }
        return new Flowable6PluginBridge(mediator, interceptors, processExecuteFailCallbacks);
    }

}
