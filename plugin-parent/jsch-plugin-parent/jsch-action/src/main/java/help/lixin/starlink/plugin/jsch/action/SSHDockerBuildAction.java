package help.lixin.starlink.plugin.jsch.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import help.lixin.core.log.Log;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.jasypt.encryption.StringEncryptor;
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
import help.lixin.starlink.core.constants.Constant;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.jsch.action.domain.command.LoadBalance;
import help.lixin.starlink.plugin.jsch.action.domain.docker.DockerBuildArgItem;
import help.lixin.starlink.plugin.jsch.action.domain.docker.DockerBuildParams;
import help.lixin.starlink.plugin.jsch.action.dto.SSHExecuteResult;
import help.lixin.starlink.plugin.jsch.action.thread.SSHShellThread;
import help.lixin.starlink.plugin.jsch.api.properties.JschProperties;
import help.lixin.starlink.plugin.jsch.api.service.IShellExecuteTemplateService;

/**
 * # 1. 进入工作目录 cd #{REPOSITORY_WORKSPACE}
 * <p>
 * # 2. 登录 docker login registry.lixin.help -u xxxx -p xxxx
 * <p>
 * # 3. 构建镜像 docker build -f #{REPOSITORY_WORKSPACE}/Dockerfile --build-arg APP_VERSION=1.2 -t #{TARGET_IMAGE}
 * #{REPOSITORY_WORKSPACE}/target docker build -f Dockerfile --build-arg APP_VERSION=1.2 -t
 * registry.lixin.help/spring-web-demo/spring-web-demo:1.0.2 /tmp/spring-web-demo/target/ docker build -f Dockerfile
 * --build-arg APP_VERSION=1.2 -t #{TARGET_IMAGE} #{REPOSITORY_WORKSPACE}/target
 * <p>
 * # 4. 推送镜像 docker push registry.lixin.help/spring-web-demo/spring-web-demo:1.0.0
 */
public class SSHDockerBuildAction implements Action {
    private Logger logger = LoggerFactory.getLogger(SSHShellAction.class);

    public static final String ACTION_NAME = "docker-build";
    private AbstractServiceFactory jschServiceFactory;

    private IExpressionService expressionService;
    private IQuerySshHostService querySshHostService;

    private StringEncryptor stringEncryptor;

