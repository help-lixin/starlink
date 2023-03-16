package help.lixin.core.log.service;

import help.lixin.core.log.model.LogEntry;

public interface ILogEntryHandlerService {
    void handler(LogEntry logEntry);
}
