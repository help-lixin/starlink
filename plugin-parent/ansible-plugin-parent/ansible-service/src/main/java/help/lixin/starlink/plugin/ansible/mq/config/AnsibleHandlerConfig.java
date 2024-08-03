package help.lixin.starlink.plugin.ansible.mq.config;

import help.lixin.starlink.plugin.ansible.constants.AnsibleConstants;
import help.lixin.starlink.plugin.ansible.mapper.AnsibleHostManageMapper;
import help.lixin.starlink.plugin.ansible.mq.consumer.UpdateAnsibleKnowHostHandler;
import help.lixin.starlink.plugin.ansible.mq.provider.DeleteAnsibleHostsHandler;
import help.lixin.starlink.plugin.ansible.mq.provider.UpdateAnsibleHostsHandler;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcherFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/24 下午5:03
 * @Description
 */
@Configuration
public class AnsibleHandlerConfig {

    @Bean
    @ConditionalOnMissingBean(name = "updateAnsibleKnowHostHandler")
    public UpdateAnsibleKnowHostHandler updateAnsibleKnowHostHandler(AnsibleHostManageMapper ansibleHostManageMapper) {
        return new UpdateAnsibleKnowHostHandler(AnsibleConstants.ANSIBLE_KNOWN_HOST_EVENT_TOPIC, ansibleHostManageMapper);
    }

    @Bean
    @ConditionalOnMissingBean(name = "updateAnsibleHostsHandler")
    public UpdateAnsibleHostsHandler updateAnsibleHostsHandler(DomainEventPublisher domainEventPublisher,
                                                               AnsibleHostManageMapper ansibleHostManageMapper) {
        return new UpdateAnsibleHostsHandler(domainEventPublisher,
                                            ansibleHostManageMapper);
    }

    @Bean
    @ConditionalOnMissingBean(name = "deleteAnsibleHostsHandler")
    public DeleteAnsibleHostsHandler deleteAnsibleHostsHandler(DomainEventPublisher domainEventPublisher,
                                                               AnsibleHostManageMapper ansibleHostManageMapper) {
        return new DeleteAnsibleHostsHandler(domainEventPublisher,
                                            ansibleHostManageMapper);
    }


    @Bean
    @ConditionalOnMissingBean(name = "ansibleDomainEventDispatcher")
    public DomainEventDispatcher ansibleDomainEventDispatcher(DomainEventDispatcherFactory domainEventDispatcherFactory, //
                                                              UpdateAnsibleKnowHostHandler updateAnsibleKnowHostHandler) {
        return domainEventDispatcherFactory.make("updateAnsibleEventDispatcherGroup", //
                updateAnsibleKnowHostHandler.domainEventHandlers());
    } // end
}
