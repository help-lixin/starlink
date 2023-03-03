package help.lixin.xxl.job.api.service;

import help.lixin.xxl.job.api.BasicTest;
import help.lixin.xxl.job.api.ResponseWrapper;
import help.lixin.xxl.job.api.dto.JobLogResponse;
import help.lixin.xxl.job.api.dto.LogResult;
import help.lixin.xxl.job.api.model.XxlJobInfo;
import help.lixin.xxl.job.api.service.impl.JobLogService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class JobLogServiceTest extends BasicTest {

    private IJobLogService jobLogService = null;

    @Before
    public void init() {
        jobLogService = new JobLogService(url);
    }

    @Test
    public void testGetJobsByGroup() {
        int jobGroup = 11;
        ResponseWrapper<List<XxlJobInfo>> jonInfos = jobLogService.getJobsByGroup(jobGroup);
        Assert.assertNotNull(jonInfos);
    }

    @Test
    public void testQuery() {
        int start = 0;
        int len = 10;
        int jobGroup = 11;
        int jobId = 1;
        // 1 : 成功
        // 2 : 失败
        // 3 : 进行中
        int logStatus = 2;
        String filterTime = "2023-02-01 00:00:00 - 2023-02-28 23:59:59";
        JobLogResponse response = jobLogService.query(start, len, jobGroup, jobId, logStatus, filterTime);
        Assert.assertNotNull(response);
    }

    @Test
    public void testLogDetailPage() {
        int id = 5;
        String result = null;//jobLogService.logDetailPage(5);
        Assert.assertNotNull(result);
    }

    @Test
    public void testLogDetail() {
        // 好像容顺化之后,这个ip的地址是会变的哈.
        String executorAddress = "http://172.17.5.104:39828/";
        long triggerTime = 1676110430000l;
        long logId = 5l;
        int fromLineNum = 100;
        ResponseWrapper<LogResult> respons = jobLogService.logDetailCat(executorAddress, triggerTime, logId, fromLineNum);
        Assert.assertNotNull(respons);
    }
}
