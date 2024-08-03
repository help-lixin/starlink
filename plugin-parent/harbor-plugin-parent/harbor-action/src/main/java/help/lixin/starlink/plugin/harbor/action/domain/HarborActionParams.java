package help.lixin.starlink.plugin.harbor.action.domain;

import java.io.Serializable;

public class HarborActionParams implements Serializable {
    private String instanceCode;

    private Long projectId;

    // 是否加密
    private Boolean isEncryption = Boolean.TRUE;

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Boolean isEncryption() {
        return isEncryption;
    }

    public Boolean getEncryption() {
        return isEncryption;
    }

    public void setEncryption(Boolean encryption) {
        isEncryption = encryption;
    }
}
