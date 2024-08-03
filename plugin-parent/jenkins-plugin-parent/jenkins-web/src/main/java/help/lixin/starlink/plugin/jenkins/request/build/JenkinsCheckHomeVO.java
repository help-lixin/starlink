package help.lixin.starlink.plugin.jenkins.request.build;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import help.lixin.starlink.plugin.jenkins.api.model.JenkinsManageToolsModule;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/3 上午11:19
 * @Description
 */
public class JenkinsCheckHomeVO {
    @NotNull(message = "插件类型不能为空")
    private JenkinsManageToolsModule jenkinsManageToolsModule;
    @NotBlank(message = "插件路径不能为空")
    private String homePath;
    @NotBlank(message = "实例编码不能为空")
    private String instanceCode;

    public JenkinsManageToolsModule getJenkinsManageToolsModule() {
        return jenkinsManageToolsModule;
    }

    public void setJenkinsManageToolsModule(JenkinsManageToolsModule jenkinsManageToolsModule) {
        this.jenkinsManageToolsModule = jenkinsManageToolsModule;
    }

    public String getHomePath() {
        return homePath;
    }

    public void setHomePath(String homePath) {
        this.homePath = homePath;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }
}
