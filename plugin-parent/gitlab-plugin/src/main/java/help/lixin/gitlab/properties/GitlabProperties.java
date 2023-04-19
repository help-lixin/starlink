package help.lixin.gitlab.properties;

import help.lixin.core.credentials.impl.TokenCredentials;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("gitlab")
public class GitlabProperties extends TokenCredentials {

}
