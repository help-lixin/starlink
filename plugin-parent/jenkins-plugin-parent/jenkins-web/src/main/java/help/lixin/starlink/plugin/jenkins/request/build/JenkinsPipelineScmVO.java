package help.lixin.starlink.plugin.jenkins.request.build;

import java.io.Serializable;

public class JenkinsPipelineScmVO implements Serializable {

    private String url;

    private String credentialsId;

    private String branch;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCredentialsId() {
        return credentialsId;
    }

    public void setCredentialsId(String credentialsId) {
        this.credentialsId = credentialsId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

}