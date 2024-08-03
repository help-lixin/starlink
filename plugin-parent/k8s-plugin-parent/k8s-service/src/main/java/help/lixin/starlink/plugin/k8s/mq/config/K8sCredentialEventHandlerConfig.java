package help.lixin.starlink.plugin.k8s.mq.config;

import help.lixin.starlink.plugin.k8s.mq.consumer.opaque.CreateK8sOpaqueEventHandler;
import help.lixin.starlink.plugin.k8s.mq.consumer.opaque.DeleteK8sOpaqueEventHandler;
import help.lixin.starlink.plugin.k8s.mq.consumer.opaque.UpdateK8sOpaqueEventHandler;
import help.lixin.starlink.plugin.k8s.mq.consumer.pods.K8sSyncPodsEventHandler;
import help.lixin.starlink.plugin.k8s.mq.consumer.pwd.CreateK8sPWDEventHandler;
import help.lixin.starlink.plugin.k8s.mq.consumer.pwd.DeleteK8sPWDEventHandler;
import help.lixin.starlink.plugin.k8s.mq.consumer.pwd.UpdateK8sPWDEventHandler;
import help.lixin.starlink.plugin.k8s.mq.consumer.tls.CreateK8sTLSEventHandler;
import help.lixin.starlink.plugin.k8s.mq.consumer.tls.DeleteK8sTLSEventHandler;
import help.lixin.starlink.plugin.k8s.mq.consumer.tls.UpdateK8sTLSEventHandler;
import help.lixin.starlink.plugin.k8s.mq.provider.K8sPodsEventPublish;
import help.lixin.starlink.plugin.k8s.service.IK8sPodService;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcherFactory;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/24 下午5:03
 * @Description
 */
@Configuration
public class K8sCredentialEventHandlerConfig {

    /** ================= TLS ================= */

    @Bean
    @ConditionalOnMissingBean(name = "createK8sTLSEventHandler")
    public CreateK8sTLSEventHandler createK8sTLSEventHandler(@Autowired @Qualifier("k8SServiceFactory") //
    AbstractServiceFactory k8sServiceFactory, StringEncryptor stringEncryptor) {
        return new CreateK8sTLSEventHandler(k8sServiceFactory, stringEncryptor);
    }

    @Bean
    @ConditionalOnMissingBean(name = "updateK8sTLSEventHandler")
    public UpdateK8sTLSEventHandler updateK8sTLSEventHandler(@Autowired @Qualifier("k8SServiceFactory") //
    AbstractServiceFactory k8sServiceFactory, StringEncryptor stringEncryptor) {
        return new UpdateK8sTLSEventHandler(k8sServiceFactory, stringEncryptor);
    }

    @Bean
    @ConditionalOnMissingBean(name = "deleteK8sTLSEventHandler")
    public DeleteK8sTLSEventHandler deleteK8sTLSEventHandler(@Autowired @Qualifier("k8SServiceFactory") //
    AbstractServiceFactory k8sServiceFactory, StringEncryptor stringEncryptor) {
        return new DeleteK8sTLSEventHandler(k8sServiceFactory, stringEncryptor);
    }

    @Bean
    @ConditionalOnMissingBean(name = "createK8sTLSEventDispatcher")
    public DomainEventDispatcher createK8sTLSEventDispatcher(DomainEventDispatcherFactory domainEventDispatcherFactory,
        CreateK8sTLSEventHandler createK8STLSEventHandler) {
        return domainEventDispatcherFactory.make("createK8STLSGroup", createK8STLSEventHandler.domainEventHandlers());
    }

    @Bean
    @ConditionalOnMissingBean(name = "updateK8sTLSEventDispatcher")
    public DomainEventDispatcher updateK8sTLSEventDispatcher(DomainEventDispatcherFactory domainEventDispatcherFactory,
        UpdateK8sTLSEventHandler updateK8STLSEventHandler) {
        return domainEventDispatcherFactory.make("updateK8STLSGroup", updateK8STLSEventHandler.domainEventHandlers());
    }

    @Bean
    @ConditionalOnMissingBean(name = "deleteK8sTLSEventDispatcher")
    public DomainEventDispatcher deleteK8sTLSEventDispatcher(DomainEventDispatcherFactory domainEventDispatcherFactory,
        DeleteK8sTLSEventHandler deleteK8sTLSEventHandler) {
        return domainEventDispatcherFactory.make("deleteK8STLSGroup", deleteK8sTLSEventHandler.domainEventHandlers());
    }

    /** ================= OPAQUE ================= */

    @Bean
    @ConditionalOnMissingBean(name = "createK8sOpaqueEventHandler")
    public CreateK8sOpaqueEventHandler createK8sOpaqueEventHandler(@Autowired @Qualifier("k8SServiceFactory") //
    AbstractServiceFactory k8sServiceFactory, StringEncryptor stringEncryptor) {
        return new CreateK8sOpaqueEventHandler(k8sServiceFactory, stringEncryptor);
    }

    @Bean
    @ConditionalOnMissingBean(name = "updateK8sOpaqueEventHandler")
    public UpdateK8sOpaqueEventHandler updateK8sOpaqueEventHandler(@Autowired @Qualifier("k8SServiceFactory") //
    AbstractServiceFactory k8sServiceFactory, StringEncryptor stringEncryptor) {
        return new UpdateK8sOpaqueEventHandler(k8sServiceFactory, stringEncryptor);
    }

