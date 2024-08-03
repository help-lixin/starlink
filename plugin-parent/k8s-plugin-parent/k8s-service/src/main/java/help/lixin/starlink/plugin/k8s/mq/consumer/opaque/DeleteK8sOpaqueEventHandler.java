package help.lixin.starlink.plugin.k8s.mq.consumer.opaque;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.DELETE_OPAQUE_EVENT_TOPIC;

import help.lixin.starlink.plugin.k8s.constant.SecretTypeConstant;
import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import help.lixin.starlink.plugin.k8s.service.impl.K8sSecretService;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.credential.domain.opaque.SysOpaqueDeleteCredentialEvent;
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
public class DeleteK8sOpaqueEventHandler {
    private Logger logger = LoggerFactory.getLogger(DeleteK8sOpaqueEventHandler.class);

    private final AbstractServiceFactory k8sServiceFactory;
    private StringEncryptor stringEncryptor;

    public DeleteK8sOpaqueEventHandler(AbstractServiceFactory k8sServiceFactory, StringEncryptor stringEncryptor) {
        this.k8sServiceFactory = k8sServiceFactory;
        this.stringEncryptor = stringEncryptor;
    }

    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
            // 聚合根对象
            .forAggregateType(DELETE_OPAQUE_EVENT_TOPIC)
            // 事件处理
            .onEvent(SysOpaqueDeleteCredentialEvent.class, this::deleteEvent)
            //
            .build();
    }

    public void deleteEvent(DomainEventEnvelope<SysOpaqueDeleteCredentialEvent> event) {
        SysOpaqueDeleteCredentialEvent deleteCredentialEvent = event.getEvent();

        if (!deleteCredentialEvent.getPluginCode().equals("k8s")) {
            return;
        }

        String credentialKey = deleteCredentialEvent.getCredentialKey();
        String nameSpace = deleteCredentialEvent.getNameSpace();
        String instanceCode = deleteCredentialEvent.getInstanceCode();

        logger.info("k8s凭证key为[{}]的凭证开始消费", credentialKey);

        K8sSecretService k8sSecretService = k8sServiceFactory.getInstance(instanceCode, K8sSecretService.class);

        // 设置密钥所属数据
        Secret secret = new Secret();
        secret.setType(SecretTypeConstant.TLS);
        ObjectMeta objectMeta = new ObjectMeta();
        objectMeta.setName(credentialKey);
        objectMeta.setName(nameSpace);
        secret.setMetadata(objectMeta);

        K8sAppDTO appDTO = new K8sAppDTO();

        appDTO.setInstanceCode(instanceCode);
        appDTO.setNamespace(nameSpace);
        appDTO.setName(credentialKey);

        k8sSecretService.deleteSecret(appDTO);
        logger.info("k8s凭证key为[{}]的凭证删除成功", credentialKey);
    }
}
