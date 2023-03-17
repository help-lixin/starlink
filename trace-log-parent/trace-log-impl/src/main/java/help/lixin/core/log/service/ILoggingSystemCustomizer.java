package help.lixin.core.log.service;

import org.springframework.boot.logging.LoggingSystem;

public interface ILoggingSystemCustomizer {

    void customizer(LoggingSystem loggingSystem);


    String supportType();
}
