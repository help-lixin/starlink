package help.lixin.starlink.plugin.jenkins.dto.plugin;

import help.lixin.starlink.core.dto.PageDTO;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/27 11:52 下午
 * @Description
 */
public class JenkinsPluginPageListDTO extends PageDTO {

    private String pluginName;

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }
}
