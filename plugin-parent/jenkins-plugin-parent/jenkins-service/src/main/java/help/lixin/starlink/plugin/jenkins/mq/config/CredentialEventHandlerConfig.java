package help.lixin.starlink.plugin.jenkins.mq.config;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.jenkins.mq.consumer.ssh.CreateJenkinsSshEventHandler;
import help.lixin.starlink.plugin.jenkins.mq.consumer.ssh.UpdateJenkinsSshEventHandler;
import help.lixin.starlink.plugin.jenkins.mq.consumer.token.CreateJenkinsTokenEventHandler;
import help.lixin.starlink.plugin.jenkins.mq.consumer.token.UpdateJenkinsTokenEventHandler;
import help.lixin.starlink.plugin.jenkins.mq.consumer.pwd.CreateJenkinsPwdEventHandler;
import help.lixin.starlink.plugin.jenkins.mq.consumer.pwd.UpdateJenkinsPwdEventHandler;
import help.lixin.starlink.plugin.jenkins.mq.consumer.secret.CreateJenkinsSecretEventHandler;
import help.lixin.starlink.plugin.jenkins.mq.consumer.secret.UpdateJenkinsSecretEventHandler;
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
public class CredentialEventHandlerConfig {

    /**
     * ====================== TOKEN ======================
     */

    @Bean
    @ConditionalOnMissingBean(name = "createJenkinsTokenEventHandler")
    public CreateJenkinsTokenEventHandler createJenkinsTokenEventHandler(@Autowired @Qualifier("jenkinsServiceFactory") //
                                                                                 AbstractServiceFactory jenkinsServiceFactory,
                                                                         StringEncryptor stringEncryptor) {
        return new CreateJenkinsTokenEventHandler(jenkinsServiceFactory,stringEncryptor);
    }

    @Bean
    @ConditionalOnMissingBean(name = "updateJenkinsTokenEventHandler")
    public UpdateJenkinsTokenEventHandler updateJenkinsTokenEventHandler(@Autowired @Qualifier("jenkinsServiceFactory") //
                                                                                 AbstractServiceFactory jenkinsServiceFactory,
                                                                         StringEncryptor stringEncryptor) {
        return new UpdateJenkinsTokenEventHandler(jenkinsServiceFactory,stringEncryptor);
    }


    @Bean
    @ConditionalOnMissingBean(name = "createJenkinsTokenEventDispatcher")
    public DomainEventDispatcher createJenkinsTokenEventDispatcher(
            DomainEventDispatcherFactory domainEventDispatcherFactory,
            CreateJenkinsTokenEventHandler createJenkinsTokenEventHandler) {
        return domainEventDispatcherFactory.make("createJenkinsTokenGroup",
                createJenkinsTokenEventHandler.domainEventHandlers());
    }

    @Bean
    @ConditionalOnMissingBean(name = "updateJenkinsTokenEventDispatcher")
    public DomainEventDispatcher updateJenkinsTokenEventDispatcher(
            DomainEventDispatcherFactory domainEventDispatcherFactory,
            UpdateJenkinsTokenEventHandler updateJenkinsTokenEventHandler) {
        return domainEventDispatcherFactory.make("updateJenkinsTokenGroup",
                updateJenkinsTokenEventHandler.domainEventHandlers());
    }

    /**
     * ====================== SECRET ======================
     */

    @Bean
    @ConditionalOnMissingBean(name = "createJenkinsSecretEventHandler")
    public CreateJenkinsSecretEventHandler createJenkinsSecretEventHandler(@Autowired @Qualifier("jenkinsServiceFactory") //
                                                                                       AbstractServiceFactory jenkinsServiceFactory,
                                                                           StringEncryptor stringEncryptor) {
        return new CreateJenkinsSecretEventHandler(jenkinsServiceFactory,stringEncryptor);
    }

    @Bean
    @ConditionalOnMissingBean(name = "updateJenkinsSecretEventHandler")
    public UpdateJenkinsSecretEventHandler updateJenkinsSecretEventHandler(@Autowired @Qualifier("jenkinsServiceFactory") //
                                                                                       AbstractServiceFactory jenkinsServiceFactory,
                                                                           StringEncryptor stringEncryptor) {
        return new UpdateJenkinsSecretEventHandler(jenkinsServiceFactory,stringEncryptor);
    }


    @Bean
    @ConditionalOnMissingBean(name = "createJenkinsSecretEventDispatcher")
    public DomainEventDispatcher createJenkinsSecretEventDispatcher(
            DomainEventDispatcherFactory domainEventDispatcherFactory,
            CreateJenkinsSecretEventHandler createJenkinsSecretEventHandler) {
        return domainEventDispatcherFactory.make("createJenkinsSecretGroup",
                createJenkinsSecretEventHandler.domainEventHandlers());
    }

