package help.lixin.starlink.plugin.k8s.mq.consumer.pwd;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.UPDATE_PWD_EVENT_TOPIC;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import help.lixin.starlink.plugin.k8s.dto.secret.SecretAuthDTO;
import help.lixin.starlink.plugin.k8s.dto.secret.SecretSaveDTO;
import help.lixin.starlink.plugin.k8s.dto.secret.SecretUserNamePasswordDTO;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.credential.domain.pwd.SysUserNamePasswordCredentialUpdateEvent;
import help.lixin.starlink.plugin.k8s.service.impl.K8sSecretService;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.Secret;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/27 下午5:15
 * @Description
 */
public class UpdateK8sPWDEventHandler {
    private Logger logger = LoggerFactory.getLogger(UpdateK8sPWDEventHandler.class);

    private final AbstractServiceFactory k8sServiceFactory;
    private StringEncryptor stringEncryptor;

    public UpdateK8sPWDEventHandler(AbstractServiceFactory k8sServiceFactory, StringEncryptor stringEncryptor) {
        this.k8sServiceFactory = k8sServiceFactory;
        this.stringEncryptor = stringEncryptor;
    }

    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
            // 聚合根对象
            .forAggregateType(UPDATE_PWD_EVENT_TOPIC)
            // 事件处理
            .onEvent(SysUserNamePasswordCredentialUpdateEvent.class, this::updatePWDEvent)
            //
            .build();
    }

    public void updatePWDEvent(DomainEventEnvelope<SysUserNamePasswordCredentialUpdateEvent> event) {
        SysUserNamePasswordCredentialUpdateEvent sysUserNamePasswordCredentialUpdateEvent = event.getEvent();

        String username = sysUserNamePasswordCredentialUpdateEvent.getUsername();
        String password = sysUserNamePasswordCredentialUpdateEvent.getPassword();
        String credentialKey = sysUserNamePasswordCredentialUpdateEvent.getCredentialKey();
        String instanceCode = sysUserNamePasswordCredentialUpdateEvent.getInstanceCode();
        String imgDomain = sysUserNamePasswordCredentialUpdateEvent.getImgDomain();

        logger.info("k8s凭证key为[{}]的凭证开始消费", credentialKey);

        K8sSecretService k8sSecretService = k8sServiceFactory.getInstance(instanceCode, K8sSecretService.class);

        SecretSaveDTO secretSaveDTO = new SecretSaveDTO();

        secretSaveDTO.setInstanceCode(instanceCode);
        secretSaveDTO.setNamespace(sysUserNamePasswordCredentialUpdateEvent.getNameSpace());
        secretSaveDTO.setName(credentialKey);

        Secret secret = new Secret();
        ObjectMeta objectMeta = new ObjectMeta();

        objectMeta.setNamespace(sysUserNamePasswordCredentialUpdateEvent.getNameSpace());
        objectMeta.setName(credentialKey);
        secret.setMetadata(objectMeta);
        String decryptPassword;
        try {
            decryptPassword = stringEncryptor.decrypt(password);
        } catch (Exception e) {
            logger.error("无法解密，请确认密文是否正确:password={}", password);
            return;
        }

        Map<String, String> dataMap = new HashMap<>();
        if (imgDomain == null) {
            secret.setType("kubernetes.io/basic-auth");
            dataMap.put("username", Base64.getEncoder().encodeToString(username.getBytes()));
            dataMap.put("password", Base64.getEncoder().encodeToString(decryptPassword.getBytes()));
            secret.setData(dataMap);
        } else {
            secret.setType("kubernetes.io/dockerconfigjson");
            SecretUserNamePasswordDTO userNamePasswordDTO = new SecretUserNamePasswordDTO();
            userNamePasswordDTO.setUsername(username);
            userNamePasswordDTO.setPassword(decryptPassword);

            Map<String, SecretUserNamePasswordDTO> userNamePasswordDTOMap = new HashMap<>();
            userNamePasswordDTOMap.put(imgDomain, userNamePasswordDTO);

            try {
                SecretAuthDTO secretAuthDTO = new SecretAuthDTO();
                secretAuthDTO.setAuths(userNamePasswordDTOMap);
                ObjectMapper objectMapper = new ObjectMapper();
                String metaData = objectMapper.writeValueAsString(secretAuthDTO);
                dataMap.put(".dockerconfigjson", Base64.getEncoder().encodeToString(metaData.getBytes()));
                secret.setData(dataMap);
            } catch (JsonProcessingException e) {
                logger.error("k8s更新账号名密码时 转换map出现异常:[{}]", userNamePasswordDTOMap);
                return;
            }

        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(secret);
            secretSaveDTO.setJsonBody(json);
        } catch (JsonProcessingException e) {
            logger.error("k8s更新账号名密码时 转换json过程发生异常" + e.getMessage());
            return;
        }

        k8sSecretService.saveSecret(secretSaveDTO);
        logger.info("k8s凭证key为[{}]的凭证更新成功", credentialKey);
    }
}
