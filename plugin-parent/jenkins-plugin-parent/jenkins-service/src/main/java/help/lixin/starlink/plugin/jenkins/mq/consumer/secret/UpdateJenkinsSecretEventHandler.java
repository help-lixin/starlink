package help.lixin.starlink.plugin.jenkins.mq.consumer.secret;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.UPDATE_SECRET_EVENT_TOPIC;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.jenkins.dto.credential.SecretDTO;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.credential.domain.secret.SysSecretCredentialUpdateEvent;
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
public class UpdateJenkinsSecretEventHandler {
    private Logger logger = LoggerFactory.getLogger(UpdateJenkinsSecretEventHandler.class);

    private final AbstractServiceFactory jenkinsServiceFactory;
    private StringEncryptor stringEncryptor;

    public UpdateJenkinsSecretEventHandler(AbstractServiceFactory jenkinsServiceFactory,
        StringEncryptor stringEncryptor) {
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.stringEncryptor = stringEncryptor;
    }

    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
            // 聚合根对象
            .forAggregateType(UPDATE_SECRET_EVENT_TOPIC)
            // 事件处理
            .onEvent(SysSecretCredentialUpdateEvent.class, this::updateSecret)
            //
            .build();
    }

    public void updateSecret(DomainEventEnvelope<SysSecretCredentialUpdateEvent> event) {
        SysSecretCredentialUpdateEvent sysSecretCredentialCreateEvent = event.getEvent();

        if (!sysSecretCredentialCreateEvent.getPluginCode().equals("jenkins")) {
            return;
        }

        String credentialKey = sysSecretCredentialCreateEvent.getCredentialKey();
        String secret = sysSecretCredentialCreateEvent.getSecret();
        if (StringUtils.isBlank(credentialKey)) {
            throw new ServiceException("凭证key不能为空");
        }
        if (StringUtils.isBlank(secret)) {
            throw new ServiceException("密文不能为空");
        }

        KeyManagerService keyManagerService = jenkinsServiceFactory
            .getInstance(sysSecretCredentialCreateEvent.getInstanceCode(), KeyManagerService.class);

        if (keyManagerService.checkCredentialsId(credentialKey, CredentialEnum.USERNAME_PASSWORD)) {
            logger.error("该凭证[{}]不存在，不能进行更新操作", credentialKey);
        }

        String credentialId = sysSecretCredentialCreateEvent.getCredentialKey();

        SecretDTO secretDTO = new SecretDTO();
        String decryptSecret = stringEncryptor.decrypt(sysSecretCredentialCreateEvent.getSecret());
        try {
            secretDTO.setSecret(decryptSecret);
        } catch (Exception e) {
            logger.error("无法解密，请确认密文是否正确:secret={}", secret);
            return;
        }
        secretDTO.setDescription(sysSecretCredentialCreateEvent.getRemark());
        secretDTO.setId(credentialId);

        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(secretDTO);
        } catch (JsonProcessingException e) {
            throw new ServiceException("JenkinsCredentialService 转换json过程发生异常" + e.getMessage());
        }

        keyManagerService.updateCredentials(json, credentialId);
    }
}
