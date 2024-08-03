package help.lixin.starlink.plugin.ansible.mq.provider;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.ansible.domain.AnsibleHostManage;
import help.lixin.starlink.plugin.ansible.mapper.AnsibleHostManageMapper;
import help.lixin.starlink.plugin.jsch.constants.JschEventConstants;
import help.lixin.starlink.plugin.jsch.domain.AnsibleLabelUpdateEvent;
import io.eventuate.tram.events.common.DomainEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/22 下午4:25
 * @Description
 */
public class UpdateAnsibleHostsHandler {

    private Logger logger = LoggerFactory.getLogger(UpdateAnsibleHostsHandler.class);
    private DomainEventPublisher domainEventPublisher;
    private AnsibleHostManageMapper ansibleHostManageMapper;

    public void updateHosts(Long id, String labelKey, List<String> instanceCodes) {
        String aggregateType = JschEventConstants.UPDATE_HOSTS_EVENT_TOPIC;
        // 获取所有主控机器hosts文件存放目录
        List<AnsibleHostManage> ansibleHostManages = ansibleHostManageMapper.selectAll();
        Map<String, String> instanceCodeAndInventoryMap = ansibleHostManages.stream()
            .collect(Collectors.toMap(v -> v.getSshInstanceCode(), v -> v.getAnsibleInventoryDir()));

        if (CollectionUtils.isEmpty(instanceCodeAndInventoryMap)) {
            throw new ServiceException("主控机与目录为空，请确认实例是否已经启用");
        }
        DomainEvent updateEvent = new AnsibleLabelUpdateEvent(labelKey, instanceCodeAndInventoryMap, instanceCodes);
        logger.info("开始发布更新hosts文件消息：[{}],Id:[{}]", updateEvent, id);

        // 发布事件.
        domainEventPublisher.publish(
            //
            aggregateType,
            //
            id,
            //
            Collections.singletonList(updateEvent));
        logger.info("发送Id为[{}]的消息成功", id);
    }

    public UpdateAnsibleHostsHandler(DomainEventPublisher domainEventPublisher,
        AnsibleHostManageMapper ansibleHostManageMapper) {
        this.domainEventPublisher = domainEventPublisher;
        this.ansibleHostManageMapper = ansibleHostManageMapper;
    }
}
