package help.lixin.xxl.job.api.service;

import help.lixin.xxl.job.api.BasicTest;
import help.lixin.xxl.job.api.ResponseWrapper;
import help.lixin.xxl.job.api.dto.JobInfoResponse;
import help.lixin.xxl.job.api.model.XxlJobInfo;
import help.lixin.xxl.job.api.service.impl.JobInfoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JobInfoServiceTest extends BasicTest {

    private IJobInfoService jobInfoService;

    @Before
    public void init() {
        jobInfoService = new JobInfoService(url);
    }

    @Test
    public void testQuery() {
        int jobGroup = 11;
        JobInfoResponse query = jobInfoService.query(0, 0, jobGroup, 0, null, null, null);
        Assert.assertNotNull(query);
    }

    @Test
    public void testAdd() {
        XxlJobInfo info = new XxlJobInfo();
        info.setJobGroup(11);
        info.setJobDesc("test");
        info.setAuthor("lixin");
        info.setExecutorHandler("test");
        // cron
        info.setScheduleConf("0/30 * * * * ?");
        // 调度状态：0-停止，1-运行
        info.setTriggerStatus(0);
        ResponseWrapper<String> res = jobInfoService.add(info);
        Assert.assertNotNull(res);
    }

    @Test
    public void testUpdate() {
        XxlJobInfo info = new XxlJobInfo();
        info.setId(3);
        info.setJobGroup(11);
        info.setJobDesc("test_");
        info.setAuthor("lixin_");
        info.setExecutorHandler("test_");
        // cron
        info.setScheduleConf("0/50 * * * * ?");
        // 调度状态：0-停止，1-运行
        info.setTriggerStatus(0);
        ResponseWrapper<String> res = jobInfoService.update(info);
        Assert.assertNotNull(res);
    }

    @Test
    public void testStart() {
        ResponseWrapper<String> res = jobInfoService.start(3);
        Assert.assertNotNull(res);
    }

    @Test
    public void testStop() {
        ResponseWrapper<String> res = jobInfoService.pause(3);
        Assert.assertNotNull(res);
    }

    @Test
    public void testTriggerJob() {
        ResponseWrapper<String> res = jobInfoService.triggerJob(3, "", "127.0.0.1:9999");
        Assert.assertNotNull(res);
    }

    @Test
    public void testRemove() {
        ResponseWrapper<String> res = jobInfoService.remove(3);
        Assert.assertNotNull(res);
    }

}
