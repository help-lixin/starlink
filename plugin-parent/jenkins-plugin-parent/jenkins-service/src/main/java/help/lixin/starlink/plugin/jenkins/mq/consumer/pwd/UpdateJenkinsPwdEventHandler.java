package help.lixin.starlink.plugin.jenkins.mq.consumer.pwd;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.UPDATE_PWD_EVENT_TOPIC;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.jenkins.dto.credential.UserNamePasswordDTO;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.credential.domain.pwd.SysUserNamePasswordCredentialUpdateEvent;
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
public class UpdateJenkinsPwdEventHandler {
    private Logger logger = LoggerFactory.getLogger(UpdateJenkinsPwdEventHandler.class);

    private final AbstractServiceFactory jenkinsServiceFactory;
    private StringEncryptor stringEncryptor;

    public UpdateJenkinsPwdEventHandler(AbstractServiceFactory jenkinsServiceFactory, StringEncryptor stringEncryptor) {
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.stringEncryptor = stringEncryptor;
    }

    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
            // 聚合根对象
            .forAggregateType(UPDATE_PWD_EVENT_TOPIC)
            // 事件处理
            .onEvent(SysUserNamePasswordCredentialUpdateEvent.class, this::updatePwd)
            //
            .build();
    }

    public void updatePwd(DomainEventEnvelope<SysUserNamePasswordCredentialUpdateEvent> event) {
        SysUserNamePasswordCredentialUpdateEvent sysUserNamePasswordCredentialUpdateEvent = event.getEvent();

        if (!sysUserNamePasswordCredentialUpdateEvent.getPluginCode().equals("jenkins")) {
            return;
        }

        String username = sysUserNamePasswordCredentialUpdateEvent.getUsername();
        String password = sysUserNamePasswordCredentialUpdateEvent.getPassword();
        String credentialKey = sysUserNamePasswordCredentialUpdateEvent.getCredentialKey();

        if (StringUtils.isBlank(username)) {
            throw new ServiceException("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            throw new ServiceException("密码不能为空");
        }
        if (StringUtils.isBlank(credentialKey)) {
            throw new ServiceException("凭证key不能为空");
        }

        KeyManagerService keyManagerService = jenkinsServiceFactory
            .getInstance(sysUserNamePasswordCredentialUpdateEvent.getInstanceCode(), KeyManagerService.class);

        if (keyManagerService.checkCredentialsId(credentialKey, CredentialEnum.USERNAME_PASSWORD)) {
            logger.error("该凭证[{}]不存在，不能进行更新操作", credentialKey);
        }

        UserNamePasswordDTO userNamePasswordDTO = new UserNamePasswordDTO();
        if (StringUtils.isNotBlank(password)) {
            String pwd = null;
            try {
                pwd = stringEncryptor.decrypt(password);
            } catch (Exception e) {
                logger.error("无法解密，请确认密文是否正确:password={}", password);
                return;
            }
            userNamePasswordDTO.setPassword(pwd);
        }
        userNamePasswordDTO.setUsername(username);
        userNamePasswordDTO.setDescription(sysUserNamePasswordCredentialUpdateEvent.getRemark());
        userNamePasswordDTO.setId(credentialKey);

        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(userNamePasswordDTO);
        } catch (JsonProcessingException e) {
            throw new ServiceException("JenkinsCredentialService 转换json过程发生异常" + e.getMessage());
        }

        keyManagerService.updateCredentials(json, credentialKey);
    }
}
