package help.lixin.starlink.plugin.gitlab.mq.consumer;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.event.user.SystemUserDeleteEvent;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.gitlab.domain.GitlabUser;
import help.lixin.starlink.plugin.gitlab.service.IUserService;
import help.lixin.topic.SystemUserTopicConstants;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/2 下午4:23
 * @Description
 */
public class SyncSystemDeleteUserHandler {

    private Logger logger = LoggerFactory.getLogger(SyncSystemDeleteUserHandler.class);

    private IUserService userService;
    private AbstractServiceFactory gitlabServiceFactory;

    public SyncSystemDeleteUserHandler(IUserService userService, AbstractServiceFactory gitlabServiceFactory) {
        this.userService = userService;
        this.gitlabServiceFactory = gitlabServiceFactory;
    }

    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
                // 聚合根对象:Account
                .forAggregateType(SystemUserTopicConstants.DELETE_USER_EVENT_TOPIC)
                // 事件处理
                .onEvent(SystemUserDeleteEvent.class, this::syncSystemDeleteUser)
                //
                .build();
    }

    @Transactional(rollbackFor = {ServiceException.class})
    public void syncSystemDeleteUser(DomainEventEnvelope<SystemUserDeleteEvent> event){
        SystemUserDeleteEvent systemUserDeleteEvent = event.getEvent();
        //获取插件列表
        Set<String> contextNames = gitlabServiceFactory.getPluginNamedContextFactory().getContextNames();
        if (CollectionUtils.isEmpty(contextNames)) {
            return;
        }

        //遍历插件列表
        contextNames.forEach(instanceCode->{
            logger.info("开始进行gitlab删除用户[{}]实例的消费", instanceCode);
            Long sysUserId = systemUserDeleteEvent.getSysUserId();

            try {

                GitlabUser gitlabUser = userService.queryUserBySysUserId(sysUserId);

                if (gitlabUser == null){
                    logger.error("不存在该用户名:[{}]",sysUserId);
                    throw new ServiceException("不存在该用户名:" + sysUserId);
                }

                int user = userService.deleteUser(gitlabUser.getId(),instanceCode);
                if (user > 0){
                    logger.info("用户名为[{}]的用户删除成功",sysUserId);
                }
            } catch (Exception e) {
                logger.error("用户名为[{}]的用户删除失败:[{}]",sysUserId,e.getMessage());
                throw new ServiceException("gitlab用户删除出现异常:"+e.getMessage());
            }

        });
    }
}
