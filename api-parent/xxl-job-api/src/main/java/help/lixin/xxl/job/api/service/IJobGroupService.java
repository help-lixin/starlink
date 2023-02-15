package help.lixin.xxl.job.api.service;

import help.lixin.xxl.job.api.ResponseWrapper;
import help.lixin.xxl.job.api.dto.JobGroupResponse;
import help.lixin.xxl.job.api.model.XxlJobGroup;

/**
 * 执行器管理
 */
public interface IJobGroupService {

    // /jobgroup/pageList
    JobGroupResponse query(int start, int length, String appname, String title);

    // /jobgroup/save
    ResponseWrapper<String> save(XxlJobGroup xxlJobGroup);

    //  /jobgroup/update
    ResponseWrapper<String> update(XxlJobGroup xxlJobGroup);

    // /jobgroup/remove
    ResponseWrapper<String> remove(int id);

    // /jobgroup/loadById
    ResponseWrapper<XxlJobGroup> loadById(int id);
}
