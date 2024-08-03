package help.lixin.starlink.plugin.xxl.job.service;

import java.util.List;

import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.xxl.job.api.model.XxlJobInfo;
import help.lixin.starlink.plugin.xxl.job.dto.JobInfoQueryDTO;

/**
 * 任务管理
 */
public interface IXxlJobInfoService {

    // /jobinfo/pageList
    PageResponse<XxlJobInfo> pageList(String instanceName, JobInfoQueryDTO jobInfoQueryDTO);

    // /jobinfo/add
    String create(String instanceName, XxlJobInfo jobInfo);

    // /jobinfo/update
    String update(String instanceName, XxlJobInfo jobInfo);

    // /jobinfo/remove
    String remove(String instanceName, int id);

    // /jobinfo/stop
    String pause(String instanceName, int id);

    // /jobinfo/start
    String start(String instanceName, int id);

    // /jobinfo/trigger
    String triggerJob(String instanceName, int id, String executorParam, String addressList);

    // /jobinfo/nextTriggerTime
    List<String> nextTriggerTime(String instanceName, String scheduleType, String scheduleConf);

}
