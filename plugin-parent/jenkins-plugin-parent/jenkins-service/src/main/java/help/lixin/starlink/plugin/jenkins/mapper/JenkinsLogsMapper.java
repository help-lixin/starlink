package help.lixin.starlink.plugin.jenkins.mapper;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsLogs;

public interface JenkinsLogsMapper {
    int insert(JenkinsLogs record);

    int insertSelective(JenkinsLogs record);

    String queryByBuildId(Long buildId);
}