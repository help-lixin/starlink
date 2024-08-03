package help.lixin.starlink.plugin.credential.mq.config;

import help.lixin.starlink.plugin.credential.mq.provider.token.SysCredentialUpdateTokenEventPublish;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.plugin.credential.mq.provider.opaque.SysCredentialCreateOpaqueEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.opaque.SysCredentialDeleteOpaqueEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.pwd.SysCredentialCreateUserNamePasswordEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.pwd.SysCredentialDeleteUserNamePasswordEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.pwd.SysCredentialUpdateUserNamePasswordEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.secret.SysCredentialCreateSecretEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.secret.SysCredentialDeleteSecretEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.secret.SysCredentialUpdateSecretEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.ssh.SysCredentialCreateSshEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.ssh.SysCredentialDeleteSshEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.ssh.SysCredentialUpdateSshEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.tls.SysCredentialCreateTLSEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.tls.SysCredentialDeleteTLSEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.tls.SysCredentialUpdateTLSEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.token.SysCredentialCreateTokenEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.token.SysCredentialDeleteTokenEventPublish;
import io.eventuate.tram.events.publisher.DomainEventPublisher;

/**
 * @Author: 伍岳林
 * @Date: 2024/3/1 下午4:36
 * @Description
 */
@Configuration
public class SysCredentialEventHandlerConfig {

    /** ============================ TOKEN ============================ */
    @Bean
    @ConditionalOnMissingBean(name = "sysCredentialCreateTokenEventPublish")
    public SysCredentialCreateTokenEventPublish
        sysCredentialCreateTokenEventPublish(DomainEventPublisher domainEventPublisher) {
        return new SysCredentialCreateTokenEventPublish(domainEventPublisher);
    }

    @Bean
    @ConditionalOnMissingBean(name = "sysCredentialUpdateTokenEventPublish")
    public SysCredentialUpdateTokenEventPublish
        sysCredentialUpdateTokenEventPublish(DomainEventPublisher domainEventPublisher) {
        return new SysCredentialUpdateTokenEventPublish(domainEventPublisher);
    }

    @Bean
    @ConditionalOnMissingBean(name = "sysCredentialDeleteTokenEventPublish")
    public SysCredentialDeleteTokenEventPublish
        sysCredentialDeleteTokenEventPublish(DomainEventPublisher domainEventPublisher) {
        return new SysCredentialDeleteTokenEventPublish(domainEventPublisher);
    }

    /** ============================ Secret ============================ */
    @Bean
    @ConditionalOnMissingBean(name = "sysCredentialCreateSecretEventPublish")
    public SysCredentialCreateSecretEventPublish
        sysCredentialCreateSecretEventPublish(DomainEventPublisher domainEventPublisher) {
        return new SysCredentialCreateSecretEventPublish(domainEventPublisher);
    }

    @Bean
    @ConditionalOnMissingBean(name = "sysCredentialUpdateSecretEventPublish")
    public SysCredentialUpdateSecretEventPublish
        sysCredentialUpdateSecretEventPublish(DomainEventPublisher domainEventPublisher) {
        return new SysCredentialUpdateSecretEventPublish(domainEventPublisher);
    }

    @Bean
    @ConditionalOnMissingBean(name = "sysCredentialDeleteSecretEventPublish")
    public SysCredentialDeleteSecretEventPublish
        sysCredentialDeleteSecretEventPublish(DomainEventPublisher domainEventPublisher) {
        return new SysCredentialDeleteSecretEventPublish(domainEventPublisher);
    }

    /** ============================ SSH ============================ */
    @Bean
    @ConditionalOnMissingBean(name = "sysCredentialCreateSshEventPublish")
    public SysCredentialCreateSshEventPublish
        sysCredentialCreateSshEventPublish(DomainEventPublisher domainEventPublisher) {
        return new SysCredentialCreateSshEventPublish(domainEventPublisher);
    }

    @Bean
    @ConditionalOnMissingBean(name = "sysCredentialUpdateSshEventHandlerPublish")
    public SysCredentialUpdateSshEventPublish
        sysCredentialUpdateSshEventHandlerPublish(DomainEventPublisher domainEventPublisher) {
        return new SysCredentialUpdateSshEventPublish(domainEventPublisher);
    }

    @Bean
    @ConditionalOnMissingBean(name = "sysCredentialDeleteSshEventPublish")
    public SysCredentialDeleteSshEventPublish
        sysCredentialDeleteSshEventPublish(DomainEventPublisher domainEventPublisher) {
        return new SysCredentialDeleteSshEventPublish(domainEventPublisher);
    }

    /** ============================ UserName Password ============================ */
    @Bean
    @ConditionalOnMissingBean(name = "sysCredentialCreateUserNamePasswordEventPublish")
    public SysCredentialCreateUserNamePasswordEventPublish
        sysCredentialCreateUserNamePasswordEventPublish(DomainEventPublisher domainEventPublisher) {
        return new SysCredentialCreateUserNamePasswordEventPublish(domainEventPublisher);
    }

    @Bean
    @ConditionalOnMissingBean(name = "sysCredentialUpdateUserNamePasswordEventPublish")
    public SysCredentialUpdateUserNamePasswordEventPublish
        sysCredentialUpdateUserNamePasswordEventPublish(DomainEventPublisher domainEventPublisher) {
        return new SysCredentialUpdateUserNamePasswordEventPublish(domainEventPublisher);
    }

    @Bean
    @ConditionalOnMissingBean(name = "sysCredentialDeleteUserNamePasswordEventPublish")
    public SysCredentialDeleteUserNamePasswordEventPublish
        sysCredentialDeleteUserNamePasswordEventPublish(DomainEventPublisher domainEventPublisher) {
        return new SysCredentialDeleteUserNamePasswordEventPublish(domainEventPublisher);
    }

    /** ============================ TLS ============================ */
    @Bean
    @ConditionalOnMissingBean(name = "sysCredentialCreateTLSEventPublish")
    public SysCredentialCreateTLSEventPublish
        sysCredentialCreateTLSEventPublish(DomainEventPublisher domainEventPublisher) {
        return new SysCredentialCreateTLSEventPublish(domainEventPublisher);
    }

    @Bean
    @ConditionalOnMissingBean(name = "sysCredentialUpdateTLSEventPublish")
    public SysCredentialUpdateTLSEventPublish
        sysCredentialUpdateTLSEventPublish(DomainEventPublisher domainEventPublisher) {
        return new SysCredentialUpdateTLSEventPublish(domainEventPublisher);
    }

    @Bean
    @ConditionalOnMissingBean(name = "sysCredentialDeleteTLSEventPublish")
    public SysCredentialDeleteTLSEventPublish
        sysCredentialDeleteTLSEventPublish(DomainEventPublisher domainEventPublisher) {
        return new SysCredentialDeleteTLSEventPublish(domainEventPublisher);
    }

    /** ============================ Opaque ============================ */
    @Bean
    @ConditionalOnMissingBean(name = "sysCredentialCreateOpaqueEventPublish")
    public SysCredentialCreateOpaqueEventPublish
        sysCredentialCreateOpaqueEventPublish(DomainEventPublisher domainEventPublisher) {
        return new SysCredentialCreateOpaqueEventPublish(domainEventPublisher);
    }

    @Bean
    @ConditionalOnMissingBean(name = "sysCredentialDeleteOpaqueEventPublish")
    public SysCredentialDeleteOpaqueEventPublish
        sysCredentialDeleteOpaqueEventPublish(DomainEventPublisher domainEventPublisher) {
        return new SysCredentialDeleteOpaqueEventPublish(domainEventPublisher);
    }

}
