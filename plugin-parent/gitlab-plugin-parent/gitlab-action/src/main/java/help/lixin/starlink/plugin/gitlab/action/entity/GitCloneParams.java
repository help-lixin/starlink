package help.lixin.starlink.plugin.gitlab.action.entity;

public class GitCloneParams {
    private String url;
    // 分支:main
    private String branch;
    // 项目名称:spring-web-demo
    private String projectName;
    // 凭证管理id
    private String credentialId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }
}
