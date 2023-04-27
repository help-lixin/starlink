package help.lixin.jenkins.action;

import com.cdancy.jenkins.rest.domain.common.IntegerResponse;
import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.cdancy.jenkins.rest.domain.job.Artifact;
import com.cdancy.jenkins.rest.domain.job.BuildInfo;
import com.cdancy.jenkins.rest.domain.job.JobInfo;
import com.cdancy.jenkins.rest.domain.job.ProgressiveText;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.artifact.ArtifactInfo;
import help.lixin.core.constants.Constant;
import help.lixin.core.exception.jenkins.JenkinsTemplateNotFoundException;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.jenkins.action.entity.JenkinsActionParams;
import help.lixin.jenkins.model.CreateJobContext;
import help.lixin.jenkins.model.TriggerBuildContext;
import help.lixin.jenkins.properties.JenkinsProperties;
import help.lixin.jenkins.service.IJobService;
import help.lixin.jenkins.service.JenkinsFaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class JenkinsAction implements Action {
    private Logger logger = LoggerFactory.getLogger(JenkinsAction.class);

    private static final String JOB_NAME = "JOB_NAME";
    private static final String _JENKINS = "_jenkins";

    private static final String FAILURE = "FAILURE";
    private static final String SUCCESS = "SUCCESS";

    public static final String JENKINS_ACTION = "jenkins";

    public JenkinsFaceService jenkinsFaceService;

    private AtomicInteger incr = new AtomicInteger(0);

    private ExecutorService executor = Executors.newFixedThreadPool(100, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("jenkins-" + incr.incrementAndGet());
            thread.setDaemon(true);
            return thread;
        }
    });

    public JenkinsAction(JenkinsFaceService jenkinsFaceService) {
        this.jenkinsFaceService = jenkinsFaceService;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        logger.info("开始运行插件:[{}]", this.getClass().getName());

        // 参数解析
        String stageParams = ctx.getStageParams();
        ObjectMapper mapper = new ObjectMapper();
        JenkinsActionParams actionParams = mapper.readValue(stageParams, JenkinsActionParams.class);

        // 上下文获取
        String projectName = (String) ctx.getVar("projectName");
        String branch = (String) ctx.getVar("branch");
        String url = (String) ctx.getVar("url");

        IJobService jobService = jenkinsFaceService.getJobService();
        // 在jenkins中的job名称是:项目名称__分支名称
        String jobName = String.format("%s__%s", projectName, branch);

        CompletableFuture<JobInfo> jobInfoCompletableFuture = CompletableFuture.supplyAsync(() -> { // 1. 获取模板
            Map<String, Object> tempContext = new HashMap<>(ctx.getVars());
            tempContext.put(JOB_NAME, jobName);
            tempContext.put(_JENKINS, actionParams);
            // 参数中定义的配置文件
            String template = null;
            try {
                template = jenkinsFaceService.getJenkinsTemplateLoadFaceService().loadAndProcess(actionParams, tempContext);
            } catch (JenkinsTemplateNotFoundException e) {
                throw new RuntimeException(e.getMessage());
            }
            return template;
        }, executor).thenApplyAsync(template -> { // 2. 创建job或者获取已经存在的job
            // 创建JobInfo或者从Jenkins中获取已经存在的:JobInfo
            JobInfo jobInfo = getOrCreateJobInfo(jobName, template);
            return jobInfo;
        }, executor).thenApplyAsync(jobInfo -> { // 3. 配置上下文
            int buildNumber = jobInfo.nextBuildNumber();
            ctx.getVars().put(Constant.BuildInfo.BUILD_NUMBER, buildNumber);
            logger.info("Jenkins准备构建,获取构建前的唯一ID:[%s]", Constant.BuildInfo.BUILD_NUMBER);
            return jobInfo;
        }).thenApplyAsync(jobInfo -> { // 4. 触发构建
            //  触发构建
            triggerBuild(jobName, branch, url);
            return jobInfo;
        }, executor);

        // 5. 循环获取构建信息(验证是否构建成功)
        CompletableFuture<BuildInfo> buildInfoCompletableFuture = jobInfoCompletableFuture.thenApplyAsync(jobInfo -> {
            // FAILURE / SUCCESS
            BuildInfo buildInfo = null;
            int i = 1;
            do {
                if (null == buildInfo) {
                    try {
                        // 后期从参数配置里拿取来这个休眠时间
                        TimeUnit.SECONDS.sleep(20);
                    } catch (InterruptedException e) {
                    }
                }
                logger.info("第[{}]次等待jenkins构建完成.", i);
                buildInfo = getBuildInfo(jobName, jobInfo.nextBuildNumber());
                logger.info("第[{}]次等待jenkins构建结束.", i);
                i++;
            } while (null == buildInfo || null == buildInfo.result());

            // 经过几轮测试,发现:result有值的时候,代表这次build是结束了的,至于是成功还是失败,要看result具体的值
            if ("FAILURE".equals(buildInfo.result())) {
                if (logger.isDebugEnabled()) {
                    String msg = String.format("构建失败,失败详细信息如下:[%s]", buildInfo);
                    logger.error(msg);
                    throw new RuntimeException(msg);
                }
            } else {
                String msg = String.format("构建成功");
                logger.info(msg);
            }
            return buildInfo;
        }, executor);

        // 6.1 构建成功后,才会去下载:成品
        CompletableFuture<BuildInfo> artifactDownloadFuture = buildInfoCompletableFuture.thenApplyAsync(buildInfo -> {
            JenkinsProperties jenkinsProperties = jenkinsFaceService.getJenkinsProperties();
            String localDiskStoreArtifactDir = jenkinsProperties.getArtifactPath();
            // 存储构建物
            List<Artifact> artifacts = buildInfo.artifacts();
            if (null != artifacts && artifacts.size() > 1) {
                String msg = String.format("打包完成后,成品库在多个.");
                throw new RuntimeException(msg);
            }

            try {
                jenkinsFaceService.getDownloadStrategyService()
                        //
                        .download(actionParams.getDownloadStrategy(),
                                //
                                localDiskStoreArtifactDir,
                                //
                                jobName,
                                //
                                (int) ctx.getVar(Constant.BuildInfo.BUILD_NUMBER),
                                //
                                artifacts.get(0),
                                //
                                new Consumer<String>() {
                                    @Override
                                    public void accept(String artifactDiskFullPath) {
                                        ArtifactInfo artifactInfo = new ArtifactInfo();
                                        artifactInfo.setArtifactFullName(artifactDiskFullPath);

                                        ctx.getVars().put(Constant.Artifact.ARTIFACT_DIR, artifactInfo.getArtifactDir());
                                        ctx.getVars().put(Constant.Artifact.ARTIFACT_NAME, artifactInfo.getArtifactFileName());
                                        ctx.getVars().put(Constant.Artifact.ARTIFACT_FULL_PATH, artifactInfo.getArtifactFullName());

                                        logger.info("制品库下载后的目录:[{}]", Constant.Artifact.ARTIFACT_DIR, ctx.getVars().get(Constant.Artifact.ARTIFACT_DIR));
                                        logger.info("制品库下载后的文件名:[{}]", Constant.Artifact.ARTIFACT_NAME, ctx.getVars().get(Constant.Artifact.ARTIFACT_NAME));
                                        logger.info("制品库下载后的绝对路径:[{}]", Constant.Artifact.ARTIFACT_FULL_PATH, ctx.getVars().get(Constant.Artifact.ARTIFACT_FULL_PATH));
                                    }
                                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return buildInfo;
        }, executor);

        // 6.1 注意:下载成品与获取日志是两个线程来着的,因为,只要有了BuildInfo信息后,就能知道结果了的,不必同步去操作,只是这样的话,Jenkins性能能否Hold得住
        CompletableFuture<ProgressiveText> fetchBuildLogFuture = buildInfoCompletableFuture.thenApplyAsync((buildInfo) -> {
            logger.info("开始拉取构建日志信息");
            int nextNumber = (int) ctx.getVar(Constant.BuildInfo.BUILD_NUMBER);
            ProgressiveText progressiveText = jobService.lookBuildLog(null, jobName, nextNumber, 0);
            if (null != progressiveText.text()) {
                // TOOD lixin
                // 保存日志内容到DB里.
            }
            logger.info("结束拉取构建日志信息");
            logger.info(progressiveText.text());
            return progressiveText;
        }, executor);

        // 组合线程
        CompletableFuture.allOf(jobInfoCompletableFuture,
                        //
                        buildInfoCompletableFuture,
                        //
                        artifactDownloadFuture,
                        //
                        fetchBuildLogFuture)
                //
                .get(30, TimeUnit.MINUTES);
        logger.debug("结束插件:[{}]运行", this.getClass().getName());
        return true;
    }

    protected IntegerResponse triggerBuild(String jobName, String branch, String url) {
        IJobService jobService = jenkinsFaceService.getJobService();
        TriggerBuildContext buildContext = TriggerBuildContext.newBuilder()
                //
                .jobName(jobName)
                //
                .properties("branch", branch)
                //
                .properties("url", url)
                //
                .build();
        logger.info("开始触发构建,jobName:[{}] url:[{}] branch:[{}]", jobName, url, branch);
        IntegerResponse response = jobService.triggerBuild(buildContext);
        logger.info("结束触发构建,jobName:[{}] url:[{}] branch:[{}]", jobName, url, branch);
        return response;
    }

    protected JobInfo getOrCreateJobInfo(String jobName, String template) throws RuntimeException {
        IJobService jobService = jenkinsFaceService.getJobService();
        // 1. 先lookup jobName exists
        JobInfo jobInfo = jobService.getJobInfo(null, jobName);
        if (null == jobInfo) { // jobName在jenkins里不存在
            CreateJobContext createJobContext = CreateJobContext.newBuilder()
                    //
                    .jobName(jobName)
                    //
                    .configXML(template)
                    //
                    .build();
            String msg = String.format("Jenkins开始创建Job:[%s],xml内容:[\n%s\n]", createJobContext.getJobName(), createJobContext.getConfigXML());
            logger.info(msg);

            RequestStatus status = jobService.createJob(createJobContext);
            if (status.errors().size() > 0) {
                msg = String.format("Jenkins创建Job:[%s]失败,异常信息如下:[\n%s\n]", createJobContext.getJobName(), status.errors());
                logger.error(msg);
                throw new RuntimeException(msg);
            } else {
                // 3.2 创建完job后,重新获取job信息
                jobInfo = jobService.getJobInfo(null, jobName);
                msg = String.format("Jenkins创建Job:[%s]结束", createJobContext.getJobName());
                logger.info(msg);
            }
        }
        return jobInfo;
    }


    protected BuildInfo getBuildInfo(String jobName, int buildNumber) {
        IJobService jobService = jenkinsFaceService.getJobService();
        return jobService.getBuildInfo(null, jobName, buildNumber);
    }

    @Override
    public String name() {
        return JENKINS_ACTION;
    }
}
