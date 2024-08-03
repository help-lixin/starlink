package help.lixin.starlink.plugin.credential.mq.provider.pwd;

import help.lixin.starlink.plugin.credential.domain.pwd.SysUserNamePasswordCredentialUpdateEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.UPDATE_PWD_EVENT_TOPIC;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/24 下午5:01
 * @Description
 */
public class SysCredentialUpdateUserNamePasswordEventPublish {
    private Logger logger = LoggerFactory.getLogger(SysCredentialUpdateUserNamePasswordEventPublish.class);

    private DomainEventPublisher domainEventPublisher;

    public void updateUserNamePassword(SysUserNamePasswordCredentialUpdateEvent userNamePasswordCredentialUpdateEvent){
        String aggregateType = UPDATE_PWD_EVENT_TOPIC;

        String userName = userNamePasswordCredentialUpdateEvent.getUsername();

        // 发布事件.
        domainEventPublisher.publish(
                //
                aggregateType,
                //
                userName,
                //
                Collections.singletonList(userNamePasswordCredentialUpdateEvent));
        logger.info("发送更新用户名密码凭证——用户名为[{}]的消息成功", userName);
    }

    public SysCredentialUpdateUserNamePasswordEventPublish(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }
}