    @Bean
    @ConditionalOnMissingBean(name = "deleteK8sOpaqueEventHandler")
    public DeleteK8sOpaqueEventHandler deleteK8sOpaqueEventHandler(@Autowired @Qualifier("k8SServiceFactory") //
    AbstractServiceFactory k8sServiceFactory, StringEncryptor stringEncryptor) {
        return new DeleteK8sOpaqueEventHandler(k8sServiceFactory, stringEncryptor);
    }

    @Bean
    @ConditionalOnMissingBean(name = "createK8sOpaqueEventDispatcher")
    public DomainEventDispatcher createK8sOpaqueEventDispatcher(
        DomainEventDispatcherFactory domainEventDispatcherFactory,
        CreateK8sOpaqueEventHandler createK8sOpaqueEventHandler) {
        return domainEventDispatcherFactory.make("createK8SOpaqueGroup",
            createK8sOpaqueEventHandler.domainEventHandlers());
    }

    @Bean
    @ConditionalOnMissingBean(name = "updateK8sOpaqueEventDispatcher")
    public DomainEventDispatcher updateK8sOpaqueEventDispatcher(
        DomainEventDispatcherFactory domainEventDispatcherFactory,
        UpdateK8sOpaqueEventHandler updateK8sOpaqueEventHandler) {
        return domainEventDispatcherFactory.make("updateK8SOpaqueGroup",
            updateK8sOpaqueEventHandler.domainEventHandlers());
    }

    @Bean
    @ConditionalOnMissingBean(name = "deleteK8sOpaqueEventDispatcher")
    public DomainEventDispatcher deleteK8sOpaqueEventDispatcher(
        DomainEventDispatcherFactory domainEventDispatcherFactory,
        DeleteK8sOpaqueEventHandler deleteK8sOpaqueEventHandler) {
        return domainEventDispatcherFactory.make("deleteK8SOpaqueGroup",
            deleteK8sOpaqueEventHandler.domainEventHandlers());
    }

    /** ================= PWD ================= */

    @Bean
    @ConditionalOnMissingBean(name = "createK8sPWDEventHandler")
    public CreateK8sPWDEventHandler createK8sPWDEventHandler(@Autowired @Qualifier("k8SServiceFactory") //
    AbstractServiceFactory k8sServiceFactory, StringEncryptor stringEncryptor) {
        return new CreateK8sPWDEventHandler(k8sServiceFactory, stringEncryptor);
    }

    @Bean
    @ConditionalOnMissingBean(name = "updateK8sPWDEventHandler")
    public UpdateK8sPWDEventHandler updateK8sPWDEventHandler(@Autowired @Qualifier("k8SServiceFactory") //
    AbstractServiceFactory k8sServiceFactory, StringEncryptor stringEncryptor) {
        return new UpdateK8sPWDEventHandler(k8sServiceFactory, stringEncryptor);
    }

    @Bean
    @ConditionalOnMissingBean(name = "deleteK8sPWDEventHandler")
    public DeleteK8sPWDEventHandler deleteK8sPWDEventHandler(@Autowired @Qualifier("k8SServiceFactory") //
    AbstractServiceFactory k8sServiceFactory) {
        return new DeleteK8sPWDEventHandler(k8sServiceFactory);
    }

    @Bean
    @ConditionalOnMissingBean(name = "k8sSyncPodsEventHandler")
    public K8sSyncPodsEventHandler k8sSyncPodsEventHandler(@Autowired @Qualifier("k8SServiceFactory") //
    AbstractServiceFactory k8sServiceFactory, IK8sPodService k8sPodService) {
        return new K8sSyncPodsEventHandler(k8sServiceFactory, k8sPodService);
    }

    @Bean
    @ConditionalOnMissingBean(name = "createK8sPWDEventDispatcher")
    public DomainEventDispatcher createK8sPWDEventDispatcher(DomainEventDispatcherFactory domainEventDispatcherFactory,
        CreateK8sPWDEventHandler createK8sPWDEventHandler) {
        return domainEventDispatcherFactory.make("createK8SPWDGroup", createK8sPWDEventHandler.domainEventHandlers());
    }

    @Bean
    @ConditionalOnMissingBean(name = "updateK8sPWDEventDispatcher")
    public DomainEventDispatcher updateK8sPWDEventDispatcher(DomainEventDispatcherFactory domainEventDispatcherFactory,
        UpdateK8sPWDEventHandler updateK8sPWDEventHandler) {
        return domainEventDispatcherFactory.make("updateK8SPWDGroup", updateK8sPWDEventHandler.domainEventHandlers());
    }

    @Bean
    @ConditionalOnMissingBean(name = "deleteK8sPWDEventDispatcher")
    public DomainEventDispatcher deleteK8sPWDEventDispatcher(DomainEventDispatcherFactory domainEventDispatcherFactory,
        DeleteK8sPWDEventHandler deleteK8sPWDEventHandler) {
        return domainEventDispatcherFactory.make("deleteK8SPWDGroup", deleteK8sPWDEventHandler.domainEventHandlers());
    }

    @Bean
    @ConditionalOnMissingBean(name = "k8sDeploymentEventPublish")
    public K8sPodsEventPublish k8sDeploymentEventPublish(DomainEventPublisher domainEventPublisher) {
        return new K8sPodsEventPublish(domainEventPublisher);
    }

    @Bean
    @ConditionalOnMissingBean(name = "k8sSyncPodsEventDispatcher")
    public DomainEventDispatcher k8sSyncPodsEventDispatcher(DomainEventDispatcherFactory domainEventDispatcherFactory,
        K8sSyncPodsEventHandler k8sSyncPodsEventHandler) {
        return domainEventDispatcherFactory.make("k8sSyncPodsDGroup", k8sSyncPodsEventHandler.domainEventHandlers());
    }

}