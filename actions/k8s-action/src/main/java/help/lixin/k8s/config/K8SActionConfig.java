package help.lixin.k8s.config;

import help.lixin.core.pipeline.action.Action;
import help.lixin.k8s.action.DeploymentAction;
import help.lixin.k8s.action.IngressAction;
import help.lixin.k8s.action.ServiceAction;
import help.lixin.k8s.service.K8SFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class K8SActionConfig {

    @Bean
    @ConditionalOnMissingBean(name = "deploymentAction")
    public Action deploymentAction(@Autowired K8SFaceService k8sFaceService) {
        return new DeploymentAction(k8sFaceService);
    }


    @Bean
    @ConditionalOnMissingBean(name = "serviceAction")
    public Action serviceAction(@Autowired K8SFaceService k8sFaceService) {
        return new ServiceAction(k8sFaceService);
    }

    @Bean
    @ConditionalOnMissingBean(name = "ingressAction")
    public Action ingressAction(@Autowired K8SFaceService k8sFaceService) {
        return new IngressAction(k8sFaceService);
    }
}
