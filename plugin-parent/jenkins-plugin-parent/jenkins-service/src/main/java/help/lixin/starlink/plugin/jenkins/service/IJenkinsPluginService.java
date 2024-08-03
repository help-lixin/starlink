package help.lixin.starlink.plugin.jenkins.service;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsInstallPlugins;
import help.lixin.starlink.plugin.jenkins.dto.plugin.InstallPluginDTO;
import help.lixin.starlink.plugin.jenkins.dto.plugin.JenkinsInstallPluginsDTO;
import help.lixin.starlink.plugin.jenkins.dto.plugin.JenkinsPluginPageListDTO;
import help.lixin.response.PageResponse;

public interface IJenkinsPluginService {

    /**
     * @param installPluginDTO
     * @Author: 伍岳林
     * @Date: 2023/12/12 5:55 下午
     * @Return: java.lang.Integer
     * @Description 添加初始化插件
    */
    Integer addInitPlugins(InstallPluginDTO installPluginDTO);

    /**
     * @Author: 伍岳林
     * @Date: 2023/12/12 4:47 下午
     * @Return: java.util.List<help.lixin.starlink.plugin.jenkins.domain.JenkinsPluginInit>
     * @Description 查询初始化插件列表
     * @param jenkinsPluginPageListDTO
     * @return
     */
    PageResponse queryInitPlugins(JenkinsPluginPageListDTO jenkinsPluginPageListDTO);

    /**
     * @Author: 伍岳林
     * @Date: 2023/12/12 4:51 下午
     * @Return: java.lang.Integer
     * @Description 删除初始化插件
    */
    Integer initPluginChangeStatus(Long id, Integer status, String updateBy);

    /**
     * @Author: 伍岳林
     * @Date: 2023/12/12 4:45 下午
     * @Return: java.lang.Integer
     * @Description 安装插件
     * @param installPluginDTO
     */
    Boolean installPlugin(InstallPluginDTO installPluginDTO);

    /**
     * @Param jenkinsInstallPluginsDTO :
     * @Author: 伍岳林
     * @Date: 2023/12/12 4:45 下午
     * @Return: java.util.List<help.lixin.starlink.plugin.jenkins.domain.JenkinsInstallPlugins>
     * @Description 查询已安装插件列表
     * @return
     */
    PageResponse<JenkinsInstallPlugins> queryInstallPlugins(JenkinsInstallPluginsDTO jenkinsInstallPluginsDTO);

    /**
     * @Author: 伍岳林
     * @Date: 2023/12/12 5:24 下午
     * @Return: java.lang.Integer
     * @Description 卸载插件
     */
    Integer uninstallPlugin(Long id);


    String queryPluginInfo(String instanceCode, String pluginName);
}
