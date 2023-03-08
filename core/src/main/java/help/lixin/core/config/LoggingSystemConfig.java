package help.lixin.core.config;

import help.lixin.core.log.ILoggingSystemCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoggingSystemConfig {

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
