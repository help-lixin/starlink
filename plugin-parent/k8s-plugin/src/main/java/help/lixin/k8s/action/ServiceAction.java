package help.lixin.k8s.action;

import help.lixin.core.pipeline.action.Action;
import help.lixin.k8s.service.K8SFaceService;

public class ServiceAction implements Action {
    public static final String K8S_SERVICE_ACTION = "k8s-service";

    private K8SFaceService k8sFaceService;

    public ServiceAction(K8SFaceService k8sFaceService) {
        this.k8sFaceService = k8sFaceService;
    }

    @Override
    public String name() {
        return K8S_SERVICE_ACTION;
    }
}
