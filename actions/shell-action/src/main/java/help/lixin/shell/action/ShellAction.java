package help.lixin.shell.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.shell.service.ShellFaceService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShellAction implements Action {
    private Logger logger = LoggerFactory.getLogger(ShellAction.class);
    public static final String SHELL_ACTION = "shell";

    public ShellFaceService shellFaceService;

    public ShellAction(ShellFaceService shellFaceService) {
        this.shellFaceService = shellFaceService;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("start execute action: [{}],ctx:[{}]", this.getClass().getName(), ctx);
        }
        String stageParams = ctx.getStageParams();
        ObjectMapper mapper = new ObjectMapper();
        ShellParams shellParams = mapper.readValue(stageParams, ShellParams.class);
        List<String> commands = getCommands(shellParams, ctx);
        String batchCommand = commands.stream().collect(Collectors.joining(" && "));
        Process process = null;
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("start execute batch shell command:[{}]", batchCommand);
            }
            // 要把所有的命令,拼装成一条语句.
            ProcessBuilder processBuilder = new ProcessBuilder(new String[]{"/bin/bash", "-c", batchCommand});
            process = processBuilder.start();
            int exitCode = process.waitFor();
            InputStream inputStream = process.getInputStream();
            if (exitCode == 0) { // 正常退出
                String success = IOUtils.readLines(inputStream, "UTF-8").stream().collect(Collectors.joining(" \n "));
                if (logger.isDebugEnabled()) {
                    logger.debug("end execute batch shell command:[{}],SUCCESS:[{}]", batchCommand, success);
                }
            } else {
                String error = IOUtils.readLines(process.getErrorStream(), "UTF-8").stream().collect(Collectors.joining(" \n "));
                if (logger.isDebugEnabled()) {
                    logger.debug("end execute batch shell command:[{}],FAIL:[{}]", batchCommand, error);
                }
            }
        } finally {
            if (null != process) {
                process.destroy();
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("end execute action: [{}],ctx:[{}]", this.getClass().getName(), ctx);
        }
        return true;
    }


    protected List<String> getCommands(ShellParams shellParams, PipelineContext ctx) {
        List<String> resultCommands = new ArrayList<>();
        // 提交上来的命令
        List<String> submitCommands = shellParams.getCmds();
        for (String cmd : submitCommands) {
            String trimCmd = cmd.trim();
            if (trimCmd.indexOf("$") > 0) {
                Map<String, Object> tempVars = new HashMap<>();
                tempVars.putAll(ctx.getVars());
                String command = shellFaceService.getExpressionService().prase(trimCmd, tempVars);
                resultCommands.add(command);
            } else {
                resultCommands.add(trimCmd);
            }
        }
        return resultCommands;
    }


    @Override
    public String name() {
        return SHELL_ACTION;
    }
}
