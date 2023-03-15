package help.lixin.core.log.config;

import ch.qos.logback.core.Appender;
import help.lixin.core.log.ILogPublishService;
import help.lixin.core.log.ILoggingSystemCustomizer;
import help.lixin.core.log.logback.LogbackLogHubAppender;
import help.lixin.core.log.logback.LogbackLoggingSystemCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(name = {"ch.qos.logback.core.Appender", "ch.qos.logback.classic.LoggerContext"})
public class LogbackConfig {

    @Bean
    @ConditionalOnMissingBean(name = "logbackLoggingSystemCustomizer")
    public ILoggingSystemCustomizer logbackLoggingSystemCustomizer(Appender logbackLogHubAppender) {
        return new LogbackLoggingSystemCustomizer(logbackLogHubAppender);
    }

    @Bean
    @ConditionalOnMissingBean
    public Appender logbackLogHubAppender(ILogPublishService logPublishService) {
        LogbackLogHubAppender logbackLogHubAppender = new LogbackLogHubAppender();
        logbackLogHubAppender.setLogPublishService(logPublishService);
        return logbackLogHubAppender;
    }
}