    @Bean
    @ConditionalOnMissingBean(name = "updateJenkinsSecretEventDispatcher")
    public DomainEventDispatcher updateJenkinsSecretEventDispatcher(
            DomainEventDispatcherFactory domainEventDispatcherFactory,
            UpdateJenkinsSecretEventHandler updateJenkinsSecretEventHandler) {
        return domainEventDispatcherFactory.make("updateJenkinsSecretGroup",
                updateJenkinsSecretEventHandler.domainEventHandlers());
    }

    /**
     * ====================== SSH ======================
     */

    @Bean
    @ConditionalOnMissingBean(name = "createJenkinsSshEventHandler")
    public CreateJenkinsSshEventHandler createJenkinsSshEventHandler(@Autowired @Qualifier("jenkinsServiceFactory") //
                                                                                 AbstractServiceFactory jenkinsServiceFactory,
                                                                     StringEncryptor stringEncryptor) {
        return new CreateJenkinsSshEventHandler(jenkinsServiceFactory,stringEncryptor);
    }

    @Bean
    @ConditionalOnMissingBean(name = "updateJenkinsSshEventHandler")
    public UpdateJenkinsSshEventHandler updateJenkinsSshEventHandler(@Autowired @Qualifier("jenkinsServiceFactory") //
                                                                                 AbstractServiceFactory jenkinsServiceFactory,
                                                                     StringEncryptor stringEncryptor) {
        return new UpdateJenkinsSshEventHandler(jenkinsServiceFactory,stringEncryptor);
    }


    @Bean
    @ConditionalOnMissingBean(name = "createJenkinsSshEventDispatcher")
    public DomainEventDispatcher createJenkinsSshEventDispatcher(
            DomainEventDispatcherFactory domainEventDispatcherFactory,
            CreateJenkinsSshEventHandler createJenkinsSshEventHandler) {
        return domainEventDispatcherFactory.make("createJenkinsSshGroup",
                createJenkinsSshEventHandler.domainEventHandlers());
    }

    @Bean
    @ConditionalOnMissingBean(name = "updateJenkinsSshEventDispatcher")
    public DomainEventDispatcher updateJenkinsSshEventDispatcher(
            DomainEventDispatcherFactory domainEventDispatcherFactory,
            UpdateJenkinsSshEventHandler updateJenkinsSshEventHandler) {
        return domainEventDispatcherFactory.make("updateJenkinsSshGroup",
                updateJenkinsSshEventHandler.domainEventHandlers());
    }

    /**
     * ====================== USERNAME_PASSWORD ======================
     */

    @Bean
    @ConditionalOnMissingBean(name = "createJenkinsPwdEventHandler")
    public CreateJenkinsPwdEventHandler createJenkinsPwdEventHandler(@Autowired @Qualifier("jenkinsServiceFactory") //
                                                                                 AbstractServiceFactory jenkinsServiceFactory,
                                                                     StringEncryptor stringEncryptor) {
        return new CreateJenkinsPwdEventHandler(jenkinsServiceFactory,stringEncryptor);
    }

    @Bean
    @ConditionalOnMissingBean(name = "updateJenkinsPwdEventHandler")
    public UpdateJenkinsPwdEventHandler updateJenkinsPwdEventHandler(@Autowired @Qualifier("jenkinsServiceFactory") //
                                                                                 AbstractServiceFactory jenkinsServiceFactory,
                                                                     StringEncryptor stringEncryptor) {
        return new UpdateJenkinsPwdEventHandler(jenkinsServiceFactory,stringEncryptor);
    }


    @Bean
    @ConditionalOnMissingBean(name = "createJenkinsUserNamePasswordEventDispatcher")
    public DomainEventDispatcher createJenkinsUserNamePasswordEventDispatcher(
            DomainEventDispatcherFactory domainEventDispatcherFactory,
            CreateJenkinsPwdEventHandler createJenkinsPwdEventHandler) {
        return domainEventDispatcherFactory.make("createJenkinsUserNamePasswordGroup",
                createJenkinsPwdEventHandler.domainEventHandlers());
    }

    @Bean
    @ConditionalOnMissingBean(name = "updateJenkinsUserNamePasswordEventDispatcher")
    public DomainEventDispatcher updateJenkinsUserNamePasswordEventDispatcher(
            DomainEventDispatcherFactory domainEventDispatcherFactory,
            UpdateJenkinsPwdEventHandler updateJenkinsPwdEventHandler) {
        return domainEventDispatcherFactory.make("updateJenkinsUserNamePasswordGroup",
                updateJenkinsPwdEventHandler.domainEventHandlers());
    }

}