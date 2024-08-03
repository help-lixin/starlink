package help.lixin.starlink.plugin.jenkins.service.impl;

import help.lixin.starlink.plugin.jenkins.mapper.JenkinsLogsMapper;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsLogsService;

/**
 * @Author: 伍岳林
 * @Date: 2024/3/14 上午10:51
 * @Description
 */
public class JenkinsLogsService implements IJenkinsLogsService {

    private JenkinsLogsMapper jenkinsLogsMapper;

    @Override
    public String queryLogInfo(Long buildId) {
        return jenkinsLogsMapper.queryByBuildId(buildId);
    }


    public JenkinsLogsService(JenkinsLogsMapper jenkinsLogsMapper) {
        this.jenkinsLogsMapper = jenkinsLogsMapper;
    }
}
