package help.lixin.jenkins.properties;

import com.cdancy.jenkins.rest.domain.job.Artifact;
import help.lixin.core.credentials.impl.UserNamePasswordCredentials;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jenkins")
public class JenkinsProperties extends UserNamePasswordCredentials {

    // 以分钟为单位
    private Long crumbRefreshinterval = 60L;
    // 成品库路径(比如:/tmp)
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
