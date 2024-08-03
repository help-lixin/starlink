package help.lixin.starlink.plugin.jenkins.dto.sys;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPluginTypeEnum;

import java.io.Serializable;

public class JenkinsSystemConfigDTO implements Serializable {
    private Long id;

    private String name;

    private String value;

    private JenkinsPluginTypeEnum pluginType;

    private String instanceCode;

    private Byte status;

    private String createBy;


    private static final long serialVersionUID = 1L;

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
        this.name = name == null ? null : name.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
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
        this.instanceCode = instanceCode == null ? null : instanceCode.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

}