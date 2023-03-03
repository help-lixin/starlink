package help.lixin.k8s.service;

import help.lixin.k8s.constants.Constant;
import io.fabric8.kubernetes.api.model.apps.Deployment;

public interface IDeploymentService {
    Deployment create(String namespace, Deployment deployment);

    default Deployment create(Deployment deployment) {
        return create(Constant.DEFAULT_NAMESPACE, deployment);
    }

    Deployment edit(String namespace, String deployName, Deployment deployment);

    default Deployment edit(String deployName, Deployment deployment) {
        return edit(Constant.DEFAULT_NAMESPACE, deployName, deployment);
    }

    Deployment get(String namespace, String deployName);

    default Deployment get(String deployName) {
        return get(Constant.DEFAULT_NAMESPACE, deployName);
    }

    Boolean delete(String namespace, String deployName);

    default Boolean delete(String deployName) {
        return delete(Constant.DEFAULT_NAMESPACE, deployName);
    }
}
