package help.lixin.starlink.plugin.credential.mq.provider.secret;

import help.lixin.starlink.plugin.credential.domain.secret.SysSecretCredentialUpdateEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.UPDATE_SECRET_EVENT_TOPIC;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/24 下午5:01
 * @Description
 */
public class SysCredentialUpdateSecretEventPublish {
    private Logger logger = LoggerFactory.getLogger(SysCredentialUpdateSecretEventPublish.class);

    private DomainEventPublisher domainEventPublisher;

    public void updateSecret(SysSecretCredentialUpdateEvent secretCredentialUpdateEvent){
        String aggregateType = UPDATE_SECRET_EVENT_TOPIC;

        String secret = secretCredentialUpdateEvent.getSecret();

        // 发布事件.
        domainEventPublisher.publish(
                //
                aggregateType,
                //
                secret,
                //
                Collections.singletonList(secretCredentialUpdateEvent));
        logger.info("发送更新密钥凭证[{}]的消息成功", secret);
    }

    public SysCredentialUpdateSecretEventPublish(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }
}
