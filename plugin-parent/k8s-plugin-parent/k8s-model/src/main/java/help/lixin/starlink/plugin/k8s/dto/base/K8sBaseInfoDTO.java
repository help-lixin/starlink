package help.lixin.starlink.plugin.k8s.dto.base;

public abstract class K8sBaseInfoDTO {

    protected String instanceCode;
    protected String namespace;
    protected Long nameSpaceId;
    protected String name;

    public Long getNameSpaceId() {
        return nameSpaceId;
    }

    public void setNameSpaceId(Long nameSpaceId) {
        this.nameSpaceId = nameSpaceId;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
