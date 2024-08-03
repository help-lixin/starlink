package help.lixin.starlink.plugin.credential.mq.provider.opaque;

import java.util.Collections;

import help.lixin.starlink.plugin.credential.constants.CredentialConstants;
import help.lixin.starlink.plugin.credential.domain.opaque.SysOpaqueDeleteCredentialEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.eventuate.tram.events.publisher.DomainEventPublisher;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/24 下午7:36
 * @Description
 */
public class SysCredentialDeleteOpaqueEventPublish {

    private Logger logger = LoggerFactory.getLogger(SysCredentialDeleteOpaqueEventPublish.class);

    private DomainEventPublisher domainEventPublisher;

    public void deleteEvent(SysOpaqueDeleteCredentialEvent sysOpaqueDeleteCredentialEvent) {
        String aggregateType = CredentialConstants.DELETE_OPAQUE_EVENT_TOPIC;

        String credentialKey = sysOpaqueDeleteCredentialEvent.getCredentialKey();

        // 发布事件.
        domainEventPublisher.publish(
            //
            aggregateType,
            //
            credentialKey,
            //
            Collections.singletonList(sysOpaqueDeleteCredentialEvent));
        logger.info("发送更新Opaque——凭证key为[{}]的消息成功", credentialKey);
    }

    public SysCredentialDeleteOpaqueEventPublish(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }
}
