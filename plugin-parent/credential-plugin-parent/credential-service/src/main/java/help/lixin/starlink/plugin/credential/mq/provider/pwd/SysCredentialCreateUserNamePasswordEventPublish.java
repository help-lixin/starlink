package help.lixin.starlink.plugin.credential.mq.provider.pwd;

import help.lixin.starlink.plugin.credential.domain.pwd.SysUserNamePasswordCredentialCreateEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.CREATE_PWD_EVENT_TOPIC;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/24 下午5:01
 * @Description
 */
public class SysCredentialCreateUserNamePasswordEventPublish {
    private Logger logger = LoggerFactory.getLogger(SysCredentialCreateUserNamePasswordEventPublish.class);

    private DomainEventPublisher domainEventPublisher;

    public void createUserNamePassword(SysUserNamePasswordCredentialCreateEvent sysUserNamePasswordCredentialCreateEvent){
        String aggregateType = CREATE_PWD_EVENT_TOPIC;

        String credentialKey = sysUserNamePasswordCredentialCreateEvent.getCredentialKey();

        // 发布事件.
        domainEventPublisher.publish(
                //
                aggregateType,
                //
                credentialKey,
                //
                Collections.singletonList(sysUserNamePasswordCredentialCreateEvent));
        logger.info("发送新增用户名密码凭证——凭证key为[{}]的消息成功", credentialKey);
    }

    public SysCredentialCreateUserNamePasswordEventPublish(DomainEventPublisher domainEventPublisher ) {
        this.domainEventPublisher = domainEventPublisher;
    }
}
