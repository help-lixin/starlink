package help.lixin.starlink.plugin.jenkins.action.domain.scm;

public class JenkinsPipelineScm {
    private String url;

    private String credentialId;

    private String branch;

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

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }
}