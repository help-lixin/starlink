package help.lixin.harbor.properties;

import help.lixin.core.credentials.impl.UserNamePasswordCredentials;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("harbor")
public class HarborProperties extends UserNamePasswordCredentials {

}
