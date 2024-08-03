package help.lixin.starlink.plugin.harbor.api.dto;

public class TotalPageDTO {
    private int private_project_count;
    private int private_repo_count;
    private int public_project_count;
    private int public_repo_count;
    private int total_project_count;
    private int total_repo_count;

    public int getPrivate_project_count() {
        return private_project_count;
    }

    public void setPrivate_project_count(int private_project_count) {
        this.private_project_count = private_project_count;
    }

    public int getPrivate_repo_count() {
        return private_repo_count;
    }

    public void setPrivate_repo_count(int private_repo_count) {
        this.private_repo_count = private_repo_count;
    }

    public int getPublic_project_count() {
        return public_project_count;
    }

    public void setPublic_project_count(int public_project_count) {
        this.public_project_count = public_project_count;
    }

    public int getPublic_repo_count() {
        return public_repo_count;
    }

    public void setPublic_repo_count(int public_repo_count) {
        this.public_repo_count = public_repo_count;
    }

    public int getTotal_project_count() {
        return total_project_count;
    }

    public void setTotal_project_count(int total_project_count) {
        this.total_project_count = total_project_count;
    }

    public int getTotal_repo_count() {
        return total_repo_count;
    }

    public void setTotal_repo_count(int total_repo_count) {
        this.total_repo_count = total_repo_count;
    }
}
