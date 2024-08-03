package help.lixin.starlink.plugin.jsch.api.service;

import help.lixin.starlink.plugin.jsch.api.dto.CommandExecuteResult;

public interface IShellExecuteTemplateService {
    default CommandExecuteResult execute(String batchCommand) throws Exception {
        return execute(batchCommand, null);
    }

    CommandExecuteResult execute(String batchCommand, ICommandExecuteCallback callback) throws Exception;
}
