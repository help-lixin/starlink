package help.lixin.starlink.plugin.jsch.action;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.core.pipeline.PipelineContextHolder;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.starlink.plugin.jsch.service.IQuerySshHostService;
import help.lixin.service.IExpressionService;
import help.lixin.starlink.core.action.thread.ActionThread;
import help.lixin.core.log.Log;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.jsch.action.domain.command.ExecuteStrategy;
import help.lixin.starlink.plugin.jsch.action.domain.command.ExecuteSuccessStrategy;
import help.lixin.starlink.plugin.jsch.action.domain.copy.LocalDir;
import help.lixin.starlink.plugin.jsch.action.domain.copy.SSHDeployActionParams;
import help.lixin.starlink.plugin.jsch.action.dto.SSHExecuteResult;
import help.lixin.starlink.plugin.jsch.api.dto.CommandExecuteResult;
import help.lixin.starlink.plugin.jsch.api.dto.Upload;
import help.lixin.starlink.plugin.jsch.api.properties.JschProperties;
import help.lixin.starlink.plugin.jsch.api.service.ISecureCopyService;
import help.lixin.starlink.plugin.jsch.api.service.IShellExecuteTemplateService;

public class SSHDeployAction implements Action {

    private final Logger logger = LoggerFactory.getLogger(SSHDeployAction.class);

    public static final String SSH_ACTION = "ssh-deploy";

    private final AbstractServiceFactory jschServiceFactory;

    private final IExpressionService expressionService;

    private final IQuerySshHostService queryHostService;

    public SSHDeployAction(AbstractServiceFactory jschServiceFactory, //
        IExpressionService expressionService, //
        IQuerySshHostService queryHostService) {
        this.jschServiceFactory = jschServiceFactory;
        this.expressionService = expressionService;
        this.queryHostService = queryHostService;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        logger.info("开始运行插件:[{}]", this.getClass().getName());
        AtomicBoolean isExecuteSuccess = new AtomicBoolean(true);
        // 参数解析
        String stageParams = ctx.getStageParams();
        ObjectMapper mapper = new ObjectMapper();
        // 忽略未知字段
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SSHDeployActionParams actionParams = mapper.readValue(stageParams, SSHDeployActionParams.class);
        // 串行/并行化
        ExecuteStrategy executeStrategy = actionParams.getExecuteStrategy();

        Set<String> labels = actionParams.getLabels();
        if (!labels.isEmpty()) {
            // 调用Service获取,标签
            Set<String> instances = queryHostService.queryByLabel(labels);

            CountDownLatch latch = new CountDownLatch(instances.size());
            // 待copy任务
            Map<String, SSHExecuteResult> sshCopyResultMap = instances //
                .stream() //
                .map(instance -> {
                    SSHExecuteResult result = new SSHExecuteResult();
                    result.setInstance(instance);
                    return result;
                }) //
                .collect(
                    Collectors.toUnmodifiableMap(SSHExecuteResult::getInstance, SSHExecuteResult -> SSHExecuteResult));

            try {
                if (executeStrategy.equals(ExecuteStrategy.SERIAL)) { // 串行化执行
                    serial(instances, latch, actionParams, sshCopyResultMap);
                }

                if (executeStrategy.equals(ExecuteStrategy.PARALLEL)) { // 并行执行
                    parallel(instances, latch, actionParams, sshCopyResultMap);
                }
            } finally {
                // 最终等待多少分钟
                latch.await(actionParams.getExecuteMaxWaitTime(), TimeUnit.SECONDS);
            }

            // 统一对执行成功与否的策略进行处理.
            // 执行成功策略(只要有一个成功,即成功?还是要等到所有的成功才是真正的成功)
            ExecuteSuccessStrategy executeSuccessStrategy = actionParams.getExecuteSuccessStrategy();

            if (executeSuccessStrategy.equals(ExecuteSuccessStrategy.ALL_SUCCESS)) {
                sshCopyResultMap.forEach((key, instance) -> {
                    // &&
                    isExecuteSuccess.set(isExecuteSuccess.get() && instance.isSuccess());
                });
            } else {
                // ||
                sshCopyResultMap.forEach((key, instance) -> {
                    isExecuteSuccess.set(isExecuteSuccess.get() || instance.isSuccess());
                });
            }
        }
        logger.debug("结束插件:[{}]运行", this.getClass().getName());
        return isExecuteSuccess.get();
    }

