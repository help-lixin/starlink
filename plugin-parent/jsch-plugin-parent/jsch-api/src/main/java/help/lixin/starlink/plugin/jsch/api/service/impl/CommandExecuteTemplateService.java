package help.lixin.starlink.plugin.jsch.api.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;

import help.lixin.starlink.plugin.jsch.api.dto.CommandExecuteResult;
import help.lixin.starlink.plugin.jsch.api.service.ICommandExecuteCallback;
import help.lixin.starlink.plugin.jsch.api.service.ICommandExecuteTemplateService;

public class CommandExecuteTemplateService implements ICommandExecuteTemplateService {

    private final Session session;

    public CommandExecuteTemplateService(Session session) {
        this.session = session;
    }

    @Override
    public CommandExecuteResult execute(String command, ICommandExecuteCallback callback) throws Exception {
        CommandExecuteResult executeResult = new CommandExecuteResult();
        ChannelExec channel = null;
        try {
            channel = (ChannelExec)session.openChannel("exec");
            InputStream inputStream = channel.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            channel.setErrStream(baos);
            channel.setCommand(command);
            if (null != callback) {
                callback.connectBeforeCallback(channel);
            }
            channel.connect();
            String result = IOUtils.toString(inputStream, "UTF-8");
            String error = null;
            if (baos.size() > 0) {
                error = new String(baos.toByteArray());
            }
            if (null != error) {
                executeResult.setSuccess(Boolean.FALSE);
                executeResult.setMsg(error);
            } else {
                executeResult.setSuccess(Boolean.TRUE);
                executeResult.setMsg(result);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != channel) {
                channel.disconnect();
            }
        }
        return executeResult;
    }
}
