package help.lixin.xxl.job.api.service;

import help.lixin.xxl.job.api.BasicTest;
import help.lixin.xxl.job.api.ResponseWrapper;
import help.lixin.xxl.job.api.dto.JobGroupResponse;
import help.lixin.xxl.job.api.model.XxlJobGroup;
import help.lixin.xxl.job.api.service.impl.JobGroupService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JobGroupServiceTest extends BasicTest {

    private IJobGroupService jobGroupService;

    @Before
    public void init() {
        jobGroupService = new JobGroupService(url);
    }

    @Test
    public void testQuery() {
        JobGroupResponse res = jobGroupService.query(0, 0, null, null);
        Assert.assertNotNull(res);
    }

    @Test
    public void testSave() {
        XxlJobGroup group = new XxlJobGroup();
        group.setAppname("test");
        group.setTitle("test");
        // 0 :自动  1:手动
        group.setAddressType(0);
        ResponseWrapper<String> res = jobGroupService.save(group);
        Assert.assertNotNull(res);
    }

    @Test
    public void testUpdate() {
        XxlJobGroup group = new XxlJobGroup();
        // 更新时,要找到具体的id来着
        group.setId(12);
        group.setAppname("test_");
        group.setTitle("test_");
        // 0 :自动  1:手动
        group.setAddressType(0);
        ResponseWrapper<String> res = jobGroupService.update(group);
        Assert.assertNotNull(res);
    }

    @Test
    public void testRemove() {
        ResponseWrapper<String> res = jobGroupService.remove(12);
        Assert.assertNotNull(res);
    }

    @Test
    public void testLoadById() {
        ResponseWrapper<XxlJobGroup> jobGroupResponseWrapper = jobGroupService.loadById(12);
        Assert.assertNotNull(jobGroupResponseWrapper);
    }
}
