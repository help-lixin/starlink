package help.lixin.starlink.plugin.jsch.api.service;

import help.lixin.starlink.plugin.jsch.api.dto.CommandExecuteResult;

public interface ICommandExecuteTemplateService {

    default CommandExecuteResult execute(String command) throws Exception {
        return execute(command, null);
    }

    CommandExecuteResult execute(String command, ICommandExecuteCallback callback) throws Exception;
}
