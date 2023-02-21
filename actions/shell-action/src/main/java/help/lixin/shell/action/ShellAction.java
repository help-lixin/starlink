package help.lixin.shell.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.shell.service.ShellFaceService;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShellAction implements Action {
    public static final String SHELL_ACTION = "shell";

    public ShellFaceService shellFaceService;

    public ShellAction(ShellFaceService shellFaceService) {
        this.shellFaceService = shellFaceService;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        String stageParams = ctx.getStageParams();
        ObjectMapper mapper = new ObjectMapper();
        ShellParams shellParams = mapper.readValue(stageParams, ShellParams.class);
        List<String> commands = getCommands(shellParams, ctx);
        Process process = Runtime.getRuntime().exec(new String[]{  });
        InputStream inputStream = process.getInputStream();

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
