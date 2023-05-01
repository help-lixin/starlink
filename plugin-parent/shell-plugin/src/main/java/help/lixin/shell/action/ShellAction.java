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
        logger.info("开始执行Shell插件");
        String stageParams = ctx.getStageParams();
        ObjectMapper mapper = new ObjectMapper();
        ShellParams shellParams = mapper.readValue(stageParams, ShellParams.class);
        List<String> commands = getCommands(shellParams, ctx);
        String batchCommand = commands.stream().collect(Collectors.joining(" && "));
        Process process = null;
        try {
            logger.debug("开始执行Shell:[{}]", batchCommand);
            // 要把所有的命令,拼装成一条语句.
            ProcessBuilder processBuilder = new ProcessBuilder(new String[]{"/bin/bash", "-c", batchCommand});
            process = processBuilder.start();
            int exitCode = process.waitFor();
            InputStream inputStream = process.getInputStream();
            if (exitCode == 0) { // 正常退出
                String success = IOUtils.readLines(inputStream, "UTF-8")
                        //
                        .stream()
                        //
                        .collect(Collectors.joining(" \n "));
                logger.error("执行Shell Command:[{}]成功,执行过程如下:[{}]", batchCommand, success);
            } else {
                String error = IOUtils.readLines(process.getErrorStream(), "UTF-8").stream().collect(Collectors.joining(" \n "));
                logger.error("执行Shell Command:[{}]失败,失败详细信息如下:[{}]", batchCommand, error);
                throw new RuntimeException(error);
            }
        } finally {
            if (null != process) {
                process.destroy();
            }
        }
        logger.info("执行Shell插件结束");
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
