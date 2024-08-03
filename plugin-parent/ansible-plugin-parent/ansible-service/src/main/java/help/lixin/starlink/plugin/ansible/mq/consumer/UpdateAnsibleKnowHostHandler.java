package help.lixin.starlink.plugin.ansible.mq.consumer;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.ansible.domain.AnsibleHostManage;
import help.lixin.starlink.plugin.ansible.domain.AnsibleKnownHostEvent;
import help.lixin.starlink.plugin.ansible.mapper.AnsibleHostManageMapper;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/2 下午4:23
 * @Description
 */
public class UpdateAnsibleKnowHostHandler {

    private Logger logger = LoggerFactory.getLogger(UpdateAnsibleKnowHostHandler.class);
    private final String aggregateType;

    private AnsibleHostManageMapper ansibleHostManageMapper;

    public UpdateAnsibleKnowHostHandler(String aggregateType, AnsibleHostManageMapper ansibleHostManageMapper) {
        this.aggregateType = aggregateType;
        this.ansibleHostManageMapper = ansibleHostManageMapper;
    }

    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
                // 聚合根对象:Account
                .forAggregateType(aggregateType)
                // 事件处理
                .onEvent(AnsibleKnownHostEvent.class, this::knowHostConsumer)
                //
                .build();
    }

    @Transactional(rollbackFor = {ServiceException.class})
    public void knowHostConsumer(DomainEventEnvelope<AnsibleKnownHostEvent> event){
        String instanceCode = event.getEvent().getInstanceCode();
        logger.info("开始进行[{}]实例的KnownHosts消费", instanceCode);
        AnsibleKnownHostEvent ansibleKnownHostEvent = event.getEvent();
        AnsibleHostManage ansibleHostManage = ansibleHostManageMapper.selectByInstanceCode(ansibleKnownHostEvent.getInstanceCode());
        if (ansibleHostManage != null){
            ansibleHostManage.setKnowHost(ansibleKnownHostEvent.getKnownHostText());
            ansibleHostManage.setUpdateTime(new Date());
            ansibleHostManageMapper.updateById(ansibleHostManage);
            logger.info("实例[{}] KnownHosts消费成功",instanceCode);
        }
    }
}
