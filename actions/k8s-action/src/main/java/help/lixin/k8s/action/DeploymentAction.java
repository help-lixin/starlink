package help.lixin.k8s.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.k8s.service.K8SFaceService;


public class DeploymentAction implements Action {
    public static final String K8S_DEPLOYMENT_ACTION = "k8s-deploy";

    private K8SFaceService k8sFaceService;

    public DeploymentAction(K8SFaceService k8sFaceService) {
        this.k8sFaceService = k8sFaceService;
    }


    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        // 1. 参数解析
        String stageParams = ctx.getStageParams();
        ObjectMapper mapper = new ObjectMapper();
        DeploymentParams params = mapper.readValue(stageParams, DeploymentParams.class);

        // 2.


        return true;
    }

    @Override
    public String name() {
        return K8S_DEPLOYMENT_ACTION;
    }
}
