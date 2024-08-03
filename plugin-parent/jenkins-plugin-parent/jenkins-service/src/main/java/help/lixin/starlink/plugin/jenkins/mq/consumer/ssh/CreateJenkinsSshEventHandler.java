package help.lixin.starlink.plugin.jenkins.mq.consumer.ssh;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.CREATE_SSH_EVENT_TOPIC;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.jenkins.dto.credential.PrivateKeySourceDTO;
import help.lixin.starlink.plugin.jenkins.dto.credential.SSHDTO;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.credential.domain.ssh.SysSshCredentialCreateEvent;
import help.lixin.starlink.plugin.jenkins.api.model.CredentialEnum;
import help.lixin.starlink.plugin.jenkins.api.service.impl.KeyManagerService;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/22 上午10:17
 * @Description
 */
public class CreateJenkinsSshEventHandler {
    private Logger logger = LoggerFactory.getLogger(CreateJenkinsSshEventHandler.class);

    private final AbstractServiceFactory jenkinsServiceFactory;
    private StringEncryptor stringEncryptor;

    public CreateJenkinsSshEventHandler(AbstractServiceFactory jenkinsServiceFactory, StringEncryptor stringEncryptor) {
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.stringEncryptor = stringEncryptor;
    }

    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
            // 聚合根对象
            .forAggregateType(CREATE_SSH_EVENT_TOPIC)
            // 事件处理
            .onEvent(SysSshCredentialCreateEvent.class, this::createSsh)
            //
            .build();
    }

    // TODO lixin:SSH凭证创建后，凭证中的privateKey无法使用
    public void createSsh(DomainEventEnvelope<SysSshCredentialCreateEvent> event) {
        SysSshCredentialCreateEvent sshCredentialCreateEvent = event.getEvent();
        String credentialKey = sshCredentialCreateEvent.getCredentialKey();
        String privatekey = sshCredentialCreateEvent.getPrivatekey();

        if (!sshCredentialCreateEvent.getPluginCode().equals("jenkins")) {
            return;
        }

        if (StringUtils.isBlank(privatekey)) {
            throw new ServiceException("私钥不能为空");
        }

        if (StringUtils.isBlank(credentialKey)) {
            throw new ServiceException("凭证key不能为空");
        }

        KeyManagerService keyManagerService =
            jenkinsServiceFactory.getInstance(sshCredentialCreateEvent.getInstanceCode(), KeyManagerService.class);

        if (keyManagerService.checkCredentialsId(credentialKey, CredentialEnum.USERNAME_PASSWORD)) {
            logger.error("该凭证[{}]已存在，不能进行插入操作", credentialKey);
        }

        PrivateKeySourceDTO privateKeySourceDTO = new PrivateKeySourceDTO();
        SSHDTO sshdto = new SSHDTO();
        sshdto.setDescription(sshCredentialCreateEvent.getRemark());

        String passphrase = sshCredentialCreateEvent.getPassphrase();
        try {
            if (StringUtils.isBlank(passphrase)) {
                sshdto.setPassphrase("");
            } else {
                sshdto.setPassphrase(stringEncryptor.decrypt(passphrase));
            }

            privateKeySourceDTO.setPrivateKey(stringEncryptor.decrypt(privatekey));
        } catch (Exception e) {
            logger.error("无法解密，请确认密文是否正确：passphrase={},privatekey={}" + passphrase, privatekey);
            return;
        }

        sshdto.setPrivateKeySource(privateKeySourceDTO);
        sshdto.setUsername(sshCredentialCreateEvent.getUsername());
        sshdto.setId(credentialKey);

        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(sshdto);
        } catch (JsonProcessingException e) {
            throw new ServiceException("JenkinsCredentialService 转换json过程发生异常" + e.getMessage());
        }

        keyManagerService.createCredentials(json, credentialKey, CredentialEnum.SSH);
    }
}
