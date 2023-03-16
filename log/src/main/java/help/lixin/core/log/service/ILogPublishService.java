package help.lixin.core.log.service;

import help.lixin.core.log.model.LogEntry;

public interface ILogPublishService {
    void publish(LogEntry logEntry);
}
