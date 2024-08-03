package help.lixin.starlink.plugin.k8s.service;

import help.lixin.starlink.plugin.k8s.properties.K8SProperties;
import io.fabric8.kubernetes.client.Config;

public interface IConfigCustomizer {
    void customizer(K8SProperties k8sPoperties, Config config);
}
