package help.lixin.starlink.plugin.gitlab.mq.config;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.gitlab.mq.consumer.SyncSystemCreateUserHandler;
import help.lixin.starlink.plugin.gitlab.mq.consumer.SyncSystemDeleteUserHandler;
import help.lixin.starlink.plugin.gitlab.mq.consumer.SyncSystemUpdateUserHandler;
import help.lixin.starlink.plugin.gitlab.service.IUserService;
import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcherFactory;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/24 下午5:03
 * @Description
 */
@Configuration
public class GitlabHandlerConfig {

    @Bean
    @ConditionalOnMissingBean(name = "syncSystemCreateUserHandler")
    public SyncSystemCreateUserHandler syncSystemCreateUserHandler(IUserService userService,
                                                                   @Autowired @Qualifier("gitlabServiceFactory")
                                                                   AbstractServiceFactory gitlabServiceFactory,
                                                                   StringEncryptor stringEncryptor) {
        return new SyncSystemCreateUserHandler(userService,gitlabServiceFactory,stringEncryptor);
    }

    @Bean
    @ConditionalOnMissingBean(name = "syncSystemUpdateUserHandler")
    public SyncSystemUpdateUserHandler syncSystemUpdateUserHandler(IUserService userService,
                                                                   @Autowired @Qualifier("gitlabServiceFactory")
                                                                   AbstractServiceFactory gitlabServiceFactory,
                                                                   StringEncryptor stringEncryptor) {
        return new SyncSystemUpdateUserHandler(userService,gitlabServiceFactory,stringEncryptor);
    }

    @Bean
    @ConditionalOnMissingBean(name = "syncSystemDeleteUserHandler")
    public SyncSystemDeleteUserHandler syncSystemDeleteUserHandler(IUserService userService,
                                                                   @Autowired @Qualifier("gitlabServiceFactory")
                                                                   AbstractServiceFactory gitlabServiceFactory) {
        return new SyncSystemDeleteUserHandler(userService,gitlabServiceFactory);
    }



    @Bean
    @ConditionalOnMissingBean(name = "gitlabCreateUserDomainEventDispatcher")
    public DomainEventDispatcher gitlabCreateUserDomainEventDispatcher(
                                    DomainEventDispatcherFactory domainEventDispatcherFactory, //
                                    SyncSystemCreateUserHandler syncSystemCreateUserHandler) {
        return domainEventDispatcherFactory.make("gitlabCreateUserDomainEventDispatcherGroup", //
                syncSystemCreateUserHandler.domainEventHandlers());
    }

    @Bean
    @ConditionalOnMissingBean(name = "gitlabUpdateUserDomainEventDispatcher")
    public DomainEventDispatcher gitlabUpdateUserDomainEventDispatcher(
                                    DomainEventDispatcherFactory domainEventDispatcherFactory, //
                                    SyncSystemUpdateUserHandler syncSystemUpdateUserHandler) {
        return domainEventDispatcherFactory.make("gitlabUpdateUserDomainEventDispatcherGroup", //
                syncSystemUpdateUserHandler.domainEventHandlers());
    }

    @Bean
    @ConditionalOnMissingBean(name = "gitlabDeleteUserDomainEventDispatcher")
    public DomainEventDispatcher gitlabDeleteUserDomainEventDispatcher(
                                    DomainEventDispatcherFactory domainEventDispatcherFactory, //
                                    SyncSystemDeleteUserHandler syncSystemDeleteUserHandler) {
        return domainEventDispatcherFactory.make("gitlabDeleteUserDomainEventDispatcherGroup", //
                syncSystemDeleteUserHandler.domainEventHandlers());
    }
}
