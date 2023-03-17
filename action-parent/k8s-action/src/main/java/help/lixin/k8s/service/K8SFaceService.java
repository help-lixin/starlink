package help.lixin.k8s.service;

import help.lixin.k8s.properties.K8SProperties;
import io.fabric8.kubernetes.client.KubernetesClient;

public class K8SFaceService {

    private K8SProperties k8SProperties;

    private KubernetesClient kubernetesClient;

    private IDeploymentApiService deploymentApiService;

    public void setDeploymentApiService(IDeploymentApiService deploymentApiService) {
        this.deploymentApiService = deploymentApiService;
    }

    public IDeploymentApiService getDeploymentApiService() {
        return deploymentApiService;
    }

    public K8SProperties getK8SProperties() {
        return k8SProperties;
    }

    public void setK8SProperties(K8SProperties k8SProperties) {
        this.k8SProperties = k8SProperties;
    }

    public void setKubernetesClient(KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    public KubernetesClient getKubernetesClient() {
        return kubernetesClient;
    }
}
