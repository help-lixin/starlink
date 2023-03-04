package help.lixin.k8s.action;

import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.core.pipeline.ctx.impl.StagePipelineContext;
import help.lixin.k8s.config.K8SActionConfig;
import help.lixin.k8s.config.K8SConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

public class DeploymentActionTest {

    private AnnotationConfigApplicationContext applicationContext;

    @Before
    public void initApplication() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("k8s.kubeconfigPath", "/Users/lixin/.kube/config");

        applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.getEnvironment().getPropertySources().addFirst(new MapPropertySource("test", properties));
        applicationContext.register(K8SConfig.class, K8SActionConfig.class);
        applicationContext.refresh();
    }

    @Test
    public void testExecute() throws Exception {
        PipelineContext ctx = new StagePipelineContext();
        ctx.setStageParams("{  \"yamlTemplatePath\":\"/Users/lixin/GitRepository/spider-web-platform/admin/src/main/resources/deployment-template.yml\" , \"deployName\":\"${projectName}\" ,\"podLabelName\":\"app\" ,\"podLabelValue\":\"spring-web-demo-pod\", \"imagePullSecretName\":\"loginharbor\" , \"image\":\"${REPOSITORY_URL}/${projectName}/${projectName}:v${SECOND}\" , \"containerName\":\"${projectName}\", \"port\":\"9091\",\"envs\":[ { \"containerName\":\"${projectName}\" , \"name\":\"app\" , \"value\":\"test\" }  ] }");
        ctx.getVars().put("REPOSITORY_URL", "103.215.125.86:3080");
        ctx.getVars().put("projectName", "spring-web-demo");
        ctx.getVars().put("SECOND", "1.1.0.1304");

        DeploymentAction action = applicationContext.getBean(DeploymentAction.class);
        action.execute(ctx);
    }
}
