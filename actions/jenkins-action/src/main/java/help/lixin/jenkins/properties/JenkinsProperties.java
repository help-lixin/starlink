package help.lixin.jenkins.properties;

import help.lixin.core.credentials.impl.UserNamePasswordCredentials;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jenkins")
public class JenkinsProperties extends UserNamePasswordCredentials {

    // 以分钟为单位
    private Long crumbRefreshinterval = 60L;

    public Long getCrumbRefreshinterval() {
        return crumbRefreshinterval;
    }

    public void setCrumbRefreshinterval(Long crumbRefreshinterval) {
        this.crumbRefreshinterval = crumbRefreshinterval;
    }
}
