package help.lixin.starlink.plugin.xxl.job.api.service;

import help.lixin.starlink.plugin.xxl.job.api.ResponseWrapper;
import help.lixin.starlink.plugin.xxl.job.api.dto.JobLogResponse;
import help.lixin.starlink.plugin.xxl.job.api.dto.LogResult;
import help.lixin.starlink.plugin.xxl.job.api.model.XxlJobInfo;

import java.util.List;

public interface IJobLogService {

    // /joblog/getJobsByGroup
    ResponseWrapper<List<XxlJobInfo>> getJobsByGroup(int jobGroup);

    // /joblog/query
    JobLogResponse query(int start, int length, int jobGroup, int jobId, int logStatus, String filterTime);

    /**
     * 这里实际是调用远程机器,查看日志来着的.
     *
     * @param executorAddress
     * @param triggerTime
     * @param logId
     * @param fromLineNum
     * @return
     */
    // /joblog/logDetailCat
    ResponseWrapper<LogResult> logDetailCat(String executorAddress, long triggerTime, long logId, int fromLineNum);


    // /joblog/logKill
    ResponseWrapper<String> logKill(int id);


    // /joblog/clearLog
    ResponseWrapper<String> clearLog(int jobGroup, int jobId, int type);


}
