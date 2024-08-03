package help.lixin.starlink.plugin.jenkins.request.build;

import help.lixin.starlink.plugin.jenkins.api.model.JenkinsManageToolsModule;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/3 上午11:19
 * @Description
 */
public class JenkinsCheckNameVO {
    @NotNull(message = "插件类型不能为空")
    private JenkinsManageToolsModule jenkinsManageToolsModule;
    @NotBlank(message = "插件名称不能为空")
    private String name;
    @NotBlank(message = "实例编码不能为空")
    private String instanceCode;

    public JenkinsManageToolsModule getJenkinsManageToolsModule() {
        return jenkinsManageToolsModule;
    }

    public void setJenkinsManageToolsModule(JenkinsManageToolsModule jenkinsManageToolsModule) {
        this.jenkinsManageToolsModule = jenkinsManageToolsModule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }
}
