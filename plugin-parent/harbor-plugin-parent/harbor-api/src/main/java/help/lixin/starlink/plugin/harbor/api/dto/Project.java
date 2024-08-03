package help.lixin.starlink.plugin.harbor.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {
    private Date creation_time;
    private Long current_user_role_id;
    private List<Long> current_user_role_ids = new ArrayList<>(0);
    private CveAllowlist cve_allowlist;
    private Metadata metadata;
    private String name;
    private Long owner_id;
    private String owner_name;
    private Long project_id;
    private Date update_time;

    public Date getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Date creation_time) {
        this.creation_time = creation_time;
    }

    public Long getCurrent_user_role_id() {
        return current_user_role_id;
    }

    public void setCurrent_user_role_id(Long current_user_role_id) {
        this.current_user_role_id = current_user_role_id;
    }

    public List<Long> getCurrent_user_role_ids() {
        return current_user_role_ids;
    }

    public void setCurrent_user_role_ids(List<Long> current_user_role_ids) {
        this.current_user_role_ids = current_user_role_ids;
    }

    public CveAllowlist getCve_allowlist() {
        return cve_allowlist;
    }

    public void setCve_allowlist(CveAllowlist cve_allowlist) {
        this.cve_allowlist = cve_allowlist;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
