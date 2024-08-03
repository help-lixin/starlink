package help.lixin.starlink.plugin.jenkins.api.properties;

import help.lixin.starlink.core.credentials.impl.UserNamePasswordCredentials;

public class JenkinsProperties extends UserNamePasswordCredentials {

    // 以分钟为单位
    private Long crumbRefreshinterval = 60L;
    // 下载成品库到本地的路径(比如:/tmp)
    private String artifactPath = "/tmp";

    public Long getCrumbRefreshinterval() {
        return crumbRefreshinterval;
    }

    public void setCrumbRefreshinterval(Long crumbRefreshinterval) {
        this.crumbRefreshinterval = crumbRefreshinterval;
    }

    public String getArtifactPath() {
        return artifactPath;
    }

    public void setArtifactPath(String artifactPath) {
        this.artifactPath = artifactPath;
    }
}
