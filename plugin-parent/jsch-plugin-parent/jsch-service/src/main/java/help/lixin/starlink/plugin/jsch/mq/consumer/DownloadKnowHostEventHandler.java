package help.lixin.starlink.plugin.jsch.mq.consumer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import help.lixin.starlink.plugin.jsch.mq.constants.JschConstants;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.ansible.constants.AnsibleConstants;
import help.lixin.starlink.plugin.ansible.domain.AnsibleKnownHostEvent;
import help.lixin.starlink.plugin.jsch.api.dto.Download;
import help.lixin.starlink.plugin.jsch.api.properties.JschProperties;
import help.lixin.starlink.plugin.jsch.api.service.impl.SecureCopyService;
import help.lixin.starlink.plugin.jsch.domain.AnsibleHostsUpdateEvent;
import io.eventuate.tram.events.common.DomainEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/22 上午10:17
 * @Description
 */
public class DownloadKnowHostEventHandler {
    private Logger logger = LoggerFactory.getLogger(DownloadKnowHostEventHandler.class);

    private final AbstractServiceFactory jschServiceFactory;
    private final String aggregateType;
    private DomainEventPublisher domainEventPublisher;

    public DownloadKnowHostEventHandler(AbstractServiceFactory jschServiceFactory,
        DomainEventPublisher domainEventPublisher, String aggregateType) {
        this.jschServiceFactory = jschServiceFactory;
        this.domainEventPublisher = domainEventPublisher;
        this.aggregateType = aggregateType;
    }

    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
            // 聚合根对象:Account
            .forAggregateType(aggregateType)
            // 事件处理
            .onEvent(AnsibleHostsUpdateEvent.class, this::startDownloadKnowHost)
            //
            .build();
    }

    public void startDownloadKnowHost(DomainEventEnvelope<AnsibleHostsUpdateEvent> event) {
        AnsibleHostsUpdateEvent ansibleHostsUpdateEvent = event.getEvent();
        List<String> instanceCodeList = ansibleHostsUpdateEvent.getInstanceCodeList();
        logger.info("开始下载ansible主机文件处理");
        instanceCodeList.forEach(instanceCode -> {
            SecureCopyService secureCopyService = jschServiceFactory.getInstance(instanceCode, SecureCopyService.class);
            Download download = new Download();

            JschProperties jschProperties = secureCopyService.getJschProperties();
            String userName = jschProperties.getUsername();
            String remoteDir = String.format("/home/%s/.ssh/known_hosts", userName);
            download.setRemoteDir(remoteDir);
            try {
                String dir = secureCopyService.download(download);
                String content = Files.readString(Paths.get(dir + File.separator + JschConstants.KNOWN_HOSTS));
                DomainEvent ansibleKnownHostEvent = new AnsibleKnownHostEvent(content, instanceCode);
                String aggregateType = AnsibleConstants.ANSIBLE_KNOWN_HOST_EVENT_TOPIC;
                Long uniqueId = System.currentTimeMillis();
                logger.info("开始发布KnownHosts下载事件,实例为[{}]", instanceCode);
                // 发布事件.
                domainEventPublisher.publish(
                    //
                    aggregateType,
                    //
                    uniqueId,
                    //
                    Collections.singletonList(ansibleKnownHostEvent));
                logger.info("实例[{}] 发送成功", instanceCode);
            } catch (IOException e) {
                throw new RuntimeException("读取文件出现异常:" + e.getMessage(), e);
            } catch (BuildException e) {
                throw new RuntimeException("文件可能不存在:" + e.getMessage(), e);
            }
        });
    }
}
