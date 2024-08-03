package help.lixin.starlink.plugin.jenkins.request.build;

import javax.validation.constraints.NotBlank;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPluginTypeEnum;
import help.lixin.starlink.request.PageRequest;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/2 下午3:21
 * @Description
 */
public class JenkinsSystemConfigPageVO extends PageRequest {

    @NotBlank(message = "实例编码不能为空")
    private String instanceCode;

    private JenkinsPluginTypeEnum pluginType;

    private String name;

    public JenkinsPluginTypeEnum getPluginType() {
        return pluginType;
    }

    public void setPluginType(JenkinsPluginTypeEnum pluginType) {
        this.pluginType = pluginType;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
