package help.lixin.starlink.plugin.k8s.config;

import help.lixin.core.pipeline.action.Action;
import help.lixin.service.IExpressionService;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.k8s.action.DeploymentAction;
import help.lixin.starlink.plugin.k8s.service.IK8sDeployTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class K8SActionConfig {
    @Bean
    @ConditionalOnMissingBean(name = "deploymentAction")
    public Action deploymentAction(IExpressionService expressionService, //
                                   @Autowired @Qualifier("k8SServiceFactory") //
                                   AbstractServiceFactory k8SServiceFactory, //
                                   IK8sDeployTemplateService k8sDeployTemplateService //
    ) {
        return new DeploymentAction(expressionService, k8SServiceFactory, k8sDeployTemplateService);
    }
}
