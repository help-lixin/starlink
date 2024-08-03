package help.lixin.starlink.plugin.credential.mq.provider.ssh;

import help.lixin.starlink.plugin.credential.domain.ssh.SysSshCredentialCreateEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.CREATE_SSH_EVENT_TOPIC;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/24 下午5:01
 * @Description
 */
public class SysCredentialCreateSshEventPublish {
    private Logger logger = LoggerFactory.getLogger(SysCredentialCreateSshEventPublish.class);

    private DomainEventPublisher domainEventPublisher;

    public void createSsh(SysSshCredentialCreateEvent sshCredentialCreateEvent){
        String aggregateType = CREATE_SSH_EVENT_TOPIC;

        String credentialKey = sshCredentialCreateEvent.getCredentialKey();

        // 发布事件.
        domainEventPublisher.publish(
                //
                aggregateType,
                //
                credentialKey,
                //
                Collections.singletonList(sshCredentialCreateEvent));
        logger.info("发送新增ssh——凭证key为[{}]的消息成功", credentialKey);
    }

    public SysCredentialCreateSshEventPublish(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }
}
