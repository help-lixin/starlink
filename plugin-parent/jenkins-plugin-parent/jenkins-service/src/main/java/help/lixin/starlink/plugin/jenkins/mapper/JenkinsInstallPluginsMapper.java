package help.lixin.starlink.plugin.jenkins.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsInstallPlugins;
import help.lixin.starlink.plugin.jenkins.dto.plugin.JenkinsInstallPluginsDTO;

import java.util.List;

public interface JenkinsInstallPluginsMapper extends BaseMapper<JenkinsInstallPlugins> {

    List<JenkinsInstallPlugins> queryList(JenkinsInstallPluginsDTO jenkinsInstallPluginsDTO);

    List<JenkinsInstallPlugins> queryRetryPlugin(String instanceCode);

}