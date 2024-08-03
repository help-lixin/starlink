package help.lixin.starlink.plugin.jenkins.request.plugin;

import help.lixin.starlink.request.PageRequest;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/27 11:52 下午
 * @Description
 */
public class JenkinsPluginPageListVO extends PageRequest {

    private String pluginName;

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }
}
