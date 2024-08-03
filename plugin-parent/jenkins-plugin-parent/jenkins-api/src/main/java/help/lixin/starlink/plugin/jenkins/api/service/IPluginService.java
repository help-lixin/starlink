package help.lixin.starlink.plugin.jenkins.api.service;


import help.lixin.starlink.plugin.jenkins.api.model.Plugin;
import help.lixin.starlink.plugin.jenkins.api.model.PluginSearchResultInfo;

import java.util.List;

public interface IPluginService {

    // 查询出所有已经安装的插件列表
    List<Plugin> installedPlugins();


    // 安装插件
    void installPlugin(String pluginShortName, String version);


    // 查询插件
    default List<PluginSearchResultInfo> searchPlugin(String pluginName) {
        return searchPlugin(pluginName, null);
    }

    // 查询插件
    List<PluginSearchResultInfo> searchPlugin(String pluginName, Integer maxRows);
}
