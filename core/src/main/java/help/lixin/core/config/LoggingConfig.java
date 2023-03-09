package help.lixin.core.config;

import help.lixin.core.log.ILogEntryHandlerService;
import help.lixin.core.log.impl.DefaultLogPublishService;
import help.lixin.core.log.ILogPublishService;
import help.lixin.core.log.ILoggingSystemCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoggingConfig {


    @Bean
    @ConditionalOnMissingBean

    public ILogPublishService logPublishService(@Autowired(required = false)
                                                //
                                                List<ILogEntryHandlerService> logEntryHandlerServices) {
        return new DefaultLogPublishService(logEntryHandlerServices);
    }

    @Configuration
    public static class LoggingSystemCustomizer {
        @Autowired
        @Qualifier("springBootLoggingSystem")
        private LoggingSystem loggingSystem;

        @Autowired(required = false)
        private List<ILoggingSystemCustomizer> customizers = new ArrayList<>();


        @PostConstruct
        public void customizer() {
            String loggingSystemName = loggingSystem.getClass().getSimpleName();
            for (ILoggingSystemCustomizer customizer : customizers) {
                if (customizer.supportType().equals(loggingSystemName)) {
                    customizer.customizer(loggingSystem);
                    break;
                }
            }
        }
    }
}
