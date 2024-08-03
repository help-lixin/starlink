package help.lixin.starlink.plugin.credential.mq.provider.opaque;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.CREATE_OPAQUE_EVENT_TOPIC;

import java.util.Collections;

import help.lixin.starlink.plugin.credential.domain.opaque.SysOpaqueCreateCredentialEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.eventuate.tram.events.publisher.DomainEventPublisher;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/24 下午7:36
 * @Description
 */
public class SysCredentialCreateOpaqueEventPublish {

    private Logger logger = LoggerFactory.getLogger(SysCredentialCreateOpaqueEventPublish.class);

    private DomainEventPublisher domainEventPublisher;

    public void createEvent(SysOpaqueCreateCredentialEvent sysOpaqueCreateCredentialEvent) {
        String aggregateType = CREATE_OPAQUE_EVENT_TOPIC;

        String credentialKey = sysOpaqueCreateCredentialEvent.getCredentialKey();

        // 发布事件.
        domainEventPublisher.publish(
                //
                aggregateType,
                //
                credentialKey,
                //
                Collections.singletonList(sysOpaqueCreateCredentialEvent));
        logger.info("发送新增Opaque——凭证key为[{}]的消息成功", credentialKey);
    }

    public SysCredentialCreateOpaqueEventPublish(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }
}
