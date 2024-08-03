package help.lixin.starlink.plugin.gitlab.mq.consumer;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.event.user.SystemUserUpdateEvent;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.gitlab.domain.GitlabUser;
import help.lixin.starlink.plugin.gitlab.dto.user.UserUpdateDTO;
import help.lixin.starlink.plugin.gitlab.mq.convert.GitlabUserMQConvert;
import help.lixin.starlink.plugin.gitlab.service.IUserService;
import help.lixin.topic.SystemUserTopicConstants;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import org.apache.commons.lang3.StringUtils;
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
public class SyncSystemUpdateUserHandler {

    private Logger logger = LoggerFactory.getLogger(SyncSystemUpdateUserHandler.class);

    private IUserService userService;
    private AbstractServiceFactory gitlabServiceFactory;
    private StringEncryptor stringEncryptor;

    public SyncSystemUpdateUserHandler(IUserService userService, AbstractServiceFactory gitlabServiceFactory, StringEncryptor stringEncryptor) {
        this.userService = userService;
        this.gitlabServiceFactory = gitlabServiceFactory;
        this.stringEncryptor = stringEncryptor;
    }

    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
                // 聚合根对象:Account
                .forAggregateType(SystemUserTopicConstants.UPDATE_USER_EVENT_TOPIC)
                // 事件处理
                .onEvent(SystemUserUpdateEvent.class, this::syncSystemUpdateUser)
                //
                .build();
    }

    @Transactional(rollbackFor = {ServiceException.class})
    public void syncSystemUpdateUser(DomainEventEnvelope<SystemUserUpdateEvent> event){
        SystemUserUpdateEvent systemUserUpdateEvent = event.getEvent();
        //获取插件列表
        Set<String> contextNames = gitlabServiceFactory.getPluginNamedContextFactory().getContextNames();
        if (CollectionUtils.isEmpty(contextNames)) {
            return;
        }

        //遍历插件列表
        contextNames.forEach(instanceCode->{
            logger.info("开始进行gitlab更新用户[{}]实例的消费", instanceCode);

            GitlabUserMQConvert mapper = Mappers.getMapper(GitlabUserMQConvert.class);
            UserUpdateDTO userUpdateDTO = mapper.convert(systemUserUpdateEvent);
            userUpdateDTO.setInstanceCode(instanceCode);
            String pwd = stringEncryptor.decrypt(systemUserUpdateEvent.getPassword());
            userUpdateDTO.setPwd(StringUtils.isBlank(pwd) ? null : pwd);
            try {
                GitlabUser gitlabUser = userService.queryUserBySysUserId(userUpdateDTO.getSysUserId());
                if (gitlabUser == null){
                    throw new ServiceException("该用户不存在");
                }

                userUpdateDTO.setId(gitlabUser.getId());
                int user = userService.updateUser(userUpdateDTO);
                if (user > 0){
                    logger.info("用户名为[{}]的用户更新成功",userUpdateDTO.getUserName());
                }
            } catch (Exception e) {
                logger.error("用户名为[{}]的用户更新失败:[{}]",userUpdateDTO.getUserName(),e.getMessage());
                throw new ServiceException("gitlab用户更新出现异常:"+e.getMessage());
            }
        });
    }
}
