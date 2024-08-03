package help.lixin.starlink.plugin.jenkins.mq.consumer.token;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.UPDATE_TOKEN_EVENT_TOPIC;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.jenkins.dto.credential.TokenDTO;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.credential.domain.token.SysTokenCredentialCreateEvent;
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
public class UpdateJenkinsTokenEventHandler {
    private Logger logger = LoggerFactory.getLogger(UpdateJenkinsTokenEventHandler.class);

    private final AbstractServiceFactory jenkinsServiceFactory;
    private StringEncryptor stringEncryptor;

    public UpdateJenkinsTokenEventHandler(AbstractServiceFactory jenkinsServiceFactory,
        StringEncryptor stringEncryptor) {
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.stringEncryptor = stringEncryptor;
    }

    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
            // 聚合根对象
            .forAggregateType(UPDATE_TOKEN_EVENT_TOPIC)
            // 事件处理
            .onEvent(SysTokenCredentialCreateEvent.class, this::updateToken)
            //
            .build();
    }

    public void updateToken(DomainEventEnvelope<SysTokenCredentialCreateEvent> event) {
        SysTokenCredentialCreateEvent sysTokenCredentialCreateEvent = event.getEvent();

        if (!sysTokenCredentialCreateEvent.getPluginCode().equals("jenkins")) {
            return;
        }

        String credentialKey = sysTokenCredentialCreateEvent.getCredentialKey();
        String token = sysTokenCredentialCreateEvent.getToken();

        if (StringUtils.isBlank(credentialKey)) {
            throw new ServiceException("凭证key不能为空");
        }

        if (StringUtils.isBlank(token)) {
            throw new ServiceException("token不能为空");
        }

        KeyManagerService keyManagerService =
            jenkinsServiceFactory.getInstance(sysTokenCredentialCreateEvent.getInstanceCode(), KeyManagerService.class);

        if (keyManagerService.checkCredentialsId(credentialKey, CredentialEnum.USERNAME_PASSWORD)) {
            logger.error("该凭证[{}]不存在，不能进行更新操作", credentialKey);
        }

        TokenDTO tokenDTO = new TokenDTO();

        tokenDTO.setId(credentialKey);
        String decryptToken = null;
        try {
            decryptToken = stringEncryptor.decrypt(token);
        } catch (Exception e) {
            logger.error("无法解密，请确认密文是否正确:token={}", token);
            return;
        }
        tokenDTO.setApiToken(decryptToken);
        tokenDTO.setDescription(sysTokenCredentialCreateEvent.getRemark());

        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(tokenDTO);
        } catch (JsonProcessingException e) {
            throw new ServiceException("JenkinsCredentialService 转换json过程发生异常" + e.getMessage());
        }

        keyManagerService.updateCredentials(json, credentialKey);
    }
}
