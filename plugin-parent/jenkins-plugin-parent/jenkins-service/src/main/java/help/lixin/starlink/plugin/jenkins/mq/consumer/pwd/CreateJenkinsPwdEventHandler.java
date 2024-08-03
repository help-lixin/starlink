package help.lixin.starlink.plugin.jenkins.mq.consumer.pwd;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.CREATE_PWD_EVENT_TOPIC;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.jenkins.dto.credential.UserNamePasswordDTO;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.credential.domain.pwd.SysUserNamePasswordCredentialCreateEvent;
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
public class CreateJenkinsPwdEventHandler {
    private Logger logger = LoggerFactory.getLogger(CreateJenkinsPwdEventHandler.class);

    private final AbstractServiceFactory jenkinsServiceFactory;
    private StringEncryptor stringEncryptor;

    public CreateJenkinsPwdEventHandler(AbstractServiceFactory jenkinsServiceFactory, StringEncryptor stringEncryptor) {
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.stringEncryptor = stringEncryptor;
    }

    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
            // 聚合根对象
            .forAggregateType(CREATE_PWD_EVENT_TOPIC)
            // 事件处理
            .onEvent(SysUserNamePasswordCredentialCreateEvent.class, this::createUserNamePassword)
            //
            .build();
    }

    public void createUserNamePassword(DomainEventEnvelope<SysUserNamePasswordCredentialCreateEvent> event) {
        SysUserNamePasswordCredentialCreateEvent userNamePasswordCredentialCreateEvent = event.getEvent();

        if (!userNamePasswordCredentialCreateEvent.getPluginCode().equals("jenkins")) {
            return;
        }

        String username = userNamePasswordCredentialCreateEvent.getUsername();
        String password = userNamePasswordCredentialCreateEvent.getPassword();
        String credentialKey = userNamePasswordCredentialCreateEvent.getCredentialKey();

        if (StringUtils.isBlank(username)) {
            throw new ServiceException("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            throw new ServiceException("密码不能为空");
        }
        if (StringUtils.isBlank(credentialKey)) {
            throw new ServiceException("凭证key不能为空");
        }

        logger.info("凭证key为[{}]的凭证开始消费", username);

        KeyManagerService keyManagerService = jenkinsServiceFactory
            .getInstance(userNamePasswordCredentialCreateEvent.getInstanceCode(), KeyManagerService.class);

        UserNamePasswordDTO userNamePasswordDTO = new UserNamePasswordDTO();
        userNamePasswordDTO.setUsername(username);
        try {
            userNamePasswordDTO.setPassword(stringEncryptor.decrypt(password));
        } catch (Exception e) {
            logger.error("无法解密，请确认密文是否正确:password={}", password);
            return;
        }

        userNamePasswordDTO.setDescription(userNamePasswordCredentialCreateEvent.getRemark());
        userNamePasswordDTO.setId(credentialKey);

        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(userNamePasswordDTO);
        } catch (JsonProcessingException e) {
            throw new ServiceException("JenkinsCredentialService 转换json过程发生异常" + e.getMessage());
        }

        keyManagerService.createCredentials(json, credentialKey, CredentialEnum.USERNAME_PASSWORD);
        logger.info("凭证key为[{}]的凭证创建成功", username);

    }
}
