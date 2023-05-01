package help.lixin.k8s.config;

import help.lixin.core.pipeline.service.IExpressionService;
import help.lixin.k8s.properties.K8SProperties;
import help.lixin.k8s.service.*;
import help.lixin.k8s.service.impl.DeploymentApiService;
import help.lixin.k8s.service.impl.DeploymentTemplateService;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;

@Configuration
@EnableConfigurationProperties({K8SProperties.class})
public class K8SConfig {


    @Bean(name = {"kubernetesClient", "k8sClient"})
    @ConditionalOnMissingBean
    public KubernetesClient kubernetesClient(@Autowired K8SProperties k8sPoperties,
                                             //
                                             @Autowired(required = false) IConfigCustomizer configCustomizer,
                                             //
                                             @Autowired(required = false) IKubernetesClientCustomizer kubernetesClientCustomizer) throws Exception {
        String kubeconfigContents = IOUtils.toString(new FileInputStream(k8sPoperties.getKubeconfigPath()), "UTF-8");
        Config config = Config.fromKubeconfig(kubeconfigContents);
        if (null != configCustomizer) {
            configCustomizer.customizer(k8sPoperties, config);
        }
        KubernetesClient client = new DefaultKubernetesClient(config);
        if (null != kubernetesClientCustomizer) {
            configCustomizer.customizer(k8sPoperties, config);
        }
        return client;
    }

    @Bean
    @ConditionalOnMissingBean
    public IDeploymentApiService deploymentApiService(KubernetesClient kubernetesClient) {
        return new DeploymentApiService(kubernetesClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public DeploymentTemplateService deploymentTemplateService() {
        return new DeploymentTemplateService();
    }

    @Bean
    public K8SFaceService k8sFaceService(@Autowired K8SProperties k8sProperties,
                                         //
                                         @Autowired IExpressionService expressionService,
                                         //
                                         @Autowired KubernetesClient kubernetesClient,
                                         //
                                         @Autowired IDeploymentApiService deploymentApiService,
                                         //
                                         @Autowired DeploymentTemplateService deploymentTemplateService,
                                         //
                                         @Autowired(required = false) IK8SFaceServiceCustomizer k8sFaceServiceCustomizer) {
        K8SFaceService k8sFaceService = new K8SFaceService();
        k8sFaceService.setK8SProperties(k8sProperties);
        k8sFaceService.setKubernetesClient(kubernetesClient);
        k8sFaceService.setDeploymentApiService(deploymentApiService);
        k8sFaceService.setExpressionService(expressionService);
        k8sFaceService.setDeploymentTemplateService(deploymentTemplateService);
        if (null != k8sFaceServiceCustomizer) {
            k8sFaceServiceCustomizer.customizer(k8sFaceService);
        }
        return k8sFaceService;
    }
}
