package jenkins.api;

import com.cdancy.jenkins.rest.domain.common.IntegerResponse;
import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.cdancy.jenkins.rest.domain.job.*;
import com.cdancy.jenkins.rest.domain.queue.QueueItem;
import help.lixin.jenkins.service.IJobService;
import help.lixin.jenkins.service.IQueueService;
import help.lixin.jenkins.service.impl.JobService;
import help.lixin.jenkins.service.impl.QueueService;
import help.lixin.jenkins.model.CreateJobContext;
import help.lixin.jenkins.model.TriggerBuildContext;
import jenkins.BasicTest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.List;

public class JobServiceTest extends BasicTest {

    protected static String JOB_NAME = "spring-web-demo";

    private IJobService jobService;
    private IQueueService queueService;

    @Before
    public void init() {
        jobService = new JobService(client);
        queueService = new QueueService(client);
    }


    @Test
    public void testGetConfigXml() {
        String configXml = jobService.getConfigXml(null, JOB_NAME);
        Assert.assertNotNull(configXml);
    }

    @Test
    public void testQuery() {
        JobList query = jobService.query(null);
        Assert.assertNotNull(query);
        Assert.assertEquals(1, query.jobs().size());
    }

    @Test
    public void testCreateJob() {
        String configXml = jobService.getConfigXml(null, JOB_NAME);
//        String configXml = jobService.getConfigXml(null, "test");
        RequestStatus status = jobService.createJob(CreateJobContext.newBuilder()
                //
                .configXML(configXml)
                //
                .jobName("tmp_" + JOB_NAME)
                //
                .build());
        Assert.assertNotNull(status);
        Assert.assertEquals(0, status.errors().size());
    }

    @Test
    public void testTriggerBuild() {
        // 1. 先获取job信息(nextBuildNumber)
        JobInfo jobInfo = jobService.getJobInfo(null, "tmp_" + JOB_NAME);
        Assert.assertNotNull(jobInfo);
        // 如果触发任务的话,下一次的构建任务id
        int buildNumber = jobInfo.nextBuildNumber();

        // 2. 触发任务
        TriggerBuildContext ctx = TriggerBuildContext
                //
                .newBuilder().jobName("tmp_" + JOB_NAME).build();
        IntegerResponse response = jobService.triggerBuild(ctx);
        Assert.assertNotNull(response);
        Assert.assertEquals(0, response.errors().size());
        Assert.assertNotNull(response.value());

        // 3. 查一下global的队列id信息
        // queueItemId 是一直递增的
        // buildNumber是Job内部递增的,要从:JobInfo里取:nextBuildNumber
        QueueItem queueItem = queueService.queueItem(response.value());
        Assert.assertNotNull(queueItem);

        // 4. 查一下构建信息(如果为null,代表没有完成)
        BuildInfo buildInfo = jobService.getBuildInfo(null, "tmp_" + JOB_NAME, buildNumber);
    }

    @Test
    public void testBuildInfo() {
        // 4. 查一下构建信息
        BuildInfo buildInfo = jobService.getBuildInfo(null, "tmp_" + JOB_NAME, 4);
        Assert.assertNotNull(buildInfo);
        Assert.assertNotNull(buildInfo.artifacts());
    }

    @Test
    public void testArtifactDownload() {
        String jobName = "tmp_" + JOB_NAME;
        int buildNumber = 4;
        // 4. 查一下构建信息
        BuildInfo buildInfo = jobService.getBuildInfo(null, jobName, buildNumber);
        Assert.assertNotNull(buildInfo);
        List<Artifact> artifacts = buildInfo.artifacts();
        for (Artifact artifact : buildInfo.artifacts()) {
            String fileName = artifact.fileName();
            String relativePath = artifact.relativePath();
            InputStream inputStream = jobService.artifact(null, jobName, buildNumber, relativePath);
            try {
                if (inputStream instanceof FilterInputStream) {
                    FilterInputStream filterInputStream = (FilterInputStream) inputStream;
                    
                    byte[] bytes = filterInputStream.readAllBytes();
                    IOUtils.write(bytes, new FileOutputStream(new File("/tmp/" + fileName)));
                }
            } catch (Exception ignore) {
                ignore.printStackTrace();
            }
        }
    }

    @Test
    public void testProgressiveText() {
        String jobName = "tmp_" + JOB_NAME;
        int buildNumber = 4;
        ProgressiveText progressiveText = jobService.lookBuildLog(null, jobName, buildNumber, 0);
        Assert.assertNotNull(progressiveText);
        try {
            String path = String.format("%s%s_%s%s", "/tmp/", jobName, buildNumber, ".txt");
            IOUtils.write(progressiveText.text(), new FileOutputStream(new File(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
