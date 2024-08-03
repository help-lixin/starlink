package help.lixin.starlink.plugin.jsch.api.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.Session;

import help.lixin.core.log.Log;
import help.lixin.core.log.model.LogEntry;
import help.lixin.starlink.plugin.jsch.api.dto.CommandExecuteResult;
import help.lixin.starlink.plugin.jsch.api.service.ICommandExecuteCallback;
import help.lixin.starlink.plugin.jsch.api.service.IShellExecuteTemplateService;

public class ShellExecuteTemplateService implements IShellExecuteTemplateService {

    private final Session session;

    private static final String IGNORE_LOGOUT = "logout";
    private static final String IGNORE_FILE = "\u001B]7;file://";
    private static final String ANSI_CODES_REGEX = "\u001B\\[[;\\d]*m";
    private static final String IGNORE_LAST_LOGIN = "Last login";
    private static final String IGNORE_USER_INFO_REGEX = "\\[(.*?) ~\\]\\$ (.*?)";
    private static final String IGNORE_ROOT_INFO_REGEX = "\\[(.*?) ~\\]\\# (.*?)";

    public ShellExecuteTemplateService(Session session) {
        this.session = session;
    }

    @Override
    public CommandExecuteResult execute(String batchCommand, ICommandExecuteCallback callback) throws Exception {
        CommandExecuteResult executeResult = new CommandExecuteResult();
        ChannelShell channel = null;
        try {
            channel = (ChannelShell)session.openChannel("shell");
            if (null != callback) {
                callback.connectBeforeCallback(channel);
            }
            channel.connect();
            String command = String.format("%s\n%s\r\n", batchCommand, "exit");
            OutputStream os = channel.getOutputStream();
            // 发送指令
            os.write(command.getBytes());
            os.flush();
            // 提取最后一条命令
            Set<String> commands = Arrays.stream(StringUtils.split(command, "\n")) //
                .filter(cmd -> null != cmd)//
                .map(cmd -> cmd.trim()) //
                .collect(Collectors.toSet());

            StringBuilder buffer = new StringBuilder();
            Queue<String> cache = new LinkedList<>();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(channel.getInputStream()))) {
                String consoleLine;
                int count = 0;
                while ((consoleLine = reader.readLine()) != null) {
                    if (StringUtils.isEmpty(consoleLine) || //
                        isContains(consoleLine, commands) || //
                        StringUtils.contains(consoleLine, IGNORE_FILE) || //
                        StringUtils.contains(consoleLine, IGNORE_LOGOUT) || //
                        StringUtils.contains(consoleLine, IGNORE_LAST_LOGIN) || //
                        StringUtils.contains(consoleLine, " -p") || //
                        StringUtils.contains(consoleLine, "p/") || //
                        isMatchUserInfo(consoleLine) || //
                        isMatchRootInfo(consoleLine) //
                    ) {
                        continue;
                    }
                    // 通过正则,去除:ANSI codes
                    String newConsoleLine = consoleLine.replaceAll(ANSI_CODES_REGEX, "") + "\n";
                    cache.add(newConsoleLine);
                    buffer.append(newConsoleLine);
                    count++;
                    if (count % 500 == 0) { // 当达到100次时,进行一次flush,但是,有时候,都读不到100次.
                        flush(cache);
                    }
                }
            }

            if (cache.size() > 0) { // 兜底flush
                flush(cache);
            }
            executeResult.setSuccess(Boolean.TRUE);
            executeResult.setMsg(buffer.toString());
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != channel) {
                channel.disconnect();
            }
        }
        return executeResult;
    }

    protected void flush(Queue<String> cache) {
        StringBuilder buffer = new StringBuilder();
        String consoleLine = null;
        while ((consoleLine = cache.poll()) != null) {
            buffer.append(consoleLine);
        }
        if (buffer.length() > 0) {
            String newConsoleLine = buffer.toString();
            // 在此处:要定制日志的输出,不配置日志当前的时间
            LogEntry logEntry = LogEntry //
                .newBuild()//
                .withBody(newConsoleLine) //
                .build();
            // 记录日志
            Log.trace(logEntry);
        }
    }

    protected boolean isMatchUserInfo(String text) {
        Pattern pattern = Pattern.compile(IGNORE_USER_INFO_REGEX);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    protected boolean isMatchRootInfo(String text) {
        Pattern pattern = Pattern.compile(IGNORE_ROOT_INFO_REGEX);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    protected boolean isContains(String line, Set<String> commands) {
        boolean isContains = false;
        for (String cmd : commands) {
            if (StringUtils.contains(line, cmd)) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }

}
