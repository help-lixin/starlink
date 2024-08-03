package help.lixin.starlink.plugin.jenkins.job.config;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.jenkins.job.JenkinsFetchBuildResult;
import help.lixin.starlink.plugin.jenkins.job.JenkinsInstallPluginJob;
import help.lixin.starlink.plugin.jenkins.job.JenkinsRetryInstallPlugin;
import help.lixin.starlink.plugin.jenkins.job.service.IJenkinsFetchJobStatusService;
import help.lixin.starlink.plugin.jenkins.job.service.IJenkinsInstallPluginJobService;
import help.lixin.starlink.plugin.jenkins.job.service.IJenkinsRetryInstallPluginService;
import help.lixin.starlink.plugin.jenkins.job.service.impl.JenkinsFetchJobStatusService;
import help.lixin.starlink.plugin.jenkins.job.service.impl.JenkinsInstallPluginJobService;
import help.lixin.starlink.plugin.jenkins.job.service.impl.JenkinsRetryInstallPluginService;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsInstallPluginsMapper;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsJobMapper;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsLogsMapper;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsPluginInitMapper;
import help.lixin.starlink.plugin.jenkins.service.impl.JenkinsBuildInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/12 上午10:46
 * @Description
 */
@Configuration
public class JenkinsJobConfig {

    @Bean
    public JenkinsInstallPluginJob jenkinsInstallPluginJob(IJenkinsInstallPluginJobService jenkinsInstallPluginJobService){
        return new JenkinsInstallPluginJob(jenkinsInstallPluginJobService);
    }

    @Bean
    public IJenkinsInstallPluginJobService jenkinsInstallPluginJobService(
            JenkinsPluginInitMapper jenkinsPluginInitMapper,
            JenkinsInstallPluginsMapper jenkinsInstallPluginsMapper,
            @Autowired @Qualifier("jenkinsServiceFactory") AbstractServiceFactory jenkinsServiceFactory){
        return new JenkinsInstallPluginJobService(jenkinsPluginInitMapper,
                jenkinsInstallPluginsMapper,
                jenkinsServiceFactory
        );
    }

    @Bean
    public IJenkinsFetchJobStatusService jenkinsFetchJobStatusService(@Autowired @Qualifier("jenkinsServiceFactory")
                                                                      AbstractServiceFactory jenkinsServiceFactory,
                                                                      JenkinsJobMapper jenkinsJobMapper,
                                                                      JenkinsBuildInfoService jenkinsBuildInfoService,
                                                                      JenkinsLogsMapper jenkinsLogsMapper){
        return new JenkinsFetchJobStatusService(jenkinsServiceFactory,
                                                jenkinsJobMapper,
                                                jenkinsBuildInfoService,
                                                jenkinsLogsMapper);
    }

    @Bean
    public JenkinsFetchBuildResult jenkinsBuildJob(IJenkinsFetchJobStatusService jenkinsBuildJobService){
        return new JenkinsFetchBuildResult(jenkinsBuildJobService);
    }

    @Bean
    public IJenkinsRetryInstallPluginService jenkinsRetryInstallPluginService(@Autowired @Qualifier("jenkinsServiceFactory")
                                                                              AbstractServiceFactory jenkinsServiceFactory,
                                                                              JenkinsInstallPluginsMapper jenkinsInstallPluginsMapper){
        return new JenkinsRetryInstallPluginService(jenkinsServiceFactory,
                                                    jenkinsInstallPluginsMapper);
    }

    @Bean
    public JenkinsRetryInstallPlugin jenkinsRetryInstallPlugin(IJenkinsRetryInstallPluginService jenkinsRetryInstallPluginService){
        return new JenkinsRetryInstallPlugin(jenkinsRetryInstallPluginService);
    }
}
