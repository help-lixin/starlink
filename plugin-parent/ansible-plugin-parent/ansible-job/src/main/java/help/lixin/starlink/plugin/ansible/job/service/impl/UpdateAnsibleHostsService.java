package help.lixin.starlink.plugin.ansible.job.service.impl;

import help.lixin.starlink.plugin.jsch.constants.JschEventConstants;
import help.lixin.starlink.plugin.jsch.domain.AnsibleHostsUpdateEvent;
import help.lixin.starlink.plugin.ansible.job.service.IUpdateAnsibleHostsService;
import help.lixin.starlink.plugin.ansible.mapper.AnsibleHostManageMapper;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/22 下午3:23
 * @Description
 */
public class UpdateAnsibleHostsService implements IUpdateAnsibleHostsService {

    private Logger logger = LoggerFactory.getLogger(UpdateAnsibleHostsService.class);
    private AnsibleHostManageMapper ansibleHostManageMapper;
    private DomainEventPublisher domainEventPublisher;

    @Override
    public void updateKnownHosts() {
        String aggregateType = JschEventConstants.DOWNLOAD_KNOWN_HOSTS_EVENT_TOPIC;
        List<String> instanceCodes = ansibleHostManageMapper.selectAllInstanceCode();

        AnsibleHostsUpdateEvent updateAnsibleHostsEvent = new AnsibleHostsUpdateEvent(instanceCodes);
        Long uniqueId = System.currentTimeMillis();
        logger.info("开始发布创建hosts文件消息：[{}],Id:[{}]", instanceCodes.toArray(), uniqueId);
        // 发布事件.
        domainEventPublisher.publish(
                //
                aggregateType,
                //
                uniqueId,
                //
                Collections.singletonList(updateAnsibleHostsEvent));
        logger.info("发送Id为[{}]的消息成功", uniqueId);
    }

    public UpdateAnsibleHostsService(AnsibleHostManageMapper ansibleHostManageMapper, DomainEventPublisher domainEventPublisher) {
        this.ansibleHostManageMapper = ansibleHostManageMapper;
        this.domainEventPublisher = domainEventPublisher;
    }
}
