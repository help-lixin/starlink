package help.lixin.starlink.plugin.credential.mq.provider.tls;

import java.util.Collections;

import help.lixin.starlink.plugin.credential.constants.CredentialConstants;
import help.lixin.starlink.plugin.credential.domain.tls.SysTLSCredentialCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.eventuate.tram.events.publisher.DomainEventPublisher;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/24 下午7:36
 * @Description
 */
public class SysCredentialCreateTLSEventPublish {

    private Logger logger = LoggerFactory.getLogger(SysCredentialCreateTLSEventPublish.class);

    private DomainEventPublisher domainEventPublisher;

    public void createEvent(SysTLSCredentialCreateEvent credentialSpecialCreateEvent) {
        String aggregateType = CredentialConstants.CREATE_TLS_EVENT_TOPIC;

        String credentialKey = credentialSpecialCreateEvent.getCredentialKey();

        // 发布事件.
        domainEventPublisher.publish(
                //
                aggregateType,
                //
                credentialKey,
                //
                Collections.singletonList(credentialSpecialCreateEvent));
        logger.info("发送新增TLS——凭证key为[{}]的消息成功", credentialKey);
    }

    public SysCredentialCreateTLSEventPublish(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }
}
