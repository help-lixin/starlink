package help.lixin.starlink.plugin.xxl.job.service;

import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.xxl.job.api.model.XxlJobGroup;

/**
 * 执行器管理
 */
public interface IXxlJobGroupService {

    // /jobgroup/pageList
    PageResponse<XxlJobGroup> query(String instanceName, int start, int length, String appname, String title);

    // /jobgroup/save
    String save(String instanceName, XxlJobGroup xxlJobGroup);

    // /jobgroup/update
    String update(String instanceName, XxlJobGroup xxlJobGroup);

    // /jobgroup/remove
    String remove(String instanceName, int id);

    // /jobgroup/loadById
    XxlJobGroup loadById(String instanceName, int id);
}