    public SSHDockerBuildAction(AbstractServiceFactory jschServiceFactory, //
        IExpressionService expressionService, //
        StringEncryptor stringEncryptor, //
        IQuerySshHostService querySshHostService) {
        this.jschServiceFactory = jschServiceFactory;
        this.stringEncryptor = stringEncryptor;
        this.expressionService = expressionService;
        this.querySshHostService = querySshHostService;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        logger.info("开始运行插件:[{}]", this.getClass().getName());
        // 参数解析
        String stageParams = ctx.getStageParams();
        ObjectMapper mapper = new ObjectMapper();
        // 忽略未知字段
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DockerBuildParams params = mapper.readValue(stageParams, DockerBuildParams.class);
        LoadBalance loadBalance = params.getLoadBalance();
        long executeMaxWaitTime = params.getExecuteMaxWaitTime();
        Set<String> labels = params.getLabels();
        if (!labels.isEmpty()) {
            // 调用Service获取,标签
            Set<String> instances = querySshHostService.queryByLabel(labels);
            CountDownLatch latch = new CountDownLatch(1);
            try {
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
                        run(instance, latch, ctx, params, sshExecuteResult);
                    } finally {
                        // 最终等待多少分钟
                        latch.await(executeMaxWaitTime, TimeUnit.MINUTES);
                    }
                    return sshExecuteResult.isSuccess();
                }
            } finally {
                // 最终等待多少分钟
                latch.await(params.getExecuteMaxWaitTime(), TimeUnit.SECONDS);
            }
        }
        logger.info("运行插件:[{}]结束", this.getClass().getName());
        return false;
    }

    @Override
    public String name() {
        return ACTION_NAME;
    }

    protected void run(final String instance, //
        final CountDownLatch latch, //
        final PipelineContext ctx, //
        final DockerBuildParams params, //
        final SSHExecuteResult sshExecuteResult) {
        JschProperties jschProperties = jschServiceFactory.getInstance(instance, //
            JschProperties.class);
        IShellExecuteTemplateService shellExecuteTemplateService = jschServiceFactory.getInstance(instance, //
            IShellExecuteTemplateService.class);
        Tuple tuple = shellScript(ctx, params);
        // 打印日志
        String traceMsg = String.format("准备执行Shell脚本:[%s]\n", tuple.getRight());
        Log.trace(traceMsg);

        SSHShellThread commandThread = new SSHShellThread( //
            PipelineContextHolder.get(), //
            jschProperties, //
            shellExecuteTemplateService, //
            instance, //
            tuple, //
            sshExecuteResult, //
            latch);
        String threadName = String.format("ssh-docker-build-[%s]", instance);
        commandThread.setName(threadName);
        commandThread.setDaemon(true);
        commandThread.start();
    }

    protected Tuple shellScript(PipelineContext ctx, DockerBuildParams params) {
        StringBuilder cdWorkDir = new StringBuilder();
        // cd /tmp/spring-web-demo
        if (null != params.getWorkDir()) {
            String cdWorkDirCommand = String.format(" cd %s\n", params.getWorkDir());
            cdWorkDir.append(cdWorkDirCommand);
        }

        // docker login registry.lixin.help -u xxxx -p xxxx
        // 明文
        StringBuilder dockerLoginPlaintext = new StringBuilder();
        // 密文
        StringBuilder dockerLoginCiphertext = new StringBuilder();
        Map<String, Object> vars = ctx.getVars();
        if (null != vars && !vars.isEmpty()) {
            if (vars.containsKey(Constant.ImageRepository.IMAGE_REPOSITORY)) {
                String repository = (String)vars.get(Constant.ImageRepository.IMAGE_REPOSITORY);
                String userName = (String)vars.get(Constant.ImageRepository.IMAGE_USERNAME);
                String password = (String)vars.get(Constant.ImageRepository.IMAGE_PASSWORD);
                // 密码是否加密,如果有加密,则需要进行解密
                String isEncryption = (String)vars.get(Constant.ImageRepository.IMAGE_PASSWORD_IS_ENCRYPTION);
                if (null != isEncryption && Constant.ImageRepository.YES.equals(isEncryption)) {
                    password = stringEncryptor.decrypt(password);
                }
                String dockerLoginCommand =
                    String.format(" docker login %s -u %s -p %s \n", repository, userName, password);
                dockerLoginPlaintext.append(dockerLoginCommand);

                // 对密码进行处理
                String ciphertextPassword = RandomStringUtils.random(null != password ? password.length() : 1, "*");
                String dockerLoginCiphertextCommand =
                    String.format(" docker login %s -u %s -p %s \n", repository, userName, ciphertextPassword);
                dockerLoginCiphertext.append(dockerLoginCiphertextCommand);
            }
        }

        StringBuilder dockerBuild = new StringBuilder();
        try {
            // docker build -f #{REPOSITORY_WORKSPACE}/Dockerfile --build-arg APP_VERSION=1.2 -t #{TARGET_IMAGE}
            // #{REPOSITORY_WORKSPACE}/target
            dockerBuild.append(" docker build ");

            // 构建Dockerfile指定
            if (null != params.getDockerFile()) {
                String dockerFile = String.format(" -f %s ", params.getDockerFile());
                dockerBuild.append(dockerFile);
            }

            // 构建参数指定
            if (null != params.getArgs()) {
                // --build-arg APP_VERSION=1.2
                List<DockerBuildArgItem> args = params.getArgs();
                for (DockerBuildArgItem arg : args) {
                    String dockerBuildArg = String.format(" --build-arg %s=%s ", arg.getName(), arg.getValue());
                    dockerBuild.append(dockerBuildArg);
                }
            }

            // 构建标签指定
            if (null != params.getTargetImage()) {
                dockerBuild.append(String.format(" -t %s ", params.getTargetImage()));
            }

            // 构建上下文指定
            if (null != params.getBuildContext()) {
                dockerBuild.append(params.getBuildContext());
            }
        } finally {
            dockerBuild.append("\n");
        }

        StringBuilder dockerPush = new StringBuilder();
        // 推送
        if (params.isPush()) {
            String dockerPushCommand = String.format(" docker push %s \n", params.getTargetImage());
            dockerPush.append(dockerPushCommand);
        }

        // 要执行的明文密码
        String plaintextCommand = new StringBuilder().append(cdWorkDir.toString()) //
            .append(dockerLoginPlaintext.toString()) //
            .append(dockerBuild.toString()) //
            .append(dockerPush.toString()) //
            .toString();

        // 要执行的密文密码
        String ciphertextCommand = new StringBuilder().append(cdWorkDir.toString()) //
            .append(dockerLoginCiphertext.toString()) //
            .append(dockerBuild.toString()) //
            .append(dockerPush.toString()) //
            .toString();

        Tuple tuple = new Tuple(plaintextCommand, ciphertextCommand);
        return tuple;
    }

    @Override
    public String meta() {
        String meta = null;
        String pluginMetaPath = "plugin/meta/ssh-docker-build.json";
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
}