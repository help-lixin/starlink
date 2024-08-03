package help.lixin.starlink.plugin.xxl.job.service.impl;

import help.lixin.starlink.core.plugin.InstanceService;
import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.exception.ServiceException;
import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.xxl.job.api.ResponseWrapper;
import help.lixin.starlink.plugin.xxl.job.api.dto.JobGroupResponse;
import help.lixin.starlink.plugin.xxl.job.api.model.XxlJobGroup;
import help.lixin.starlink.plugin.xxl.job.api.service.impl.JobGroupService;
import help.lixin.starlink.plugin.xxl.job.service.IXxlJobGroupService;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/6 10:55 上午
 * @Description
 */
public class XxlJobGroupServiceImpl extends InstanceService<JobGroupService> implements IXxlJobGroupService {
    @Override
    public PageResponse<XxlJobGroup> query(String instanceName, int start, int length, String appname, String title) {
        JobGroupResponse pageList = getApi(instanceName).query(start, length, appname, title);
        if (pageList == null){
            throw new ServiceException("暂无数据");
        }
        PageResponse<XxlJobGroup> pageResponse = new PageResponse<>();
        pageResponse.setTotal(pageList.getRecordsTotal());
        pageResponse.setRecords(pageList.getData());
        return pageResponse;
    }

    @Override
    public String save(String instanceName, XxlJobGroup xxlJobGroup) {
        ResponseWrapper<String> result = getApi(instanceName).save(xxlJobGroup);
        return result.getContent();
    }

    @Override
    public String update(String instanceName, XxlJobGroup xxlJobGroup) {
        ResponseWrapper<String> result = getApi(instanceName).update(xxlJobGroup);
        return result.getContent();
    }

    @Override
    public String remove(String instanceName, int id) {
        ResponseWrapper<String> result = getApi(instanceName).remove(id);
        return result.getContent();
    }

    @Override
    public XxlJobGroup loadById(String instanceName, int id) {
        ResponseWrapper<XxlJobGroup> result = getApi(instanceName).loadById(id);
        return result.getContent();
    }

    public XxlJobGroupServiceImpl(PluginNamedContextFactory pluginNamedContextFactory) {
        this.pluginNamedContextFactory = pluginNamedContextFactory;
    }
}
