package help.lixin.starlink.plugin.xxl.job.service.impl;

import help.lixin.starlink.core.plugin.InstanceService;
import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.xxl.job.api.ResponseWrapper;
import help.lixin.starlink.plugin.xxl.job.api.dto.JobLogResponse;
import help.lixin.starlink.plugin.xxl.job.api.dto.LogResult;
import help.lixin.starlink.plugin.xxl.job.api.model.XxlJobInfo;
import help.lixin.starlink.plugin.xxl.job.api.model.XxlJobLog;
import help.lixin.starlink.plugin.xxl.job.api.service.impl.JobLogService;
import help.lixin.starlink.plugin.xxl.job.dto.log.JobLogClearDTO;
import help.lixin.starlink.plugin.xxl.job.dto.log.JobLogDetailDTO;
import help.lixin.starlink.plugin.xxl.job.dto.log.JobLogPageListDTO;
import help.lixin.starlink.plugin.xxl.job.service.IXxlJobLogService;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/6 11:18 上午
 * @Description
 */
public class XxlJobLogServiceImpl extends InstanceService<JobLogService> implements IXxlJobLogService {
    @Override
    public List<XxlJobInfo> queryById(String instanceName, Integer jobGroupId) {
        ResponseWrapper<List<XxlJobInfo>> result = getApi(instanceName).getJobsByGroup(jobGroupId);
        return result.getContent();
    }

    @Override
    public PageResponse<XxlJobLog> pageList(JobLogPageListDTO jobLogPageListDTO) {
        JobLogResponse result = getApi(jobLogPageListDTO.getInstanceName()).query(
                jobLogPageListDTO.getPageRequest().getPageNum(),
                jobLogPageListDTO.getPageRequest().getPageSize(),
                jobLogPageListDTO.getJobGroup(),
                jobLogPageListDTO.getJobId(),
                jobLogPageListDTO.getLogStatus(),
                jobLogPageListDTO.getFilterTime());
        PageResponse<XxlJobLog> pageResponse = new PageResponse();
        pageResponse.setTotal(result.getRecordsTotal());
        pageResponse.setRecords(result.getData());
        return pageResponse;
    }

    @Override
    public LogResult logDetailCat(JobLogDetailDTO jobLogDetailDTO) {
        ResponseWrapper<LogResult> result = getApi(jobLogDetailDTO.getInstanceName()).logDetailCat(
                jobLogDetailDTO.getExecutorAddress(),
                jobLogDetailDTO.getTriggerTime(),
                jobLogDetailDTO.getLogId(),
                jobLogDetailDTO.getFromLineNum());
        return result.getContent();
    }

    @Override
    public String logKill(String instanceName, Integer id) {
        ResponseWrapper<String> result = getApi(instanceName).logKill(id);
        return result.getContent();
    }

    @Override
    public String clearLog(JobLogClearDTO jobLogClearDTO) {
        ResponseWrapper<String> result = getApi(jobLogClearDTO.getInstanceName()).clearLog(
                jobLogClearDTO.getJobGroupId(),
                jobLogClearDTO.getJobId(),
                jobLogClearDTO.getType());
        return result.getContent();
    }


    public XxlJobLogServiceImpl(PluginNamedContextFactory pluginNamedContextFactory) {
        this.pluginNamedContextFactory = pluginNamedContextFactory;
    }
}
