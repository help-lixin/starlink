package help.lixin.starlink.plugin.credential.mq.provider.token;

import java.util.Collections;

import help.lixin.starlink.plugin.credential.constants.CredentialConstants;
import help.lixin.starlink.plugin.credential.domain.token.SysTokenCredentialCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.eventuate.tram.events.publisher.DomainEventPublisher;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/24 下午5:01
 * @Description
 */
public class SysCredentialCreateTokenEventPublish {
    private Logger logger = LoggerFactory.getLogger(SysCredentialCreateTokenEventPublish.class);

    private DomainEventPublisher domainEventPublisher;

    public void createToken(SysTokenCredentialCreateEvent sysTokenCredentialCreateEvent) {
        String aggregateType = CredentialConstants.CREATE_TOKEN_EVENT_TOPIC;

        String credentialKey = sysTokenCredentialCreateEvent.getCredentialKey();

        // 发布事件.
        domainEventPublisher.publish(
                //
                aggregateType,
                //
                credentialKey,
                //
                Collections.singletonList(sysTokenCredentialCreateEvent));
        logger.info("发送新增token——凭证key为[{}]的消息成功", credentialKey);
    }

    public SysCredentialCreateTokenEventPublish(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }
}
