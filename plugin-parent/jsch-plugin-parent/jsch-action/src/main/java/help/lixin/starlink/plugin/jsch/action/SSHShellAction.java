package help.lixin.starlink.plugin.jsch.action;

import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.core.log.Log;
import help.lixin.core.pipeline.PipelineContextHolder;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.starlink.plugin.jsch.service.IQuerySshHostService;
import help.lixin.service.IExpressionService;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.jsch.action.domain.command.CmdParams;
import help.lixin.starlink.plugin.jsch.action.domain.command.LoadBalance;
import help.lixin.starlink.plugin.jsch.action.dto.SSHExecuteResult;
import help.lixin.starlink.plugin.jsch.action.thread.SSHShellThread;
import help.lixin.starlink.plugin.jsch.api.properties.JschProperties;
import help.lixin.starlink.plugin.jsch.api.service.IShellExecuteTemplateService;

public class SSHShellAction implements Action {

    private Logger logger = LoggerFactory.getLogger(SSHShellAction.class);

    public static final String ACTION_NAME = "shell";

    private AbstractServiceFactory jschServiceFactory;

    private IExpressionService expressionService;

    private IQuerySshHostService querySshHostService;

    public SSHShellAction(AbstractServiceFactory jschServiceFactory, //
        IExpressionService expressionService, //
        IQuerySshHostService querySshHostService) {
        this.jschServiceFactory = jschServiceFactory;
        this.expressionService = expressionService;
        this.querySshHostService = querySshHostService;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        // 参数解析
        String stageParams = ctx.getStageParams();
        ObjectMapper mapper = new ObjectMapper();
        // 忽略未知字段
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        CmdParams actionParams = mapper.readValue(stageParams, CmdParams.class);
        Set<String> labels = actionParams.getLabels();
        String command = actionParams.getCommand();
        long executeMaxWaitTime = actionParams.getExecuteMaxWaitTime();
        LoadBalance loadBalance = actionParams.getLoadBalance();

        if (!labels.isEmpty() && StringUtils.isNotBlank(command)) {
            // 调用Service获取,标签
            Set<String> instances = querySshHostService.queryByLabel(labels);
            CountDownLatch latch = new CountDownLatch(1);
            if (instances.isEmpty()) {
                String msg = String.format("标签:[%s]未配置有可用实例.", labels.stream().collect(Collectors.joining(",")));
                Log.trace(msg);
                logger.error(msg);
                return false;
            } else {
                String instance = null;
                if (instances.size() == 1) {
                    instance = instances.stream().findFirst().get();
                } else {
                    instance = loadBalance(instances, loadBalance);
                }
                if (null != instance) {
                    SSHExecuteResult sshExecuteResult = new SSHExecuteResult();
                    sshExecuteResult.setInstance(instance);
                    try {
                        run(instance, latch, actionParams, sshExecuteResult);
                    } finally {
                        // 最终等待多少分钟
                        latch.await(executeMaxWaitTime, TimeUnit.MINUTES);
                    }
                    return sshExecuteResult.isSuccess();
                }
            }
        }
        return false;
    }

    protected String loadBalance(Set<String> instances, LoadBalance loadBalance) {
        String instance = null;
        switch (loadBalance) {
            case ROUND: // 轮询
                // TODO lixin轮询处理方案,暂时选择延用随机,后面再补充.
                // break;
            case RANDOM: // 随机
                int index = new Random().nextInt(instances.size());
                instance = instances.stream().collect(Collectors.toList()).get(index);
                break;
        }
        return instance;
    }

    protected void run(final String instance, //
        final CountDownLatch latch, //
        final CmdParams actionParams, //
        final SSHExecuteResult sshExecuteResult) {
        JschProperties jschProperties = jschServiceFactory.getInstance(instance, //
            JschProperties.class);
        IShellExecuteTemplateService shellExecuteTemplateService = jschServiceFactory.getInstance(instance, //
            IShellExecuteTemplateService.class);
        Tuple tuple = new Tuple(actionParams.getCommand(), actionParams.getCommand());
        SSHShellThread commandThread = new SSHShellThread(PipelineContextHolder.get(), //
            jschProperties, //
            shellExecuteTemplateService, //
            instance, //
            tuple, //
            sshExecuteResult, //
            latch);
        String threadName = String.format("ssh-shell-compile-[%s]", instance);
        commandThread.setName(threadName);
        commandThread.setDaemon(true);
        commandThread.start();
    }

    @Override
    public String name() {
        return ACTION_NAME;
    }

    @Override
    public String meta() {
        String meta = null;
        String pluginMetaPath = "plugin/meta/ssh-shell-compile-meta.json";
        Resource resource = new ClassPathResource(pluginMetaPath);
        if (resource.exists()) {
            try {
                meta = IOUtils.toString(resource.getInputStream(), "UTF-8");
            } catch (IOException ignore) {
                logger.warn("读取插件元配置文件[{}]出现异常,异常详细如下:[\n{},\n]", pluginMetaPath, ignore);
            }
        }
        return meta;
    }
}