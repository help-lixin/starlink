package help.lixin.k8s.service.impl;

import help.lixin.k8s.constants.Constant;
import help.lixin.k8s.service.IDeploymentService;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.client.KubernetesClient;

public class DeploymentService implements IDeploymentService {

    private KubernetesClient client;

    public DeploymentService(KubernetesClient client) {
        this.client = client;
    }

    @Override
    public Deployment create(String namespace, Deployment deployment) {
        namespace = null == namespace ? Constant.DEFAULT_NAMESPACE : namespace;
        return null;
    }

    @Override
    public Deployment edit(String namespace, String deployName, Deployment deployment) {
        namespace = null == namespace ? Constant.DEFAULT_NAMESPACE : namespace;
        // TODO lixin
        return null;
    }

    @Override
    public Deployment get(String namespace, String deployName) {
        namespace = null == namespace ? Constant.DEFAULT_NAMESPACE : namespace;
        return null;
    }

    @Override
    public Boolean delete(String namespace, String deployName) {
        return true;
    }
}
