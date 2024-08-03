package help.lixin.starlink.plugin.jenkins.convert;

import org.mapstruct.Mapper;

import help.lixin.starlink.plugin.jenkins.dto.plugin.InstallPluginDTO;
import help.lixin.starlink.plugin.jenkins.dto.plugin.JenkinsInstallPluginsDTO;
import help.lixin.starlink.plugin.jenkins.dto.plugin.JenkinsPluginPageListDTO;
import help.lixin.starlink.plugin.jenkins.request.plugin.InstallPluginVO;
import help.lixin.starlink.plugin.jenkins.request.plugin.JenkinsInstallPluginsVO;
import help.lixin.starlink.plugin.jenkins.request.plugin.JenkinsPluginPageListVO;

@Mapper
public interface PluginControllerConvert {

    JenkinsInstallPluginsDTO convert(JenkinsInstallPluginsVO jenkinsInstallPluginsVO);

    InstallPluginDTO convert(InstallPluginVO installPluginVO);

    JenkinsPluginPageListDTO convert(JenkinsPluginPageListVO jenkinsPluginPageListVO);
}
