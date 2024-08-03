package help.lixin.starlink.plugin.credential.mq.provider.token;

import java.util.Collections;

import help.lixin.starlink.plugin.credential.constants.CredentialConstants;
import help.lixin.starlink.plugin.credential.domain.token.SysTokenCredentialDeleteEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.eventuate.tram.events.publisher.DomainEventPublisher;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/24 下午7:36
 * @Description
 */
public class SysCredentialDeleteTokenEventPublish {

    private Logger logger = LoggerFactory.getLogger(SysCredentialDeleteTokenEventPublish.class);

    private DomainEventPublisher domainEventPublisher;

    public void deleteEvent(SysTokenCredentialDeleteEvent tokenCredentialDeleteEvent) {
        String aggregateType = CredentialConstants.DELETE_TOKEN_EVENT_TOPIC;

        String credentialKey = tokenCredentialDeleteEvent.getCredentialKey();

        // 发布事件.
        domainEventPublisher.publish(
            //
            aggregateType,
            //
            credentialKey,
            //
            Collections.singletonList(tokenCredentialDeleteEvent));
        logger.info("发送删除TOKEN——凭证key为[{}]的消息成功", credentialKey);
    }

    public SysCredentialDeleteTokenEventPublish(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }
}
