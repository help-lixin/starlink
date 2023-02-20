package help.lixin.jenkins.action;

import com.cdancy.jenkins.rest.domain.common.IntegerResponse;
import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.cdancy.jenkins.rest.domain.job.Artifact;
import com.cdancy.jenkins.rest.domain.job.BuildInfo;
import com.cdancy.jenkins.rest.domain.job.JobInfo;
import com.cdancy.jenkins.rest.domain.job.ProgressiveText;
import com.cdancy.jenkins.rest.domain.queue.QueueItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.jenkins.model.CreateJobContext;
import help.lixin.jenkins.model.TriggerBuildContext;
import help.lixin.jenkins.service.IJobService;
import help.lixin.jenkins.service.IQueueService;
import help.lixin.jenkins.service.JenkinsFaceService;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JenkinsAction implements Action {
    public static final String JENKINS_ACTION = "jenkins";

    public JenkinsFaceService jenkinsFaceService;

    public JenkinsAction(JenkinsFaceService jenkinsFaceService) {
        this.jenkinsFaceService = jenkinsFaceService;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        // 1. 参数解析
        String stageParams = ctx.getStageParams();
        ObjectMapper mapper = new ObjectMapper();
        JenkinsActionProperties actionProperties = mapper.readValue(stageParams, JenkinsActionProperties.class);

        // 2. 上下文获取
        String projectName = (String) ctx.getVar("projectName");
        String branch = (String) ctx.getVar("branch");
        String url = (String) ctx.getVar("url");

        IJobService jobService = jenkinsFaceService.getJobService();

        // 3. 调用jenkins去执行
        // 在jenkins中的job名称是:项目名称_分支名称
        String jobName = String.format("%s_%s", projectName, branch);
        String templateFile = actionProperties.getTemplateFile();
        JobInfo jobInfo = getOrCreateJobInfo(jobName, templateFile);
        // 3.4 触发构建
        IntegerResponse response = triggerBuild(jobName, branch, url);
        if (response.errors().size() > 0) {
            //throw new Exception()
        } else {
            // 阻塞直到队列里查不出这条信息,代表quque里的数据已经构建完毕.
            // jenkins gloabl queue
            Integer queueId = response.value();
            QueueItem queue = null;
            Boolean run = Boolean.TRUE;
            do {
                queue = getQueueItem(queueId);
                System.out.println(queue);
                TimeUnit.SECONDS.sleep(1);
            } while (run);

            // 因为,前面已经阻塞直到队列成功,所以,在这里是能直接拿到内容了的.
            int buildNumber = jobInfo.nextBuildNumber();
            BuildInfo buildInfo = getBuildInfo(jobName, buildNumber);

            List<Artifact> artifacts = buildInfo.artifacts();
            ctx.addVar("__artifacts", artifacts);
            // 获得构建后的日志信息
            ProgressiveText progressiveText = jobService.lookBuildLog(null, jobName, buildNumber, 0);
            if (null != progressiveText.text()) {
                ctx.addVar("_build_log", progressiveText.text());
            }
        }
        // 4. 添加变量
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
        IntegerResponse response = jobService.triggerBuild(buildContext);
        return response;
    }

    protected JobInfo getOrCreateJobInfo(String jobName, String templateFilePath) throws Exception {
        IJobService jobService = jenkinsFaceService.getJobService();

        // 1. 先lookup jobName是否存在
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


    protected QueueItem getQueueItem(Integer queueId) {
        IQueueService queueService = jenkinsFaceService.getQueueService();
        QueueItem queueItem = queueService.queueItem(queueId);
        return queueItem;
    }

    protected BuildInfo getBuildInfo(String jobName, int buildNumber) {
        IJobService jobService = jenkinsFaceService.getJobService();
        return jobService.getBuildInfo(null, jobName, buildNumber);
    }

    @Override
    public boolean after(PipelineContext ctx) throws Exception {
        return true;
    }

    @Override
    public String name() {
        return JENKINS_ACTION;
    }
}
