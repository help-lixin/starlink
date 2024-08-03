package help.lixin.starlink.plugin.credential.mq.provider.pwd;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.DELETE_PWD_EVENT_TOPIC;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import help.lixin.starlink.plugin.credential.domain.pwd.SysUserNamePasswordCredentialDeleteEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/24 下午7:36
 * @Description
 */
public class SysCredentialDeleteUserNamePasswordEventPublish {

    private Logger logger = LoggerFactory.getLogger(SysCredentialDeleteUserNamePasswordEventPublish.class);

    private DomainEventPublisher domainEventPublisher;

    public void deleteEvent(SysUserNamePasswordCredentialDeleteEvent sysUserNamePasswordCredentialDeleteEvent) {
        String aggregateType = DELETE_PWD_EVENT_TOPIC;

        String credentialKey = sysUserNamePasswordCredentialDeleteEvent.getCredentialKey();

        // 发布事件.
        domainEventPublisher.publish(
            //
            aggregateType,
            //
            credentialKey,
            //
            Collections.singletonList(sysUserNamePasswordCredentialDeleteEvent));
        logger.info("发送删除用户名密码——凭证key为[{}]的消息成功", credentialKey);
    }

    public SysCredentialDeleteUserNamePasswordEventPublish(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }
}
