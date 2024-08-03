package help.lixin.starlink.plugin.jenkins.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.jenkins.mapper.*;
import help.lixin.starlink.plugin.jenkins.service.*;
import help.lixin.starlink.plugin.jenkins.service.impl.*;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/18 4:01 下午
 * @Description
 */
@Configuration
public class JenkinsServiceCnofig {

    @Bean
    @ConditionalOnMissingBean
    public IJenkinsPluginService jenkinsPluginService(
        @Autowired @Qualifier("jenkinsServiceFactory") AbstractServiceFactory jenkinsServiceFactory,
        QueryTemplate queryTemplate, JenkinsPluginInitMapper jenkinsPluginInitMapper,
        JenkinsInstallPluginsMapper jenkinsInstallPluginsMapper) {
        return new JenkinsPluginService(jenkinsServiceFactory, queryTemplate, jenkinsPluginInitMapper,
            jenkinsInstallPluginsMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJenkinsParamsService jenkinsParamsService(
        @Autowired @Qualifier("jenkinsServiceFactory") AbstractServiceFactory jenkinsServiceFactory,
        JenkinsParamsMapper jenkinsParamsMapper) {
        return new JenkinsParamsService(jenkinsServiceFactory, jenkinsParamsMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJenkinsPipelineScmService jenkinsPipelineScmService(
        @Autowired @Qualifier("jenkinsServiceFactory") AbstractServiceFactory jenkinsServiceFactory,
        JenkinsPipelineScmMapper jenkinsPipelineScmMapper) {
        return new JenkinsPipelineScmService(jenkinsServiceFactory, jenkinsPipelineScmMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJenkinsPipelineSetupAntService jenkinsPipelineSetupAntService(
        @Autowired @Qualifier("jenkinsServiceFactory") AbstractServiceFactory jenkinsServiceFactory,
        JenkinsPipelineSetupAntMapper jenkinsPipelineSetupAntMapper) {
        return new JenkinsPipelineSetupAntService(jenkinsServiceFactory, jenkinsPipelineSetupAntMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJenkinsPipelineSetupGoService jenkinsPipelineSetupGoService(
        @Autowired @Qualifier("jenkinsServiceFactory") AbstractServiceFactory jenkinsServiceFactory,
        JenkinsPipelineSetupGoMapper jenkinsPipelineSetupGoMapper) {
        return new JenkinsPipelineSetupGoService(jenkinsServiceFactory, jenkinsPipelineSetupGoMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJenkinsPipelineSetupGradleService jenkinsPipelineSetupGradleService(
        @Autowired @Qualifier("jenkinsServiceFactory") AbstractServiceFactory jenkinsServiceFactory,
        JenkinsPipelineSetupGradleMapper jenkinsPipelineSetupGradleMapper) {
        return new JenkinsPipelineSetupGradleService(jenkinsServiceFactory, jenkinsPipelineSetupGradleMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJenkinsPipelineSetupMavenService jenkinsPipelineSetupMavenService(
        @Autowired @Qualifier("jenkinsServiceFactory") AbstractServiceFactory jenkinsServiceFactory,
        JenkinsPipelineSetupMavenMapper jenkinsPipelineSetupMavenMapper) {
        return new JenkinsPipelineSetupMavenService(jenkinsServiceFactory, jenkinsPipelineSetupMavenMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJenkinsPipelineSetupNodejsService jenkinsPipelineSetupNodejsService(
        @Autowired @Qualifier("jenkinsServiceFactory") AbstractServiceFactory jenkinsServiceFactory,
        JenkinsPipelineSetupNodejsMapper jenkinsPipelineSetupNodejsMapper) {
        return new JenkinsPipelineSetupNodejsService(jenkinsServiceFactory, jenkinsPipelineSetupNodejsMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJenkinsPipelineSetupPythonService jenkinsPipelineSetupPythonService(
        @Autowired @Qualifier("jenkinsServiceFactory") AbstractServiceFactory jenkinsServiceFactory,
        JenkinsPipelineSetupPythonMapper jenkinsPipelineSetupPythonMapper) {
        return new JenkinsPipelineSetupPythonService(jenkinsServiceFactory, jenkinsPipelineSetupPythonMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJenkinsPipelineSetupShellService jenkinsPipelineSetupShellService(
        @Autowired @Qualifier("jenkinsServiceFactory") AbstractServiceFactory jenkinsServiceFactory,
        JenkinsPipelineSetupShellMapper jenkinsPipelineSetupShellMapper) {
        return new JenkinsPipelineShellService(jenkinsServiceFactory, jenkinsPipelineSetupShellMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJenkinsBuildDependencyService jenkinsBuildDependencyService(
        @Autowired @Qualifier("jenkinsServiceFactory") AbstractServiceFactory jenkinsServiceFactory,
        JenkinsBuildDependencyMapper jenkinsBuildDependencyMapper) {
        return new JenkinsBuildDependencyService(jenkinsServiceFactory, jenkinsBuildDependencyMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJenkinsBuildInfoService jenkinsBuildInfoService(
        @Autowired @Qualifier("jenkinsServiceFactory") AbstractServiceFactory jenkinsServiceFactory,
        JenkinsBuildInfoMapper jenkinsBuildInfoMapper, QueryTemplate queryTemplate) {
        return new JenkinsBuildInfoService(jenkinsServiceFactory, jenkinsBuildInfoMapper, queryTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJenkinsLogsService jenkinsLogsService(JenkinsLogsMapper jenkinsLogsMapper) {
        return new JenkinsLogsService(jenkinsLogsMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJenkinsJobService jenkinsJobService(
        @Autowired @Qualifier("jenkinsServiceFactory") AbstractServiceFactory jenkinsServiceFactory,
        IJenkinsParamsService jenkinsParamsService, IJenkinsPipelineScmService jenkinsPipelineScmService,
        IJenkinsBuildDependencyService jenkinsBuildDependencyService,
        IJenkinsPipelineSetupCommonService jenkinsPipelineSetupCommonService,
        IJenkinsBuildInfoService jenkinsBuildInfoService, JenkinsJobMapper jenkinsJobMapper,
        QueryTemplate queryTemplate) {
        return new JenkinsJobService(jenkinsServiceFactory, jenkinsParamsService, jenkinsPipelineScmService,
            jenkinsBuildDependencyService, jenkinsPipelineSetupCommonService, jenkinsBuildInfoService, jenkinsJobMapper,
            queryTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJenkinsSystemConfigService jenkinsSystemConfigService(
        @Autowired @Qualifier("jenkinsServiceFactory") AbstractServiceFactory jenkinsServiceFactory,
        JenkinsSystemConfigMapper jenkinsSystemConfigMapper, QueryTemplate queryTemplate) {
        return new JenkinsSystemConfigService(jenkinsServiceFactory, jenkinsSystemConfigMapper, queryTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJenkinsPipelineSetupCommonService jenkinsPipelineSetupCommonService(
        @Autowired @Qualifier("jenkinsServiceFactory") AbstractServiceFactory jenkinsServiceFactory,
        JenkinsPipelineSetupMapper jenkinsPipelineSetupMapper) {
        return new JenkinsPipelineSetupCommonService(jenkinsServiceFactory, jenkinsPipelineSetupMapper);
    }

}
