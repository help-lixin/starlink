package help.lixin.starlink.plugin.credential.mq.provider.ssh;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.UPDATE_SSH_EVENT_TOPIC;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import help.lixin.starlink.plugin.credential.domain.ssh.SysSshCredentialUpdateEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/24 下午5:01
 * @Description
 */
public class SysCredentialUpdateSshEventPublish {
    private Logger logger = LoggerFactory.getLogger(SysCredentialUpdateSshEventPublish.class);

    private DomainEventPublisher domainEventPublisher;

    public void updateSsh(SysSshCredentialUpdateEvent sshCredentialUpdateEvent) {
        String aggregateType = UPDATE_SSH_EVENT_TOPIC;

        String credentialKey = sshCredentialUpdateEvent.getCredentialKey();

        // 发布事件.
        domainEventPublisher.publish(
            //
            aggregateType,
            //
            credentialKey,
            //
            Collections.singletonList(sshCredentialUpdateEvent));
        logger.info("发送更新ssh——凭证key为[{}]的消息成功", credentialKey);
    }

    public SysCredentialUpdateSshEventPublish(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }
}
