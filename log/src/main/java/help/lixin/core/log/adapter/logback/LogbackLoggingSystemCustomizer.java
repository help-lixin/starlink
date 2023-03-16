package help.lixin.core.log.adapter.logback;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.Appender;
import help.lixin.core.log.service.ILoggingSystemCustomizer;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.boot.logging.logback.LogbackLoggingSystem;

import java.lang.reflect.Method;

public class LogbackLoggingSystemCustomizer implements ILoggingSystemCustomizer {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(LogbackLoggingSystemCustomizer.class);

    private Appender appender;


    public LogbackLoggingSystemCustomizer(Appender appender) {
        this.appender = appender;
    }


    @Override
    public void customizer(LoggingSystem loggingSystem) {
        if (loggingSystem instanceof LogbackLoggingSystem) {
            LogbackLoggingSystem logbackLoggingSystem = (LogbackLoggingSystem) loggingSystem;
            try {
                // 只能通过反射拿到:LoggerContext
                Method loggerContextMethod = LogbackLoggingSystem.class.getDeclaredMethod("getLoggerContext");
                loggerContextMethod.setAccessible(Boolean.TRUE);
                LoggerContext loggerContext = (LoggerContext) loggerContextMethod.invoke(logbackLoggingSystem);
                // 拿到root(每个Logger对象内部都Hold住一个root,这个root持有:Appender列表)
                Logger root = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);

                // 切记要配置上下文
                appender.setContext(loggerContext);
                appender.start();

                // 添加:Appender
                root.addAppender(appender);
                loggerContextMethod.setAccessible(Boolean.FALSE);
            } catch (Exception ignore) {
                logger.error("customizer fail:[{}]", ignore);
            }
        }
    }

    @Override
    public String supportType() {
        return LogbackLoggingSystem.class.getSimpleName();
    }
}
