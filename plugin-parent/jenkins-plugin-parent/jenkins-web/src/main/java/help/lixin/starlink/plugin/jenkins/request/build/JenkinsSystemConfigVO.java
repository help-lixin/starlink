package help.lixin.starlink.plugin.jenkins.request.build;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPluginTypeEnum;

/**
 * Table: jenkins_system_config
 */
public class JenkinsSystemConfigVO implements Serializable {
    private Long id;

    @NotBlank(message = "名称不能为空")
    private String name;

    @NotBlank(message = "值不能为空")
    private String value;

    @NotNull(message = "插件类型不能为空")
    private JenkinsPluginTypeEnum pluginType;

    @NotBlank(message = "插件编码不能为空")
    private String instanceCode;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}