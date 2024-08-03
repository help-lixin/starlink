package help.lixin.starlink.plugin.credential.config;

import help.lixin.starlink.plugin.credential.mq.provider.opaque.SysCredentialCreateOpaqueEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.pwd.SysCredentialCreateUserNamePasswordEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.pwd.SysCredentialDeleteUserNamePasswordEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.pwd.SysCredentialUpdateUserNamePasswordEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.secret.SysCredentialCreateSecretEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.secret.SysCredentialDeleteSecretEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.secret.SysCredentialUpdateSecretEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.ssh.SysCredentialCreateSshEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.ssh.SysCredentialUpdateSshEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.token.SysCredentialUpdateTokenEventPublish;
import help.lixin.starlink.plugin.credential.service.ICredentialService;
import help.lixin.starlink.plugin.credential.service.impl.CredentialService;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.credential.listen.NameSpaceSubscribeEventService;
import help.lixin.starlink.plugin.credential.mapper.*;
import help.lixin.starlink.plugin.credential.mq.provider.opaque.SysCredentialDeleteOpaqueEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.ssh.SysCredentialDeleteSshEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.tls.SysCredentialCreateTLSEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.tls.SysCredentialDeleteTLSEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.tls.SysCredentialUpdateTLSEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.token.SysCredentialCreateTokenEventPublish;
import help.lixin.starlink.plugin.credential.mq.provider.token.SysCredentialDeleteTokenEventPublish;

@Configuration
public class CredentialConfig {

    @Bean
    @ConditionalOnMissingBean
    public NameSpaceSubscribeEventService
        nameSpaceSubscribeEventService(SysCredentialNamespacesMapper sysCredentialNamespacesMapper) {
        return new NameSpaceSubscribeEventService(sysCredentialNamespacesMapper);
    }

    @Bean
    @ConditionalOnMissingBean(name = "credentialService")
    public ICredentialService credentialService(
        SysCredentialCreateUserNamePasswordEventPublish createUserNamePasswordEventPublish,
        SysCredentialUpdateUserNamePasswordEventPublish updateUserNamePasswordEventPublish,
        SysCredentialDeleteUserNamePasswordEventPublish deleteUserNamePasswordEventPublish,
        SysCredentialCreateSecretEventPublish createSecretEventPublish,
        SysCredentialUpdateSecretEventPublish updateSecretEventPublish,
        SysCredentialDeleteSecretEventPublish deleteSecretEventPublish,
        SysCredentialCreateSshEventPublish createSshEventPublish,
        SysCredentialUpdateSshEventPublish updateSshEventPublish,
        SysCredentialDeleteSshEventPublish deleteSshEventPublish,
        SysCredentialCreateTokenEventPublish createTokenEventPublish,
        SysCredentialUpdateTokenEventPublish updateTokenEventPublish,
        SysCredentialDeleteTokenEventPublish deleteTokenEventPublish,
        SysCredentialCreateTLSEventPublish createTLSEventPublish,
        SysCredentialUpdateTLSEventPublish updateTLSEventPublish,
        SysCredentialDeleteTLSEventPublish deleteTLSEventPublish,
        SysCredentialCreateOpaqueEventPublish createOpaqueEventPublish,
        SysCredentialDeleteOpaqueEventPublish deleteOpaqueEventPublish, SysCredentialTextMapper sysCredentialTextMapper,
        SysCredentialTokenMapper sysCredentialTokenMapper, SysCredentialsSshMapper sysCredentialsSshMapper,
        SysCredentialsUsernamePasswordMapper sysCredentialsUsernamePasswordMapper,
        SysCredentialCommonMapper sysCredentialCommonMapper, SysCredentialTlsMapper sysCredentialTlsMapper,
        SysCredentialOpaqueMapper sysCredentialOpaqueMapper,
        SysCredentialNamespacesMapper sysCredentialNamespacesMapper, QueryTemplate queryTemplate,
        StringEncryptor stringEncryptor) {
        return new CredentialService(createUserNamePasswordEventPublish, updateUserNamePasswordEventPublish,
            deleteUserNamePasswordEventPublish, createSecretEventPublish, updateSecretEventPublish,
            deleteSecretEventPublish, createSshEventPublish, updateSshEventPublish, deleteSshEventPublish,
            createTokenEventPublish, updateTokenEventPublish, deleteTokenEventPublish, createTLSEventPublish,
            updateTLSEventPublish, deleteTLSEventPublish, createOpaqueEventPublish, deleteOpaqueEventPublish,
            sysCredentialTextMapper, sysCredentialTokenMapper, sysCredentialsSshMapper,
            sysCredentialsUsernamePasswordMapper, sysCredentialCommonMapper, sysCredentialTlsMapper,
            sysCredentialOpaqueMapper, sysCredentialNamespacesMapper, queryTemplate, stringEncryptor);
    }
}
