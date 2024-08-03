package help.lixin.starlink.core.pipeline.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.pipeline.callback.ProcessExecuteMessageProvider;
import help.lixin.starlink.core.pipeline.callback.SendProcessExecuteCompleteCallback;
import help.lixin.starlink.core.pipeline.callback.SendProcessExecuteFailCallback;
import help.lixin.transport.client.Client;
import help.lixin.workflow.service.IProcessExecuteCompleteCallback;
import help.lixin.workflow.service.IProcessExecuteFailCallback;

@Configuration
@ConditionalOnClass(name = { //
    "help.lixin.transport.client.Client", //
    "help.lixin.transport.client.config.MessageProcessConfig", //
    "help.lixin.transport.client.config.TransportClientConfig" //
})
public class ProcessExecuteCallbackConfig {

    @Bean
    @ConditionalOnMissingBean(name = "processInstanceMessageProvider")
    public ProcessExecuteMessageProvider processInstanceMessageProvider(Client client) {
        return new ProcessExecuteMessageProvider(client);
    }

    @Bean
    @ConditionalOnMissingBean(name = "sendProcessExecuteCompleteCallback")
    public IProcessExecuteCompleteCallback
        sendProcessExecuteCompleteCallback(ProcessExecuteMessageProvider processExecuteMessageProvider) {
        return new SendProcessExecuteCompleteCallback(processExecuteMessageProvider);
    }

    @Bean
    @ConditionalOnMissingBean(name = "sendProcessExecuteFailCallback")
    public IProcessExecuteFailCallback
        sendProcessExecuteFailCallback(ProcessExecuteMessageProvider processExecuteMessageProvider) {
        return new SendProcessExecuteFailCallback(processExecuteMessageProvider);
    }
}