    protected void parallel(final Set<String> instances, //
        final CountDownLatch latch, //
        final SSHDeployActionParams actionParams, //
        final Map<String, SSHExecuteResult> sshCopyResultMap) {
        for (String instance : instances) {
            JschProperties jschProperties = jschServiceFactory.getInstance(instance, //
                JschProperties.class);
            ISecureCopyService secureCopyService = jschServiceFactory.getInstance(instance, //
                ISecureCopyService.class);
            IShellExecuteTemplateService shellExecuteTemplateService = jschServiceFactory.getInstance(instance, //
                IShellExecuteTemplateService.class);

            SSHExecuteResult copyResult = sshCopyResultMap.get(instance);
            SSHCopyThread sshCopyThread = new SSHCopyThread( //
                PipelineContextHolder.get() //
                , jschProperties, //
                secureCopyService, //
                shellExecuteTemplateService, //
                instance, //
                actionParams, //
                copyResult, //
                latch);
            String threadName = String.format("ssh-deploy-thread-[%s]", instance);
            sshCopyThread.setName(threadName);
            sshCopyThread.setDaemon(true);
            sshCopyThread.start();
        }
    }

    protected void serial(final Set<String> instances, //
        final CountDownLatch latch, //
        final SSHDeployActionParams actionParams, //
        final Map<String, SSHExecuteResult> sshCopyResultMap) {
        ExecuteSuccessStrategy executeSuccessStrategy = actionParams.getExecuteSuccessStrategy();
        for (String instance : instances) {
            JschProperties jschProperties = jschServiceFactory.getInstance(instance, //
                JschProperties.class);
            ISecureCopyService secureCopyService = jschServiceFactory.getInstance(instance, //
                ISecureCopyService.class);
            IShellExecuteTemplateService shellExecuteTemplateService = jschServiceFactory.getInstance(instance, //
                IShellExecuteTemplateService.class);

            SSHExecuteResult copyResult = sshCopyResultMap.get(instance);
            SSHCopyThread sshCopyThread = new SSHCopyThread(PipelineContextHolder.get(), //
                jschProperties, //
                secureCopyService, //
                shellExecuteTemplateService, //
                instance, //
                actionParams, //
                copyResult, //
                latch);
            String threadName = String.format("ssh-copy-thread-[%s]", instance);
            sshCopyThread.setName(threadName);
            sshCopyThread.setDaemon(true);
            try {
                sshCopyThread.runInternal();
            } catch (Exception e) {
                // 当用户配置,要求是:全部成功,在这种情况下,只要有一个异常,直接跳出循环,不要再继续往下执行下去了,直接跳过执行
                if (executeSuccessStrategy.equals(ExecuteSuccessStrategy.ALL_SUCCESS)) {
                    break;
                }
            }
        }
    }

    @Override
    public String name() {
        return SSH_ACTION;
    }

    @Override
    public String meta() {
        String meta = null;
        String pluginMetaPath = "plugin/meta/ssh-deploy-meta.json";
        Resource resource = new ClassPathResource(pluginMetaPath);
        if (resource.exists()) {
            try {
                meta = IOUtils.toString(resource.getInputStream(), "UTF-8");
            } catch (IOException ignore) {
                logger.warn("读取插件元配置文件[{}]出现异常,异常详细如下:[\n{},\n]", pluginMetaPath, ExceptionUtils.getStackTrace(ignore));
            }
        }
        return meta;
    }
}

class SSHCopyThread extends ActionThread {
    private Logger logger = LoggerFactory.getLogger(SSHCopyThread.class);
    private final JschProperties jschProperties;
    private final ISecureCopyService secureCopyService;
    private final IShellExecuteTemplateService shellExecuteTemplateService;
    private final SSHDeployActionParams actionParams;
    private final String instance;
    private final CountDownLatch latch;

    private SSHExecuteResult copyResult;

    public SSHCopyThread(PipelineContext ctx, //
        JschProperties jschProperties, //
        ISecureCopyService secureCopyService, //
        IShellExecuteTemplateService shellExecuteTemplateService, //
        String instance, //
        SSHDeployActionParams actionParams, //
        SSHExecuteResult copyResult, //
        CountDownLatch latch) {
        super(ctx);
        this.jschProperties = jschProperties;
        this.secureCopyService = secureCopyService;
        this.shellExecuteTemplateService = shellExecuteTemplateService;
        this.instance = instance;
        this.actionParams = actionParams;
        this.copyResult = copyResult;
        this.latch = latch;
    }

