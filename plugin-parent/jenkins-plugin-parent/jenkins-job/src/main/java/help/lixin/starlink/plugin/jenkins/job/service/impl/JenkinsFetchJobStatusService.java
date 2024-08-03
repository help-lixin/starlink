package help.lixin.starlink.plugin.jenkins.job.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.cdancy.jenkins.rest.domain.job.BuildInfo;
import com.cdancy.jenkins.rest.domain.job.ProgressiveText;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.jenkins.api.service.impl.JobService;
import help.lixin.starlink.plugin.jenkins.constants.BuildConstants;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsLogs;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsBuildInfoDTO;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsBuildingJobInfo;
import help.lixin.starlink.plugin.jenkins.job.service.IJenkinsFetchJobStatusService;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsJobMapper;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsLogsMapper;
import help.lixin.starlink.plugin.jenkins.service.impl.JenkinsBuildInfoService;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/22 下午6:03
 * @Description
 */
public class JenkinsFetchJobStatusService implements IJenkinsFetchJobStatusService {

    private Logger logger = LoggerFactory.getLogger(JenkinsFetchJobStatusService.class);
    private JenkinsJobMapper jenkinsJobMapper;
    private JenkinsBuildInfoService jenkinsBuildInfoService;
    private JenkinsLogsMapper jenkinsLogsMapper;
    private final AbstractServiceFactory jenkinsServiceFactory;

    public JenkinsFetchJobStatusService(AbstractServiceFactory jenkinsServiceFactory, JenkinsJobMapper jenkinsJobMapper,
        JenkinsBuildInfoService jenkinsBuildInfoService, JenkinsLogsMapper jenkinsLogsMapper) {
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.jenkinsJobMapper = jenkinsJobMapper;
        this.jenkinsBuildInfoService = jenkinsBuildInfoService;
        this.jenkinsLogsMapper = jenkinsLogsMapper;
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public void fetchJobStatus() {
        List<JenkinsBuildingJobInfo> jenkinsJobs = jenkinsJobMapper.queryBuildingJob();
        logger.info("需要更新jenkins_job数[{}]", jenkinsJobs.size());
        for (JenkinsBuildingJobInfo jenkinsBuildingJobInfo : jenkinsJobs) {

            try {
                String jobName = jenkinsBuildingJobInfo.getJobName();
                JobService jobService =
                    jenkinsServiceFactory.getInstance(jenkinsBuildingJobInfo.getInstanceCode(), JobService.class);

                logger.info("查询参数：jobName[{}],buildNumber[{}]", jobName, jenkinsBuildingJobInfo.getJenkinsLogId());
                BuildInfo buildInfo = jobService.getBuildInfo(null, jobName, jenkinsBuildingJobInfo.getJenkinsLogId());
                logger.info("构建信息：[{}]", buildInfo);

                // 构建中则中断
                if (buildInfo.result() == null) {
                    continue;
                }

                // 更新构建结果
                JenkinsBuildInfoDTO buildInfoDTO = new JenkinsBuildInfoDTO();
                buildInfoDTO.setId(jenkinsBuildingJobInfo.getBuildId());
                buildInfoDTO.setJobName(jobName);
                buildInfoDTO.setJenkinsLogId(jenkinsBuildingJobInfo.getJenkinsLogId());
                buildInfoDTO.setInstanceCode(jenkinsBuildingJobInfo.getInstanceCode());
                buildInfoDTO.setBuildStatus(buildInfo.result().equals(BuildConstants.SUCCESS) ? 1 : -1);
                jenkinsBuildInfoService.saveBuildIfo(buildInfoDTO);

                ProgressiveText buildLog =
                    jobService.lookBuildLog(null, jobName, jenkinsBuildingJobInfo.getJenkinsLogId(), 0);
                JenkinsLogs jenkinsLogs = new JenkinsLogs();
                jenkinsLogs.setContent(buildLog.text());
                jenkinsLogs.setJenkinsBuildId(jenkinsBuildingJobInfo.getBuildId());
                jenkinsLogs.setCreateTime(new Date());
                jenkinsLogsMapper.insertSelective(jenkinsLogs);
            } catch (Exception e) {
                logger.error("查询构建结果发生异常:{}", e.getMessage());
            }

        }

    }

}
