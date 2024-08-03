package help.lixin.starlink.plugin.jenkins.service;

import java.util.List;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsSystemConfig;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsSystemConfigPageDTO;
import help.lixin.starlink.plugin.jenkins.dto.sys.JenkinsSystemConfigDTO;
import help.lixin.starlink.plugin.jenkins.dto.systool.OptionDTO;
import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.jenkins.api.model.JenkinsManageToolsModule;

public interface IJenkinsSystemConfigService {

    Boolean saveConfig(JenkinsSystemConfigDTO jenkinsSystemConfigDTO);

    Boolean deleteConfig(Long id);

    Integer changeStatus(Long id, Integer status, String updateBy);

    JenkinsSystemConfig querySystemConfigInfo(Long id);

    PageResponse<JenkinsSystemConfig> list(JenkinsSystemConfigPageDTO jenkinsSystemConfigPageDTO);

    Boolean syncAllSysConfig();

    List<OptionDTO> queryByPluginType(String pluginType, String instanceCode);

    List<String> queryPluginTypeByInstanceCode(String instanceCode);

    Boolean checkHome(JenkinsManageToolsModule jenkinsManageToolsModule, String homePath, String instanceCode);

    Boolean checkName(JenkinsManageToolsModule jenkinsManageToolsModule, String name, String instanceCode);
}