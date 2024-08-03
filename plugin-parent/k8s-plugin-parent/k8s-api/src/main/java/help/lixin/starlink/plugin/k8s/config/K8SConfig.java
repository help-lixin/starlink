package help.lixin.starlink.plugin.k8s.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import help.lixin.starlink.plugin.k8s.properties.K8SProperties;
import help.lixin.starlink.plugin.k8s.service.*;
import help.lixin.starlink.plugin.k8s.service.impl.*;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

public class K8SConfig {
    private Logger logger = LoggerFactory.getLogger(K8SConfig.class);

    @Value("${plugin:default}")
    private String plugin;

    @Value("${instance:default}")
    private String instance;

    @Bean
    public K8SProperties k8SProperties(Environment environment) {
        logger.info("插件：{}，实例：{}", plugin, instance);
        String prefix = String.format("%s.%s", plugin, instance);
        K8SProperties k8SProperties = Binder.get(environment)//
            .bind(prefix, K8SProperties.class)//
            .get();
        return k8SProperties;
    }

    @Bean(name = {"kubernetesClient", "k8sClient"})
    @ConditionalOnMissingBean
    public KubernetesClient kubernetesClient(@Autowired K8SProperties k8sPoperties, //
        @Autowired(required = false) IConfigCustomizer configCustomizer, //
        @Autowired(required = false) IKubernetesClientCustomizer kubernetesClientCustomizer) throws Exception {
        Config config = Config.empty();
        config.setApiVersion(k8sPoperties.getVersion());
        // clusters.cluster.server
        config.setMasterUrl(k8sPoperties.getUrl());
        // clusters.cluster.certificate-authority-data
        config.setCaCertData(k8sPoperties.getCertificateAuthorityData());
        // users.user.client-certificate-data
        config.setClientCertData(k8sPoperties.getClientCertificateData());
        // users.user.client-key-data
        config.setClientKeyData(k8sPoperties.getClientKeyData());
        // 可以不配置常用配置
        Config.configFromSysPropsOrEnvVars(config);

        logger.info("准备连接K8S客户端");
        KubernetesClient client = new KubernetesClientBuilder().withConfig(config).build();
        if (null != configCustomizer) {
            configCustomizer.customizer(k8sPoperties, config);
        }
        if (null != kubernetesClientCustomizer) {
            configCustomizer.customizer(k8sPoperties, config);
        }
        logger.info("K8S连接成功！");
        return client;
    }

    @Bean
    @ConditionalOnMissingBean
    public IDeploymentApiService deploymentApiService(KubernetesClient kubernetesClient) {
        return new DeploymentApiService(kubernetesClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public INodeApiService nodeApiService(KubernetesClient kubernetesClient) {
        return new NodeApiService(kubernetesClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public INamespaceApiService namespaceApiService(KubernetesClient kubernetesClient) {
        return new NameSpaceApiService(kubernetesClient);
    }

    /**
     * 监听命名空间所有操作的事件
     *
     * @param kubernetesClient
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public WatchNamespaceEventService watchNamespaceEventService(KubernetesClient kubernetesClient) {
        return new WatchNamespaceEventService(instance, kubernetesClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public ISecretApiService secretApiService(KubernetesClient kubernetesClient) {
        return new SecretApiService(kubernetesClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJobApiService jobApiService(KubernetesClient kubernetesClient) {
        return new JobApiService(kubernetesClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public ICronJobApiService cronJobApiService(KubernetesClient kubernetesClient) {
        return new CronJobApiService(kubernetesClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public IServiceApiService serviceApiService(KubernetesClient kubernetesClient) {
        return new ServiceApiService(kubernetesClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public IDaemonSetApiService daemonSetApiService(KubernetesClient kubernetesClient) {
        return new DaemonSetApiService(kubernetesClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public IPodApiService podApiService(KubernetesClient kubernetesClient) {
        return new PodApiService(kubernetesClient);
    }

}
