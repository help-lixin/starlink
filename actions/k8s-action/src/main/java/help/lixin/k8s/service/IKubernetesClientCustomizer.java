package help.lixin.k8s.service;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.KubernetesClient;

public interface IKubernetesClientCustomizer {
    void customizer(Config config, KubernetesClient client);
}
