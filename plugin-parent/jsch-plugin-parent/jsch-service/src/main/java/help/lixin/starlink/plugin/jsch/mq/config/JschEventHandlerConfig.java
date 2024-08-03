package help.lixin.starlink.plugin.jsch.mq.config;

import help.lixin.starlink.plugin.jsch.mq.consumer.DeleteAnsibleHostsEventHandler;
import help.lixin.starlink.plugin.jsch.mq.consumer.DownloadKnowHostEventHandler;
import help.lixin.starlink.plugin.jsch.mq.consumer.UpdateAnsibleHostsEventHandler;
import help.lixin.starlink.plugin.jsch.service.IGenerateSshHostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.jsch.constants.JschEventConstants;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcherFactory;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/24 下午5:03
 * @Description
 */
@Configuration
public class JschEventHandlerConfig {

    @Bean
    @ConditionalOnMissingBean
    public UpdateAnsibleHostsEventHandler updateAnsibleHostsEventHandler(@Autowired @Qualifier("jschServiceFactory") //
    AbstractServiceFactory jschServiceFactory, //
                                                                         IGenerateSshHostsService generateHostsService) {
        return new UpdateAnsibleHostsEventHandler(jschServiceFactory, //
            generateHostsService, //
            JschEventConstants.UPDATE_HOSTS_EVENT_TOPIC);
    }

    @Bean
    @ConditionalOnMissingBean
    public DeleteAnsibleHostsEventHandler deleteAnsibleHostsEventHandler(@Autowired @Qualifier("jschServiceFactory") //
    AbstractServiceFactory jschServiceFactory) {
        return new DeleteAnsibleHostsEventHandler(jschServiceFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public DownloadKnowHostEventHandler downloadKnowHostEventHandler(@Autowired @Qualifier("jschServiceFactory") //
    AbstractServiceFactory jschServiceFactory, DomainEventPublisher domainEventPublisher) {
        return new DownloadKnowHostEventHandler(jschServiceFactory, //
            domainEventPublisher, //
            JschEventConstants.DOWNLOAD_KNOWN_HOSTS_EVENT_TOPIC);
    }

    @Bean
    @ConditionalOnMissingBean(name = "deleteAnsibleEventDispatcher")
    public DomainEventDispatcher deleteAnsibleEventDispatcher(DomainEventDispatcherFactory domainEventDispatcherFactory,
        DeleteAnsibleHostsEventHandler deleteAnsibleHostsEventHandler) {
        return domainEventDispatcherFactory.make("deleteAnsibleHostsEventDispatcherGroup",
            deleteAnsibleHostsEventHandler.domainEventHandlers());
    } // end

    @Bean
    @ConditionalOnMissingBean(name = "updateAnsibleEventDispatcher")
    public DomainEventDispatcher updateAnsibleEventDispatcher(DomainEventDispatcherFactory domainEventDispatcherFactory,
        UpdateAnsibleHostsEventHandler updateAnsibleHostsEventHandler) {
        return domainEventDispatcherFactory.make("updateAnsibleHostsEventDispatcherGroup",
            updateAnsibleHostsEventHandler.domainEventHandlers());
    } // end

    @Bean
    @ConditionalOnMissingBean(name = "downloadKnownHostsEventDispatcher")
    public DomainEventDispatcher downloadKnownHostsEventDispatcher(
        DomainEventDispatcherFactory domainEventDispatcherFactory,
        DownloadKnowHostEventHandler downloadKnowHostEventHandler) {
        return domainEventDispatcherFactory.make("downloadKnownHostsEventDispatcherGroup",
            downloadKnowHostEventHandler.domainEventHandlers());
    } // end
}
