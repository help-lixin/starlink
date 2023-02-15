package help.lixin.xxl.job.api.service;

import help.lixin.xxl.job.api.ResponseWrapper;
import help.lixin.xxl.job.api.dto.JobInfoResponse;
import help.lixin.xxl.job.api.model.XxlJobInfo;

import java.util.List;

/**
 * 任务管理
 */
public interface IJobInfoService {

    // /jobinfo/pageList
    JobInfoResponse query(int start, int length, int jobGroup, int triggerStatus, String jobDesc, String executorHandler, String author);


    // /jobinfo/add
    ResponseWrapper<String> add(XxlJobInfo jobInfo);

    // /jobinfo/update
    ResponseWrapper<String> update(XxlJobInfo jobInfo);

    // /jobinfo/remove
    ResponseWrapper<String> remove(int id);

    // /jobinfo/stop
    ResponseWrapper<String> pause(int id);

    // /jobinfo/start
    ResponseWrapper<String> start(int id);


    // /jobinfo/trigger
    ResponseWrapper<String> triggerJob(int id, String executorParam, String addressList);

    // /jobinfo/nextTriggerTime
    ResponseWrapper<List<String>> nextTriggerTime(String scheduleType, String scheduleConf);


}
