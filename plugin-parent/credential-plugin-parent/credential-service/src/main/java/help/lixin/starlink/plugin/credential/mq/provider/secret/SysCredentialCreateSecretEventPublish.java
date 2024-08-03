package help.lixin.starlink.plugin.credential.mq.provider.secret;

import help.lixin.starlink.plugin.credential.domain.secret.SysSecretCredentialCreateEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.CREATE_SECRET_EVENT_TOPIC;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/24 下午5:01
 * @Description
 */
public class SysCredentialCreateSecretEventPublish {
    private Logger logger = LoggerFactory.getLogger(SysCredentialCreateSecretEventPublish.class);

    private DomainEventPublisher domainEventPublisher;

    public void createSecret(SysSecretCredentialCreateEvent secretCredentialCreateEvent){
        String aggregateType = CREATE_SECRET_EVENT_TOPIC;

        String secret = secretCredentialCreateEvent.getSecret();

        // 发布事件.
        domainEventPublisher.publish(
                //
                aggregateType,
                //
                secret,
                //
                Collections.singletonList(secretCredentialCreateEvent));
        logger.info("发送新增密钥凭证[{}]的消息成功", secret);
    }

    public SysCredentialCreateSecretEventPublish(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }
}
