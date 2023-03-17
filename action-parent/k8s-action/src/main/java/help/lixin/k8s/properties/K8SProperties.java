package help.lixin.k8s.properties;

import help.lixin.k8s.credentials.KubeFileCredentials;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("k8s")
public class K8SProperties extends KubeFileCredentials {

}
