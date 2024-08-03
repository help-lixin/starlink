package help.lixin.starlink.plugin.jenkins.xml.freestyle.delegate.impl;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsSystemConfig;
import help.lixin.starlink.plugin.jenkins.dto.setup.*;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsPluginService;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsSystemConfigService;
import help.lixin.starlink.plugin.jenkins.xml.freestyle.delegate.IBuildWrapperDelegate;
import help.lixin.starlink.plugin.jenkins.xml.pojo.build.wrappers.AbstractBuildWrapper;
import help.lixin.starlink.plugin.jenkins.xml.pojo.build.wrappers.GolangBuildWrapper;
import help.lixin.starlink.plugin.jenkins.xml.pojo.build.wrappers.NodeJSBuildWrapper;

import java.util.ArrayList;
import java.util.List;

public class BuildWrapperDelegate implements IBuildWrapperDelegate {

    private IJenkinsPluginService jenkinsPluginService;

    private IJenkinsSystemConfigService jenkinsSystemConfigService;

    private static final String PLUGIN_NAME_GO = "golang";
    private static final String PLUGIN_NAME_NODE_JS = "nodejs";
    private static final String PLUGIN_NAME_GRADLE = "gradle";

    private static final String PLUGIN_NAME_ANT = "ant";

    public BuildWrapperDelegate(IJenkinsPluginService jenkinsPluginService, //
                                IJenkinsSystemConfigService jenkinsSystemConfigService) {
        this.jenkinsPluginService = jenkinsPluginService;
        this.jenkinsSystemConfigService = jenkinsSystemConfigService;
    }


    @Override
    public List<AbstractBuildWrapper> apply(String instanceCode, List<JenkinsPipelineSetupCommonDTO> setups) {
        List<AbstractBuildWrapper> result = new ArrayList<>(0);

        for (JenkinsPipelineSetupCommonDTO setupDto : setups) {
            if (setupDto instanceof JenkinsPipelineSetupGoDTO) {
                JenkinsPipelineSetupGoDTO go = (JenkinsPipelineSetupGoDTO) setupDto;

                GolangBuildWrapper golangBuildWrapper = new GolangBuildWrapper();
                String plugin = jenkinsPluginService.queryPluginInfo(instanceCode, PLUGIN_NAME_GO);
                golangBuildWrapper.setPlugin(plugin);
                JenkinsSystemConfig jenkinsSystemConfig = jenkinsSystemConfigService.querySystemConfigInfo(go.getGoId());
                if (null != jenkinsSystemConfig) {
                    golangBuildWrapper.setGoVersion(jenkinsSystemConfig.getName());
                }

                result.add(golangBuildWrapper);
            } else if (setupDto instanceof JenkinsPipelineSetupNodejsDTO) {
                JenkinsPipelineSetupNodejsDTO nodeJs = (JenkinsPipelineSetupNodejsDTO) setupDto;
                NodeJSBuildWrapper nodeJSBuildWrapper = new NodeJSBuildWrapper();
                String plugin = jenkinsPluginService.queryPluginInfo(instanceCode, PLUGIN_NAME_NODE_JS);
                nodeJSBuildWrapper.setPlugin(plugin);

                JenkinsSystemConfig jenkinsSystemConfig = jenkinsSystemConfigService.querySystemConfigInfo(nodeJs.getNodejsId());
                if (null != jenkinsSystemConfig) {
                    nodeJSBuildWrapper.setNodeJSInstallationName(jenkinsSystemConfig.getName());
                }
                result.add(nodeJSBuildWrapper);
            } else if (setupDto instanceof JenkinsPipelineSetupGradleDTO) {
            } else if (setupDto instanceof JenkinsPipelineSetupAntDTO) {
            } else if (setupDto instanceof JenkinsPipelineSetupPythonDTO) {
                // TODO lixin
            }
        }
        return result;
    }
}