    @Override
    public void runInternal() {
        try {
            // 远程主机IP
            String host = jschProperties.getHost();
            // 远程主机用户名
            String username = jschProperties.getUsername();
            // 执行是否成功
            Boolean beforeCommandExecuteFlag = null;
            // 拷贝之前命令
            String beforeCommand = actionParams.getBeforeCommand();
            if (null != beforeCommand) {
                CommandExecuteResult beforeRes = shellExecuteTemplateService.execute(beforeCommand);
                beforeCommandExecuteFlag = beforeRes.isSuccess();
                String msg = beforeCommandExecuteFlag ? "成功" : "失败";
                if (beforeCommandExecuteFlag) {
                    String beforeExecuteMsg = String.format("主机[%s],执行Shell命令:[%s] %s", host, beforeCommand, msg);
                    Log.trace(beforeExecuteMsg);
                    logger.info(beforeExecuteMsg);
                } else {
                    String beforeExecuteMsg = String.format("主机[%s],执行Shell命令:[%s] %s,运行时日志如下:%s", host, beforeCommand,
                        msg, beforeRes.getMsg());
                    Log.trace(beforeExecuteMsg);
                    logger.info(beforeExecuteMsg);
                }
            }

            // 如果,用户没有配置(beforeCommand),又或者,beforeCommand命令执行成功,才能进行:拷贝和后置命令的执行
            if (null == beforeCommandExecuteFlag || beforeCommandExecuteFlag) {
                String localFile = actionParams.getLocalFile();
                LocalDir localDir = actionParams.getLocalDir();
                String remoteDir = actionParams.getRemoteDir();

                Upload upload = new Upload();
                upload.setRemoteDir(remoteDir);

                StringBuilder localCommandFormatBuilder = new StringBuilder();
                if (null != localDir) {
                    String tempLocalDir = localDir.getLocalDir();
                    String excludes = localDir.getExcludes();
                    String includes = localDir.getIncludes();

                    if (StringUtils.isNotBlank(tempLocalDir)) {
                        help.lixin.starlink.plugin.jsch.api.dto.LocalDir.Builder builder =
                            help.lixin.starlink.plugin.jsch.api.dto.LocalDir.newBuilder();
                        builder.withLocalDir(tempLocalDir);

                        localCommandFormatBuilder.append(tempLocalDir);
                        if (StringUtils.isNotBlank(includes)) {
                            builder.withIncludes(includes);

                            localCommandFormatBuilder.append(includes);
                        }
                        if (StringUtils.isNotBlank(excludes)) {
                            builder.withExcludes(excludes);
                        }
                        upload.setLocalDir(builder.build());
                    }
                } else {
                    upload.setLocalFile(localFile);

                    localCommandFormatBuilder.append(localFile);
                }

                String localCommandFormat = localCommandFormatBuilder.toString();
                String remoteHostFormat = String.format("%s:%s@%s:%s", username, "****", host, remoteDir);

                String beforeCopyMsg =
                    String.format("主机[%s],准备执行命令[scp -r %s %s ]", host, localCommandFormat, remoteHostFormat);
                // Log.trace(beforeCopyMsg);
                logger.info(beforeCopyMsg);

                secureCopyService.upload(upload);

                String afterCopyMsg =
                    String.format("主机[%s],执行命令[scp -r %s %s ]", host, localCommandFormat, remoteHostFormat);
                Log.trace(afterCopyMsg);
                logger.info(afterCopyMsg);

                // 拷贝之后命令
                String afterCommand = actionParams.getAfterCommand();
                if (null != afterCommand) {
                    CommandExecuteResult afterRes = shellExecuteTemplateService.execute(afterCommand);
                    boolean afterCommandExecuteFlag = afterRes.isSuccess();
                    String msg = afterCommandExecuteFlag ? "成功" : "失败";
                    if (afterCommandExecuteFlag) {
                        String afterCommandExecuteMsg =
                            String.format("主机[%s],执行Shell命令:[%s] %s", host, afterCommand, msg);
                        Log.trace(afterCommandExecuteMsg);
                        logger.info(afterCommandExecuteMsg);
                    } else {
                        String afterCommandExecuteMsg = String.format("主机[%s],执行Shell命令:[%s] %s,运行时日志如下:%s", host,
                            afterCommand, msg, afterRes.getMsg());
                        Log.trace(afterCommandExecuteMsg);
                        logger.info(afterCommandExecuteMsg);
                    }
                }
            }
            copyResult.setSuccess(Boolean.TRUE);
        } catch (Exception e) {
            String msg = String.format("[%s]实例,执行失败,失败详细信息如下:[%s]", instance, ExceptionUtils.getStackTrace(e));
            copyResult.setSuccess(Boolean.FALSE);
            copyResult.setFailMsg(msg);
            throw new RuntimeException(msg, e);
        } finally {
            latch.countDown();
        }
    }
}
