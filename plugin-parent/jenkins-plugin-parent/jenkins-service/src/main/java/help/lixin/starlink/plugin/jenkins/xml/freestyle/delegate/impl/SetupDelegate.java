package help.lixin.starlink.plugin.jenkins.xml.freestyle.delegate.impl;

import java.util.ArrayList;
import java.util.List;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsSystemConfig;
import help.lixin.starlink.plugin.jenkins.dto.setup.*;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsPluginService;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsSystemConfigService;
import help.lixin.starlink.plugin.jenkins.xml.freestyle.delegate.ISetupDelegate;
import help.lixin.starlink.plugin.jenkins.xml.pojo.setup.*;

public class SetupDelegate implements ISetupDelegate {

    private IJenkinsSystemConfigService jenkinsSystemConfigService;

    private IJenkinsPluginService jenkinsPluginService;

    private static final String PLUGIN_NAME_ANT = "ant";

    private static final String PLUGIN_NAME_GRADLE = "gradle";

    public SetupDelegate(IJenkinsPluginService jenkinsPluginService, //
        IJenkinsSystemConfigService jenkinsSystemConfigService) {
        this.jenkinsPluginService = jenkinsPluginService;
        this.jenkinsSystemConfigService = jenkinsSystemConfigService;
    }

    @Override
    public List<AbstractSetup> apply(String instanceCode, List<JenkinsPipelineSetupCommonDTO> setups) {
        List<AbstractSetup> results = new ArrayList<>(0);

        for (JenkinsPipelineSetupCommonDTO setupDto : setups) {
            if (setupDto instanceof JenkinsPipelineSetupMavenDTO) {
                // maven
                JenkinsPipelineSetupMavenDTO maven = (JenkinsPipelineSetupMavenDTO)setupDto;
                MavenSetup mavenSetup = new MavenSetup();
                JenkinsSystemConfig jenkinsSystemConfig =
                    jenkinsSystemConfigService.querySystemConfigInfo(maven.getMavenId());
                if (null != jenkinsSystemConfig) {
                    mavenSetup.setMavenName(jenkinsSystemConfig.getName());
                }
                mavenSetup.setTargets(maven.getGoals());
                results.add(mavenSetup);
            } else if (setupDto instanceof JenkinsPipelineSetupAntDTO) {
                // ant
                JenkinsPipelineSetupAntDTO ant = (JenkinsPipelineSetupAntDTO)setupDto;
                AntSetup antSetup = new AntSetup();
                String plugin = jenkinsPluginService.queryPluginInfo(instanceCode, PLUGIN_NAME_ANT);
                antSetup.setPlugin(plugin);
                antSetup.setTargets(ant.getTargets());
                JenkinsSystemConfig jenkinsSystemConfig =
                    jenkinsSystemConfigService.querySystemConfigInfo(ant.getAntId());
                if (null != jenkinsSystemConfig) {
                    antSetup.setAntName(jenkinsSystemConfig.getName());
                }
                results.add(antSetup);
            } else if (setupDto instanceof JenkinsPipelineSetupGradleDTO) {
                // gradle
                JenkinsPipelineSetupGradleDTO gradle = (JenkinsPipelineSetupGradleDTO)setupDto;
                GradleSetup gradleSetup = new GradleSetup();

                String plugin = jenkinsPluginService.queryPluginInfo(instanceCode, PLUGIN_NAME_GRADLE);
                gradleSetup.setPlugin(plugin);
                gradleSetup.setTasks(gradle.getTask());
                JenkinsSystemConfig jenkinsSystemConfig =
                    jenkinsSystemConfigService.querySystemConfigInfo(gradle.getGradleId());
                if (null != jenkinsSystemConfig) {
                    gradleSetup.setGradleName(jenkinsSystemConfig.getName());
                }
                results.add(gradleSetup);
            } else if (setupDto instanceof JenkinsPipelineSetupGoDTO) {
                // 针对go语言,产生一个shell
                JenkinsPipelineSetupGoDTO go = (JenkinsPipelineSetupGoDTO)setupDto;
                String script = go.getScript();
                ShellSetup shellSetup = new ShellSetup();
                shellSetup.setCommand(script);
                results.add(shellSetup);
            } else if (setupDto instanceof JenkinsPipelineSetupNodejsDTO) {
                // 针对nodejs 产生一个shell
                JenkinsPipelineSetupNodejsDTO nodejs = (JenkinsPipelineSetupNodejsDTO)setupDto;
                ShellSetup shellSetup = new ShellSetup();
                shellSetup.setCommand(nodejs.getScript());
                results.add(shellSetup);
            } else if (setupDto instanceof JenkinsPipelineSetupShellDTO) {
                // shell
                JenkinsPipelineSetupShellDTO shell = (JenkinsPipelineSetupShellDTO)setupDto;
                ShellSetup shellSetup = new ShellSetup();
                shellSetup.setCommand(shell.getShellScript());
                results.add(shellSetup);
            }
        }
        return results;
    }
}
