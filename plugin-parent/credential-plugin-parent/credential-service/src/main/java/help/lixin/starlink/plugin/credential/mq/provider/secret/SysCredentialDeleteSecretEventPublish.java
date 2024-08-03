package help.lixin.starlink.plugin.credential.mq.provider.secret;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.DELETE_SECRET_EVENT_TOPIC;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import help.lixin.starlink.plugin.credential.domain.secret.SysSecretCredentialDeleteEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/24 下午7:36
 * @Description
 */
public class SysCredentialDeleteSecretEventPublish {

    private Logger logger = LoggerFactory.getLogger(SysCredentialDeleteSecretEventPublish.class);

    private DomainEventPublisher domainEventPublisher;

    public void deleteEvent(SysSecretCredentialDeleteEvent deleteEvent) {
        String aggregateType = DELETE_SECRET_EVENT_TOPIC;

        String credentialKey = deleteEvent.getCredentialKey();

        // 发布事件.
        domainEventPublisher.publish(
            //
            aggregateType,
            //
            credentialKey,
            //
            Collections.singletonList(deleteEvent));
        logger.info("发送删除密钥——凭证key为[{}]的消息成功", credentialKey);
    }

    public SysCredentialDeleteSecretEventPublish(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }
}
