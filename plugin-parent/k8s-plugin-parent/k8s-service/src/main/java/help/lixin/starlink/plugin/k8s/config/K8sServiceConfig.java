package help.lixin.starlink.plugin.k8s.config;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesAppMapper;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesDeployTemplateMapper;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesNameSpaceMapper;
import help.lixin.starlink.plugin.k8s.mq.provider.K8sPodsEventPublish;
import help.lixin.starlink.plugin.k8s.service.*;
import help.lixin.starlink.plugin.k8s.service.impl.*;
import help.lixin.starlink.plugin.k8s.service.subscribe.K8sNameSpaceCreateEventSubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class K8sServiceConfig {

    @Bean
    @ConditionalOnMissingBean
    public IK8sDeployTemplateService
    k8sDeployTemplateService(QueryTemplate queryTemplate, //
                             KubernetesDeployTemplateMapper kubernetesDeployTemplateMapper) {
        return new K8sDeployTemplateService(queryTemplate, kubernetesDeployTemplateMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public K8sNameSpaceCreateEventSubscribeService
        k8sNameSpaceCreateEventSubscribeService(KubernetesNameSpaceMapper kubernetesNameSpaceMapper) {
        return new K8sNameSpaceCreateEventSubscribeService(kubernetesNameSpaceMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public IK8sSecretService
        k8sSecretService(@Autowired @Qualifier("k8SServiceFactory") AbstractServiceFactory k8sServiceFactory) {
        return new K8sSecretService(k8sServiceFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public IK8sDeploymentService k8sDeploymentService(
        @Autowired @Qualifier("k8SServiceFactory") AbstractServiceFactory k8sServiceFactory,
        QueryTemplate queryTemplate, KubernetesNameSpaceMapper kubernetesNameSpaceMapper,
        KubernetesAppMapper kubernetesAppMapper, K8sPodsEventPublish k8sPodsEventPublish) {
        return new K8sDeploymentService(k8sServiceFactory, queryTemplate, kubernetesNameSpaceMapper,
            kubernetesAppMapper, k8sPodsEventPublish);
    }

    @Bean
    @ConditionalOnMissingBean
    public IK8sCronJobService k8sCronJobService(
        @Autowired @Qualifier("k8SServiceFactory") AbstractServiceFactory k8sServiceFactory,
        QueryTemplate queryTemplate, KubernetesNameSpaceMapper kubernetesNameSpaceMapper,
        KubernetesAppMapper kubernetesAppMapper, K8sPodsEventPublish k8sPodsEventPublish) {
        return new K8sCronJobService(k8sServiceFactory, queryTemplate, kubernetesNameSpaceMapper, kubernetesAppMapper,
            k8sPodsEventPublish);
    }

    @Bean
    @ConditionalOnMissingBean
    public IK8sJobService k8sJobService(
        @Autowired @Qualifier("k8SServiceFactory") AbstractServiceFactory k8sServiceFactory,
        QueryTemplate queryTemplate, KubernetesNameSpaceMapper kubernetesNameSpaceMapper,
        KubernetesAppMapper kubernetesAppMapper, K8sPodsEventPublish k8sPodsEventPublish) {
        return new K8sJobService(k8sServiceFactory, queryTemplate, kubernetesNameSpaceMapper, kubernetesAppMapper,
            k8sPodsEventPublish);
    }

    @Bean
    @ConditionalOnMissingBean
    public IK8sDaemonSetService k8sDaemonSetService(
        @Autowired @Qualifier("k8SServiceFactory") AbstractServiceFactory k8sServiceFactory,
        QueryTemplate queryTemplate, KubernetesNameSpaceMapper kubernetesNameSpaceMapper,
        KubernetesAppMapper kubernetesAppMapper, K8sPodsEventPublish k8sPodsEventPublish) {
        return new K8sDaemonSetService(k8sServiceFactory, queryTemplate, kubernetesNameSpaceMapper, kubernetesAppMapper,
            k8sPodsEventPublish);
    }

    @Bean
    @ConditionalOnMissingBean
    public IK8sPodService k8sPodService(
        @Autowired @Qualifier("k8SServiceFactory") AbstractServiceFactory k8sServiceFactory,
        QueryTemplate queryTemplate, KubernetesNameSpaceMapper kubernetesNameSpaceMapper,
        KubernetesAppMapper kubernetesAppMapper, K8sPodsEventPublish k8sPodsEventPublish) {
        return new K8sPodService(k8sServiceFactory, queryTemplate, kubernetesNameSpaceMapper, kubernetesAppMapper,
            k8sPodsEventPublish);
    }

    @Bean
    @ConditionalOnMissingBean
    public IK8sServiceService k8sServiceService(
        @Autowired @Qualifier("k8SServiceFactory") AbstractServiceFactory k8sServiceFactory,
        QueryTemplate queryTemplate, KubernetesNameSpaceMapper kubernetesNameSpaceMapper,
        KubernetesAppMapper kubernetesAppMapper, K8sPodsEventPublish k8sPodsEventPublish) {
        return new K8sServiceService(k8sServiceFactory, queryTemplate, kubernetesNameSpaceMapper, kubernetesAppMapper,
            k8sPodsEventPublish);
    }

    @Bean
    @ConditionalOnMissingBean
    public IK8sNodeService k8sNodeService(
        @Autowired @Qualifier("k8SServiceFactory") AbstractServiceFactory k8sServiceFactory,
        QueryTemplate queryTemplate, KubernetesNameSpaceMapper kubernetesNameSpaceMapper,
        KubernetesAppMapper kubernetesAppMapper, K8sPodsEventPublish k8sPodsEventPublish) {
        return new K8sNodeService(k8sServiceFactory, queryTemplate, kubernetesNameSpaceMapper, kubernetesAppMapper,
            k8sPodsEventPublish);
    }

    @Bean
    @ConditionalOnMissingBean
    public IK8SNameSpaceService nameSpaceService(
        @Autowired @Qualifier("k8SServiceFactory") AbstractServiceFactory k8sServiceFactory,
        QueryTemplate queryTemplate, KubernetesNameSpaceMapper kubernetesNameSpaceMapper) {
        return new K8sNameSpaceService(k8sServiceFactory, queryTemplate, kubernetesNameSpaceMapper);
    }

}
