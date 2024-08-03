package help.lixin.starlink.plugin.k8s.config;

import help.lixin.starlink.plugin.k8s.*;
import help.lixin.starlink.plugin.k8s.job.*;
import help.lixin.starlink.plugin.k8s.job.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesAppMapper;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesNameSpaceMapper;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/12 上午10:46
 * @Description
 */
@Configuration
public class K8sJobConfig {

    @Bean
    @ConditionalOnMissingBean
    public NameSpaceSyncJob nameSpaceSyncJob(INameSpaceSyncService nameSpaceSyncService) {
        return new NameSpaceSyncJob(nameSpaceSyncService);
    }

    @Bean
    @ConditionalOnMissingBean
    public NodeSyncJob nodeSyncJob(INodeSyncService nodeSyncService) {
        return new NodeSyncJob(nodeSyncService);
    }

    @Bean
    @ConditionalOnMissingBean
    public DeploymentSyncJob deploymentSyncJob(IDeploymentSyncService deploymentSyncService) {
        return new DeploymentSyncJob(deploymentSyncService);
    }

    @Bean
    @ConditionalOnMissingBean
    public PodSyncJob podSyncJob(IPodSyncService podSyncService) {
        return new PodSyncJob(podSyncService);
    }

    @Bean
    @ConditionalOnMissingBean
    public ServiceSyncJob serviceSyncJob(IServiceSyncService serviceSyncService) {
        return new ServiceSyncJob(serviceSyncService);
    }

    @Bean
    @ConditionalOnMissingBean
    public INameSpaceSyncService nameSpaceSyncService(KubernetesNameSpaceMapper nameSpaceMapper,
        @Autowired @Qualifier("k8SServiceFactory") AbstractServiceFactory k8sServiceFactory) {
        return new NameSpaceSyncService(nameSpaceMapper, k8sServiceFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public IDeploymentSyncService deploymentSyncService(KubernetesAppMapper kubernetesAppMapper,
        KubernetesNameSpaceMapper nameSpaceMapper,
        @Autowired @Qualifier("k8SServiceFactory") AbstractServiceFactory k8sServiceFactory) {
        return new DeploymentSyncService(kubernetesAppMapper, nameSpaceMapper, k8sServiceFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public INodeSyncService nodeSyncService(KubernetesAppMapper kubernetesAppMapper,
        KubernetesNameSpaceMapper nameSpaceMapper,
        @Autowired @Qualifier("k8SServiceFactory") AbstractServiceFactory k8sServiceFactory) {
        return new NodeSyncService(kubernetesAppMapper, nameSpaceMapper, k8sServiceFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public IPodSyncService podSyncService(KubernetesAppMapper kubernetesAppMapper,
        KubernetesNameSpaceMapper nameSpaceMapper,
        @Autowired @Qualifier("k8SServiceFactory") AbstractServiceFactory k8sServiceFactory) {
        return new PodSyncService(kubernetesAppMapper, nameSpaceMapper, k8sServiceFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public IServiceSyncService serviceSyncService(KubernetesAppMapper kubernetesAppMapper,
        KubernetesNameSpaceMapper nameSpaceMapper,
        @Autowired @Qualifier("k8SServiceFactory") AbstractServiceFactory k8sServiceFactory) {
        return new ServiceSyncService(kubernetesAppMapper, nameSpaceMapper, k8sServiceFactory);
    }

}
