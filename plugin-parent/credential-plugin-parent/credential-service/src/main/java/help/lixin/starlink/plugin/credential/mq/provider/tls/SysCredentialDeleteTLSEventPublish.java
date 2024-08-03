package help.lixin.starlink.plugin.credential.mq.provider.tls;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.DELETE_TLS_EVENT_TOPIC;

import java.util.Collections;

import help.lixin.starlink.plugin.credential.domain.tls.SysTLSCredentialDeleteEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.eventuate.tram.events.publisher.DomainEventPublisher;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/24 下午7:36
 * @Description
 */
public class SysCredentialDeleteTLSEventPublish {

    private Logger logger = LoggerFactory.getLogger(SysCredentialDeleteTLSEventPublish.class);

    private DomainEventPublisher domainEventPublisher;

    public void deleteEvent(SysTLSCredentialDeleteEvent sysTLSCredentialDeleteEvent) {
        String aggregateType = DELETE_TLS_EVENT_TOPIC;

        String credentialKey = sysTLSCredentialDeleteEvent.getCredentialKey();

        // 发布事件.
        domainEventPublisher.publish(
            //
            aggregateType,
            //
            credentialKey,
            //
            Collections.singletonList(sysTLSCredentialDeleteEvent));
        logger.info("发送删除TLS——凭证key为[{}]的消息成功", credentialKey);
    }

    public SysCredentialDeleteTLSEventPublish(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }
}
