package help.lixin.starlink.plugin.jenkins.dto.plugin;

import java.io.Serializable;

/**
 * Table: jenkins_plugin_init
 */
public class JenkinsPluginInitDTO implements Serializable {
    private Long id;

    private String pulginName;

    private Integer status;

    private String createBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPulginName() {
        return pulginName;
    }

    public void setPulginName(String pulginName) {
        this.pulginName = pulginName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}