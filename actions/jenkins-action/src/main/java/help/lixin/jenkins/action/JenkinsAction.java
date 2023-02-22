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
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.jenkins.model.CreateJobContext;
import help.lixin.jenkins.model.TriggerBuildContext;
import help.lixin.jenkins.properties.JenkinsProperties;
import help.lixin.jenkins.service.IJobService;
import help.lixin.jenkins.service.JenkinsFaceService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class JenkinsAction implements Action {

    private static final String FAILURE = "FAILURE";
    private static final String SUCCESS = "SUCCESS";

    public static final String JENKINS_ACTION = "jenkins";

    public JenkinsFaceService jenkinsFaceService;

    private AtomicInteger incr = new AtomicInteger(0);

    private ExecutorService executor = Executors.newFixedThreadPool(100, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("jenkins-build-" + incr.incrementAndGet() + " thread");
            thread.setDaemon(true);
            return thread;
        }
    });

    public JenkinsAction(JenkinsFaceService jenkinsFaceService) {
        this.jenkinsFaceService = jenkinsFaceService;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        // 1. 参数解析
        String stageParams = ctx.getStageParams();
        ObjectMapper mapper = new ObjectMapper();
        JenkinsActionParams actionProperties = mapper.readValue(stageParams, JenkinsActionParams.class);

        // 2. 上下文获取
        String projectName = (String) ctx.getVar("projectName");
        String branch = (String) ctx.getVar("branch");
        String url = (String) ctx.getVar("url");

        IJobService jobService = jenkinsFaceService.getJobService();
        // 3. 调用jenkins
        // 在jenkins中的job名称是:项目名称-分支名称
        String jobName = String.format("%s-%s", projectName, branch);
        // 参数中定义的配置文件
        String templateFile = actionProperties.getTemplateFile();
        // 创建JobInfo或者从Jenkins中获取已经存在的:JobInfo
        JobInfo jobInfo = getOrCreateJobInfo(jobName, templateFile);
        //  触发构建
        IntegerResponse response = triggerBuild(jobName, branch, url);
        int buildNumber = jobInfo.nextBuildNumber();
        Future<BuildInfo> buildInfoFuture = executor.submit(new Callable<BuildInfo>() {
            @Override
            public BuildInfo call() throws Exception {
                // FAILURE / SUCCESS
                BuildInfo buildInfo = null;
                do {
                    buildInfo = getBuildInfo(jobName, buildNumber);
                    if (null == buildInfo) {
                        TimeUnit.SECONDS.sleep(1);
                    }
                } while (null == buildInfo || null == buildInfo.result());
                // 经过几轮测试,发现:result有值的时候,代表这次build是结束了的,至于是成功还是失败,要看result具体的值
                return buildInfo;
            }
        });

        // 最多等10分钟(这个应该留在参数里,让用户自己提交过来)
        BuildInfo buildInfo = buildInfoFuture.get(10, TimeUnit.MINUTES);
        if ("FAILURE".equals(buildInfo.result())) {
            // throw new Exception
        }

        JenkinsProperties jenkinsProperties = jenkinsFaceService.getJenkinsProperties();
        String artifactPath = jenkinsProperties.getArtifactPath();

        // 这一块扔出去,让一个线程去重试下载
        // 存储构建物
        List<Artifact> artifacts = buildInfo.artifacts();
        if (null != artifacts && artifacts.size() > 1) {
            // 控制产生的构建物应该只能是一个来着的.
            //throw Exception
        }

        // 在这里也只一个成品出来.
        Artifact artifact = artifacts.get(0);
        String artifactFullPath = String.format("%s/%s/%s/%s", artifactPath, jobName, buildNumber, artifact.fileName());
        // 先强制创建一下父目录
        FileUtils.forceMkdirParent(new File(artifactFullPath));
        // 通过jenkins api远程下载文件
        InputStream inputStream = jobService.artifact(null, jobName, buildNumber, artifact.relativePath());
        // 指定输出的位置
        IOUtils.copy(inputStream, new FileOutputStream(artifactFullPath));

        ArtifactInfo artifactInfo = new ArtifactInfo();
        artifactInfo.setArtifactFullName(artifactFullPath);
        ctx.getVars().put(Constant.Artifact.ARTIFACT_DIR, artifactInfo.getArtifactDir());
        ctx.getVars().put(Constant.Artifact.ARTIFACT_NAME, artifactInfo.getArtifactFileName());
        ctx.getVars().put(Constant.Artifact.ARTIFACT_FULL_PATH, artifactInfo.getArtifactFullName());


        // DockerFile
        String dockerFilePath = getDockerFilePath(actionProperties);
        if (null != dockerFilePath) {
            ctx.getVars().put(Constant.Docker.DOCKER_FILE, dockerFilePath);
        }

        // 这一块扔出去,让另一个线程去执行.
        // 获得构建后的日志信息
        ProgressiveText progressiveText = jobService.lookBuildLog(null, jobName, buildNumber, 0);
        if (null != progressiveText.text()) {
            ctx.addVar("__build_log", progressiveText.text());
        }
        return true;
    }

    protected String getDockerFilePath(JenkinsActionParams params) {
        String dockerFile = params.getDockerFile();
        if (null == dockerFile) {
            dockerFile = jenkinsFaceService.getJenkinsProperties().getDockerFile();
        }
        return dockerFile;
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
        IntegerResponse response = jobService.triggerBuild(buildContext);
        return response;
    }

    protected JobInfo getOrCreateJobInfo(String jobName, String templateFilePath) throws Exception {
        IJobService jobService = jenkinsFaceService.getJobService();

        // 1. 先lookup jobName exists
        JobInfo jobInfo = jobService.getJobInfo(null, jobName);
        if (null == jobInfo) { // jobName在jenkins里不存在
            File templateFile = new File(templateFilePath);
            if (!templateFile.exists()) {
                // throw new Exception
            } else {
                String configXML = FileUtils.readFileToString(templateFile, "UTF-8");
                CreateJobContext createJobContext = CreateJobContext.newBuilder().jobName(jobName).configXML(configXML).build();
                RequestStatus status = jobService.createJob(createJobContext);
                if (status.errors().size() > 0) {
                    // 返回错误处理
                    // throw new Exception
                } else {
                    // 3.2 创建完job后,重新获取job信息
                    jobInfo = jobService.getJobInfo(null, jobName);
                }
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
