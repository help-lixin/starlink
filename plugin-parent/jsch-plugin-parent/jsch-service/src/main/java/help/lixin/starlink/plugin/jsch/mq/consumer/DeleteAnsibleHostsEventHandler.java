package help.lixin.starlink.plugin.jsch.mq.consumer;

import java.io.File;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import help.lixin.starlink.plugin.jsch.mq.constants.JschConstants;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.jsch.api.dto.CommandExecuteResult;
import help.lixin.starlink.plugin.jsch.api.service.ICommandExecuteTemplateService;
import help.lixin.starlink.plugin.jsch.api.service.impl.SecureCopyService;
import help.lixin.starlink.plugin.jsch.constants.JschEventConstants;
import help.lixin.starlink.plugin.jsch.domain.AnsibleLabelDeleteEvent;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/24 下午5:01
 * @Description
 */
public class DeleteAnsibleHostsEventHandler {
    private Logger logger = LoggerFactory.getLogger(DeleteAnsibleHostsEventHandler.class);

    private AbstractServiceFactory jschServiceFactory;

    private final String aggregateType = JschEventConstants.DELETE_FILE_EVENT_TOPIC;

    public DeleteAnsibleHostsEventHandler(AbstractServiceFactory jschServiceFactory) {
        this.jschServiceFactory = jschServiceFactory;
    }

    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
            // 聚合根对象:Account
            .forAggregateType(aggregateType)
            // 事件处理
            .onEvent(AnsibleLabelDeleteEvent.class, this::deleteAnsibleHandle)
            //
            .build();
    }

    public void deleteAnsibleHandle(DomainEventEnvelope<AnsibleLabelDeleteEvent> event) {
        AnsibleLabelDeleteEvent deleteEvent = event.getEvent();
        logger.info("开始删除ansible处理，标签key为[{}]", deleteEvent.getLabelKey());
        // 主控机器
        Map<String, String> masterHostsAndDir = deleteEvent.getMasterHostsAndDir();
        // 标签
        String labelKey = deleteEvent.getLabelKey();

        String fileName = labelKey + JschConstants.FILE_SUFFIX;
        // 删除每个主控机器上的hosts文件
        masterHostsAndDir.forEach((instanceCode, dir) -> {
            SecureCopyService jschServiceFactoryInstance =
                jschServiceFactory.getInstance(instanceCode, SecureCopyService.class);
            try {
                ICommandExecuteTemplateService commandExecuteTemplateService =
                    jschServiceFactory.getInstance(instanceCode, ICommandExecuteTemplateService.class);
                String filePath = dir + File.separator + fileName;
                CommandExecuteResult executeResult = commandExecuteTemplateService.execute("rm -rf " + filePath);
                if (executeResult.isSuccess()) {
                    logger.info("文件[{}]删除成功，已删除主机文件:[{}],目录为:{}]", filePath,
                        jschServiceFactoryInstance.getJschProperties().getHost(), dir);
                } else {
                    logger.error("文件删除失败，可能因为文件不存在或连接已断开");
                }
            } catch (Exception e) {
                throw new RuntimeException("删除文件出现异常：" + e.getMessage());
            }
        });
    }
}
