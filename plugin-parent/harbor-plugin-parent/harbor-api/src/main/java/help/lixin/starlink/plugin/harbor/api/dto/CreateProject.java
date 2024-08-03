package help.lixin.starlink.plugin.harbor.api.dto;

public class CreateProject {
    private String project_name;
    private Long registry_id;
    private Metadata metadata = new Metadata();
    private Long storage_limit;

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public Long getRegistry_id() {
        return registry_id;
    }

    public void setRegistry_id(Long registry_id) {
        this.registry_id = registry_id;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Long getStorage_limit() {
        return storage_limit;
    }

    public void setStorage_limit(Long storage_limit) {
        this.storage_limit = storage_limit;
    }
}
