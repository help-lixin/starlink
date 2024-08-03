package help.lixin.starlink.plugin.gitlab.mq.consumer;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.event.user.SystemUserCreateEvent;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.gitlab.dto.user.UserCreateDTO;
import help.lixin.starlink.plugin.gitlab.mq.convert.GitlabUserMQConvert;
import help.lixin.starlink.plugin.gitlab.service.IUserService;
import help.lixin.topic.SystemUserTopicConstants;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import org.jasypt.encryption.StringEncryptor;
import org.mapstruct.factory.Mappers;
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
public class SyncSystemCreateUserHandler {

    private Logger logger = LoggerFactory.getLogger(SyncSystemCreateUserHandler.class);

    private IUserService userService;
    private AbstractServiceFactory gitlabServiceFactory;
    private StringEncryptor stringEncryptor;

    public SyncSystemCreateUserHandler(IUserService userService, AbstractServiceFactory gitlabServiceFactory,
                                       StringEncryptor stringEncryptor) {
        this.userService = userService;
        this.gitlabServiceFactory = gitlabServiceFactory;
        this.stringEncryptor = stringEncryptor;
    }

    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
                // 聚合根对象:Account
                .forAggregateType(SystemUserTopicConstants.CREATE_USER_EVENT_TOPIC)
                // 事件处理
                .onEvent(SystemUserCreateEvent.class, this::syncSystemCreateUser)
                //
                .build();
    }

    @Transactional(rollbackFor = {ServiceException.class})
    public void syncSystemCreateUser(DomainEventEnvelope<SystemUserCreateEvent> event){
        SystemUserCreateEvent systemUserCreateEvent = event.getEvent();
        //获取插件列表
        Set<String> contextNames = gitlabServiceFactory.getPluginNamedContextFactory().getContextNames();
        if (CollectionUtils.isEmpty(contextNames)) {
            return;
        }

        //遍历插件列表
        contextNames.forEach(instanceCode->{
            logger.info("开始进行gitlab用户[{}]实例的消费", instanceCode);

            String userName = systemUserCreateEvent.getUserName();

            try {
                GitlabUserMQConvert mapper = Mappers.getMapper(GitlabUserMQConvert.class);
                UserCreateDTO userCreateDTO = mapper.convert(systemUserCreateEvent);

                userCreateDTO.setInstanceCode(instanceCode);
                String clearPwd = stringEncryptor.decrypt(systemUserCreateEvent.getPassword());
                userCreateDTO.setPwd(clearPwd);

                // 新增用户
                int userResult = userService.createUser(userCreateDTO);

                if (userResult > 0){
                    logger.info("用户名为[{}]的用户添加成功", userName);
                }
            } catch (Exception e) {
                logger.error("用户名为[{}]的用户添加失败:[{}]", userName,e.getMessage());
                throw new ServiceException("gitlab用户添加出现异常:"+e.getMessage());
            }
        });
    }
}
