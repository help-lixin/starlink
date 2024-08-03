package help.lixin.starlink.plugin.k8s.mq.consumer.opaque;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.CREATE_OPAQUE_EVENT_TOPIC;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import help.lixin.starlink.plugin.k8s.constant.SecretTypeConstant;
import help.lixin.starlink.plugin.k8s.dto.secret.SecretSaveDTO;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.credential.domain.opaque.SysOpaqueCreateCredentialEvent;
import help.lixin.starlink.plugin.credential.event.opaque.OpaqueCredentialEvent;
import help.lixin.starlink.plugin.k8s.dto.secret.SecretOptionDTO;
import help.lixin.starlink.plugin.k8s.service.impl.K8sSecretService;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.Secret;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/22 上午10:17
 * @Description
 */
public class CreateK8sOpaqueEventHandler {
    private Logger logger = LoggerFactory.getLogger(CreateK8sOpaqueEventHandler.class);

    private final AbstractServiceFactory k8sServiceFactory;
    private StringEncryptor stringEncryptor;

    public CreateK8sOpaqueEventHandler(AbstractServiceFactory k8sServiceFactory, StringEncryptor stringEncryptor) {
        this.k8sServiceFactory = k8sServiceFactory;
        this.stringEncryptor = stringEncryptor;
    }

    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
            // 聚合根对象
            .forAggregateType(CREATE_OPAQUE_EVENT_TOPIC)
            // 事件处理
            .onEvent(SysOpaqueCreateCredentialEvent.class, this::createK8sOpaqueCredential)
            //
            .build();
    }

    public void createK8sOpaqueCredential(DomainEventEnvelope<SysOpaqueCreateCredentialEvent> event) {
        SysOpaqueCreateCredentialEvent sysOpaqueCreateCredentialEvent = event.getEvent();

        if (!sysOpaqueCreateCredentialEvent.getPluginCode().equals("k8s")) {
            return;
        }

        List<OpaqueCredentialEvent> dataList = sysOpaqueCreateCredentialEvent.getDataList();
        String credentialKey = sysOpaqueCreateCredentialEvent.getCredentialKey();
        String nameSpace = sysOpaqueCreateCredentialEvent.getNameSpace();
        String instanceCode = sysOpaqueCreateCredentialEvent.getInstanceCode();

        logger.info("k8s凭证key为[{}]的凭证开始消费", credentialKey);

        K8sSecretService k8sSecretService = k8sServiceFactory.getInstance(instanceCode, K8sSecretService.class);

        // 设置密钥所属数据
        Secret secret = new Secret();
        secret.setType(SecretTypeConstant.OPQ);
        ObjectMeta objectMeta = new ObjectMeta();
        objectMeta.setName(credentialKey);
        objectMeta.setNamespace(nameSpace);
        secret.setMetadata(objectMeta);

        SecretSaveDTO secretSaveDTO = new SecretSaveDTO();

        secretSaveDTO.setInstanceCode(instanceCode);
        secretSaveDTO.setNamespace(nameSpace);
        secretSaveDTO.setName(credentialKey);

        try {
            Map<String, String> secretMap = new HashMap<>();
            dataList.forEach(v -> {
                String decryptValue = stringEncryptor.decrypt(v.getValue());
                String value = Base64.getEncoder().encodeToString(decryptValue.getBytes());
                secretMap.put(v.getKey(), value);
            });
            secret.setData(secretMap);
        } catch (Exception e) {
            logger.error("无法解密，请确认密文是否正确:[{}]", dataList.toArray());
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(secret);
            secretSaveDTO.setJsonBody(json);
        } catch (JsonProcessingException e) {
            logger.error("k8s创建TLS时 转换json过程发生异常", e.getMessage());
        }

        SecretOptionDTO secretOptionDTO = k8sSecretService.querySecret(secretSaveDTO);
        if (secretOptionDTO != null) {
            k8sSecretService.deleteSecret(secretSaveDTO);
        }
        k8sSecretService.saveSecret(secretSaveDTO);
        logger.info("凭证key为[{}]的凭证创建成功", credentialKey);

    }
}
