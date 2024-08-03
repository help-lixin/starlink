package help.lixin.starlink.plugin.credential.mq.provider.tls;

import java.util.Collections;

import help.lixin.starlink.plugin.credential.constants.CredentialConstants;
import help.lixin.starlink.plugin.credential.domain.tls.SysTLSCredentialUpdateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.eventuate.tram.events.publisher.DomainEventPublisher;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/24 下午7:36
 * @Description
 */
public class SysCredentialUpdateTLSEventPublish {

    private Logger logger = LoggerFactory.getLogger(SysCredentialUpdateTLSEventPublish.class);

    private DomainEventPublisher domainEventPublisher;

    public void updateEvent(SysTLSCredentialUpdateEvent sysTLSCredentialUpdateEvent) {
        String aggregateType = CredentialConstants.UPDATE_TLS_EVENT_TOPIC;

        String credentialKey = sysTLSCredentialUpdateEvent.getCredentialKey();

        // 发布事件.
        domainEventPublisher.publish(
                //
                aggregateType,
                //
                credentialKey,
                //
            Collections.singletonList(sysTLSCredentialUpdateEvent));
        logger.info("发送更新TLS——凭证key为[{}]的消息成功", credentialKey);
    }

    public SysCredentialUpdateTLSEventPublish(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }
}
