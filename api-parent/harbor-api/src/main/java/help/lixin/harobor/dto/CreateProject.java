package help.lixin.harobor.dto;

import help.lixin.harobor.model.Metadata;

public class CreateProject {
    private String project_name;
    private Integer registry_id;
    private Metadata metadata = new Metadata();
    private Integer storage_limit;

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public Integer getRegistry_id() {
        return registry_id;
    }

    public void setRegistry_id(Integer registry_id) {
        this.registry_id = registry_id;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Integer getStorage_limit() {
        return storage_limit;
    }

    public void setStorage_limit(Integer storage_limit) {
        this.storage_limit = storage_limit;
    }
}
