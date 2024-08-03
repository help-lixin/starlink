package help.lixin.starlink.plugin.jenkins.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.plugin.credential.mapper.SysCredentialCommonMapper;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsBuildDependencyMapper;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsSystemConfigMapper;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsPluginService;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsSystemConfigService;
import help.lixin.starlink.plugin.jenkins.xml.freestyle.FreeStyleProjectGenerateXmlFace;
import help.lixin.starlink.plugin.jenkins.xml.freestyle.delegate.*;
import help.lixin.starlink.plugin.jenkins.xml.freestyle.delegate.impl.*;
import help.lixin.starlink.plugin.jenkins.xml.pipeline.PipelineProjectGenerateXmlFace;

@Configuration
public class JenkinsXmlGenerateConfig {

    @Bean
    @ConditionalOnMissingBean
    public FreeStyleProjectGenerateXmlFace freeStyleProjectGenerateXmlFace(
        IBuildDependencyDelegate buildDependencyDelegate, //
        IBuildWrapperDelegate buildWrapperDelegate, //
        IJavaDelegate javaDelegate, //
        IParamsDelegate paramsDelegate, //
        IScmDelegate scmDelegate, //
        ISetupDelegate setupDelegate) {
        return new FreeStyleProjectGenerateXmlFace(buildDependencyDelegate, //
            buildWrapperDelegate, //
            javaDelegate, //
            paramsDelegate, //
            scmDelegate, //
            setupDelegate);
    }

    @Bean
    @ConditionalOnMissingBean
    public PipelineProjectGenerateXmlFace pipelineProjectGenerateXmlFace() {
        return new PipelineProjectGenerateXmlFace();
    }

    // Delegate
    @Bean
    @ConditionalOnMissingBean
    public IBuildDependencyDelegate buildDependencyDelegate(JenkinsBuildDependencyMapper jenkinsBuildDependencyMapper) {
        return new BuildDependencyDelegate();
    }

    @Bean
    @ConditionalOnMissingBean
    public IBuildWrapperDelegate buildWrapperDelegate(IJenkinsPluginService jenkinsPluginService, //
        IJenkinsSystemConfigService jenkinsSystemConfigService) {
        return new BuildWrapperDelegate(jenkinsPluginService, jenkinsSystemConfigService);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJavaDelegate javaDelegate(JenkinsSystemConfigMapper jenkinsSystemConfigMapper) {
        return new JavaDelegate(jenkinsSystemConfigMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public IParamsDelegate paramsDelegate() {
        return new ParamsDelegate();
    }

    @Bean
    @ConditionalOnMissingBean
    public IScmDelegate scmDelegate(SysCredentialCommonMapper sysCredentialCommonMapper, //
        IJenkinsPluginService jenkinsPluginService) {
        return new ScmDelegate(sysCredentialCommonMapper, jenkinsPluginService);
    }

    @Bean
    @ConditionalOnMissingBean
    public ISetupDelegate setupDelegate(IJenkinsPluginService jenkinsPluginService, //
        IJenkinsSystemConfigService jenkinsSystemConfigService) {
        return new SetupDelegate(jenkinsPluginService, jenkinsSystemConfigService);
    }
}
