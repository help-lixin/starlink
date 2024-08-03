package help.lixin.starlink.plugin.credential.mq.provider.ssh;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.DELETE_SSH_EVENT_TOPIC;

import java.util.Collections;

import help.lixin.starlink.plugin.credential.domain.ssh.SysSshCredentialDeleteEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.eventuate.tram.events.publisher.DomainEventPublisher;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/24 下午7:36
 * @Description
 */
public class SysCredentialDeleteSshEventPublish {

    private Logger logger = LoggerFactory.getLogger(SysCredentialDeleteSshEventPublish.class);

    private DomainEventPublisher domainEventPublisher;

    public void deleteEvent(SysSshCredentialDeleteEvent sshCredentialDeleteEvent) {
        String aggregateType = DELETE_SSH_EVENT_TOPIC;

        String credentialKey = sshCredentialDeleteEvent.getCredentialKey();

        // 发布事件.
        domainEventPublisher.publish(
            //
            aggregateType,
            //
            credentialKey,
            //
            Collections.singletonList(sshCredentialDeleteEvent));
        logger.info("发送删除SSH——凭证key为[{}]的消息成功", credentialKey);
    }

    public SysCredentialDeleteSshEventPublish(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }
}
