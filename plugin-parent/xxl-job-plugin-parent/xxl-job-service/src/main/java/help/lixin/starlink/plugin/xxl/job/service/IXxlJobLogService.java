package help.lixin.starlink.plugin.xxl.job.service;

import java.util.List;

import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.xxl.job.api.dto.LogResult;
import help.lixin.starlink.plugin.xxl.job.api.model.XxlJobInfo;
import help.lixin.starlink.plugin.xxl.job.api.model.XxlJobLog;
import help.lixin.starlink.plugin.xxl.job.dto.log.JobLogClearDTO;
import help.lixin.starlink.plugin.xxl.job.dto.log.JobLogDetailDTO;
import help.lixin.starlink.plugin.xxl.job.dto.log.JobLogPageListDTO;

public interface IXxlJobLogService {

    // /joblog/getJobsByGroup
    List<XxlJobInfo> queryById(String instanceName, Integer jobGroupId);

    // /joblog/query
    PageResponse<XxlJobLog> pageList(JobLogPageListDTO jobLogPageListDTO);

    // /joblog/logDetailCat
    LogResult logDetailCat(JobLogDetailDTO jobLogDetailDTO);

    // /joblog/logKill
    String logKill(String instanceName, Integer id);

    // /joblog/clearLog
    String clearLog(JobLogClearDTO jobLogClearDTO);

}
