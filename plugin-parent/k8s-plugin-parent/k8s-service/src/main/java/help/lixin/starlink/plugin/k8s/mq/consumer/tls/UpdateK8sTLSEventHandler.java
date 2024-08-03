package help.lixin.starlink.plugin.k8s.mq.consumer.tls;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.UPDATE_TLS_EVENT_TOPIC;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import help.lixin.starlink.plugin.k8s.constant.SecretTypeConstant;
import help.lixin.starlink.plugin.k8s.dto.secret.SecretSaveDTO;
import help.lixin.starlink.plugin.k8s.service.impl.K8sSecretService;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.credential.domain.tls.SysTLSCredentialUpdateEvent;
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
public class UpdateK8sTLSEventHandler {
    private Logger logger = LoggerFactory.getLogger(UpdateK8sTLSEventHandler.class);

    private final AbstractServiceFactory k8sServiceFactory;
    private StringEncryptor stringEncryptor;

    public UpdateK8sTLSEventHandler(AbstractServiceFactory k8sServiceFactory, StringEncryptor stringEncryptor) {
        this.k8sServiceFactory = k8sServiceFactory;
        this.stringEncryptor = stringEncryptor;
    }

    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
            // 聚合根对象
            .forAggregateType(UPDATE_TLS_EVENT_TOPIC)
            // 事件处理
            .onEvent(SysTLSCredentialUpdateEvent.class, this::updateEvent)
            //
            .build();
    }

    public void updateEvent(DomainEventEnvelope<SysTLSCredentialUpdateEvent> event) {
        SysTLSCredentialUpdateEvent specialUpdateEvent = event.getEvent();

        if (!specialUpdateEvent.getPluginCode().equals("k8s")) {
            return;
        }

        String certificate = specialUpdateEvent.getCertificate();
        String privateKey = specialUpdateEvent.getPrivateKey();
        String credentialKey = specialUpdateEvent.getCredentialKey();
        String instanceCode = specialUpdateEvent.getInstanceCode();
        String nameSpace = specialUpdateEvent.getNameSpace();

        logger.info("k8s凭证key为[{}]的凭证开始消费", credentialKey);

        K8sSecretService k8sSecretService = k8sServiceFactory.getInstance(instanceCode, K8sSecretService.class);

        // 设置密钥所属数据
        Secret secret = new Secret();
        secret.setType(SecretTypeConstant.TLS);
        ObjectMeta objectMeta = new ObjectMeta();
        objectMeta.setName(credentialKey);
        objectMeta.setNamespace(nameSpace);
        secret.setMetadata(objectMeta);

        SecretSaveDTO secretSaveDTO = new SecretSaveDTO();

        secretSaveDTO.setInstanceCode(instanceCode);
        secretSaveDTO.setNamespace(nameSpace);
        secretSaveDTO.setName(credentialKey);

        try {
            String decryptCertificate = stringEncryptor.decrypt(certificate);
            String decryptPrivateKey = stringEncryptor.decrypt(privateKey);

            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("tls.crt", Base64.getEncoder().encodeToString(decryptCertificate.getBytes()));
            dataMap.put("tls.key", Base64.getEncoder().encodeToString(decryptPrivateKey.getBytes()));
            secret.setData(dataMap);
        } catch (Exception e) {
            logger.error("无法解密，请确认密文是否正确:certificate={},privateKey={}", certificate, privateKey);
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(secret);
            secretSaveDTO.setJsonBody(json);
        } catch (JsonProcessingException e) {
            logger.error("k8s更新TLS时 转换json过程发生异常", e.getMessage());
        }

        k8sSecretService.saveSecret(secretSaveDTO);
        logger.info("k8s凭证key为[{}]的凭证更新成功", credentialKey);
    }
}
