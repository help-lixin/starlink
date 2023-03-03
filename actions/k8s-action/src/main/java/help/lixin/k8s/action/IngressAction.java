package help.lixin.k8s.action;

import help.lixin.core.pipeline.action.Action;
import help.lixin.k8s.service.K8SFaceService;

public class IngressAction implements Action {

    public static final String K8S_INGRESS_ACTION = "k8s-ingress";

    private K8SFaceService k8sFaceService;

    public IngressAction(K8SFaceService k8sFaceService) {
        this.k8sFaceService = k8sFaceService;
    }

    @Override
    public String name() {
        return K8S_INGRESS_ACTION;
    }
}
