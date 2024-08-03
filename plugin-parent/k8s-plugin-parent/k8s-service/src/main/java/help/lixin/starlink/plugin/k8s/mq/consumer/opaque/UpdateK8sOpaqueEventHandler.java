package help.lixin.starlink.plugin.k8s.mq.consumer.opaque;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.UPDATE_OPAQUE_EVENT_TOPIC;

import java.util.Base64;
import java.util.Map;

import help.lixin.starlink.plugin.k8s.constant.SecretTypeConstant;
import help.lixin.starlink.plugin.k8s.dto.secret.SecretSaveDTO;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.credential.domain.opaque.SysOpaqueUpdateCredentialEvent;
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
public class UpdateK8sOpaqueEventHandler {
    private Logger logger = LoggerFactory.getLogger(UpdateK8sOpaqueEventHandler.class);

    private final AbstractServiceFactory k8sServiceFactory;
    private StringEncryptor stringEncryptor;

    public UpdateK8sOpaqueEventHandler(AbstractServiceFactory k8sServiceFactory, StringEncryptor stringEncryptor) {
        this.k8sServiceFactory = k8sServiceFactory;
        this.stringEncryptor = stringEncryptor;
    }

    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
            // 聚合根对象
            .forAggregateType(UPDATE_OPAQUE_EVENT_TOPIC)
            // 事件处理
            .onEvent(SysOpaqueUpdateCredentialEvent.class, this::updateEvent)
            //
            .build();
    }

    public void updateEvent(DomainEventEnvelope<SysOpaqueUpdateCredentialEvent> event) {
        SysOpaqueUpdateCredentialEvent sysOpaqueUpdateCredentialEvent = event.getEvent();

        if (!sysOpaqueUpdateCredentialEvent.getPluginCode().equals("k8s")) {
            return;
        }

        Map<String, String> dataMap = sysOpaqueUpdateCredentialEvent.getDataMap();
        String credentialKey = sysOpaqueUpdateCredentialEvent.getCredentialKey();
        String nameSpace = sysOpaqueUpdateCredentialEvent.getNameSpace();
        String instanceCode = sysOpaqueUpdateCredentialEvent.getInstanceCode();

        logger.info("k8s凭证key为[{}]的凭证开始消费", credentialKey);

        K8sSecretService k8sSecretService = k8sServiceFactory.getInstance(instanceCode, K8sSecretService.class);

        // 设置密钥所属数据
        Secret secret = new Secret();
        secret.setType(SecretTypeConstant.TLS);
        ObjectMeta objectMeta = new ObjectMeta();
        objectMeta.setName(credentialKey);
        objectMeta.setName(nameSpace);
        secret.setMetadata(objectMeta);

        SecretSaveDTO secretSaveDTO = new SecretSaveDTO();

        secretSaveDTO.setInstanceCode(instanceCode);
        secretSaveDTO.setNamespace(nameSpace);
        secretSaveDTO.setName(credentialKey);

        try {
            dataMap.forEach((k, v) -> {
                String decryptValue = stringEncryptor.decrypt(v);
                v = Base64.getEncoder().encodeToString(decryptValue.getBytes());
            });
            secret.setData(dataMap);
        } catch (Exception e) {
            logger.error("无法解密，请确认密文是否正确:[{}]", dataMap);
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
