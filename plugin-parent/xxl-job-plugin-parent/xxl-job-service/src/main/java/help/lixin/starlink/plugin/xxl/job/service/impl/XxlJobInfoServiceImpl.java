package help.lixin.starlink.plugin.xxl.job.service.impl;

import help.lixin.starlink.core.plugin.InstanceService;
import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.xxl.job.api.ResponseWrapper;
import help.lixin.starlink.plugin.xxl.job.api.dto.JobInfoResponse;
import help.lixin.starlink.plugin.xxl.job.api.model.XxlJobInfo;
import help.lixin.starlink.plugin.xxl.job.api.service.impl.JobInfoService;
import help.lixin.starlink.plugin.xxl.job.dto.JobInfoQueryDTO;
import help.lixin.starlink.plugin.xxl.job.service.IXxlJobInfoService;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/6 10:55 上午
 * @Description
 */
public class XxlJobInfoServiceImpl extends InstanceService<JobInfoService> implements IXxlJobInfoService {


    @Override
    public PageResponse<XxlJobInfo> pageList(String instanceName, JobInfoQueryDTO jobInfoQueryDTO) {
        JobInfoService jobInfoService = getApi(instanceName);

        JobInfoResponse pageList = jobInfoService.query(jobInfoQueryDTO.getStart(),
                jobInfoQueryDTO.getLength(), jobInfoQueryDTO.getJobGroup(),
                jobInfoQueryDTO.getTriggerStatus(), jobInfoQueryDTO.getJobDesc(),
                jobInfoQueryDTO.getExecutorHandler(), jobInfoQueryDTO.getAuthor());

        PageResponse<XxlJobInfo> result = new PageResponse();
        result.setTotal(pageList.getRecordsTotal());
        result.setRecords(pageList.getData());
        return result;
    }

    @Override
    public String create(String instanceName, XxlJobInfo jobInfo) {
        ResponseWrapper<String> result = getApi(instanceName).add(jobInfo);
        return result.getContent();
    }

    @Override
    public String update(String instanceName, XxlJobInfo jobInfo) {
        ResponseWrapper<String> result = getApi(instanceName).update(jobInfo);
        return result.getContent();
    }

    @Override
    public String remove(String instanceName, int id) {
        ResponseWrapper<String> result = getApi(instanceName).remove(id);
        return result.getContent();
    }

    @Override
    public String pause(String instanceName, int id) {
        ResponseWrapper<String> result = getApi(instanceName).pause(id);
        return result.getContent();
    }

    @Override
    public String start(String instanceName, int id) {
        ResponseWrapper<String> result = getApi(instanceName).start(id);
        return result.getContent();
    }

    @Override
    public String triggerJob(String instanceName, int id, String executorParam, String addressList) {
        ResponseWrapper<String> result = getApi(instanceName).triggerJob(id,executorParam,addressList);
        return result.getContent();
    }

    @Override
    public List<String> nextTriggerTime(String instanceName, String scheduleType, String scheduleConf) {
        ResponseWrapper<List<String>> resultList = getApi(instanceName).nextTriggerTime(scheduleType, scheduleConf);
        return resultList.getContent();
    }

    public XxlJobInfoServiceImpl(PluginNamedContextFactory pluginNamedContextFactory) {
        this.pluginNamedContextFactory = pluginNamedContextFactory;
    }
}
