package help.lixin.starlink.plugin.jsch.mq.consumer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import help.lixin.starlink.plugin.jsch.mq.constants.JschConstants;
import help.lixin.starlink.plugin.jsch.service.IGenerateSshHostsService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.jsch.api.dto.Upload;
import help.lixin.starlink.plugin.jsch.api.service.impl.SecureCopyService;
import help.lixin.starlink.plugin.jsch.domain.AnsibleLabelUpdateEvent;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/24 下午5:01
 * @Description
 */
public class UpdateAnsibleHostsEventHandler {
    private Logger logger = LoggerFactory.getLogger(UpdateAnsibleHostsEventHandler.class);

    private AbstractServiceFactory jschServiceFactory;
    private IGenerateSshHostsService generateHostsService;

    private final String aggregateType;

    public UpdateAnsibleHostsEventHandler(AbstractServiceFactory jschServiceFactory, //
        IGenerateSshHostsService generateHostsService, //
        String aggregateType) {

        this.jschServiceFactory = jschServiceFactory;
        this.generateHostsService = generateHostsService;
        this.aggregateType = aggregateType;
    }

    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
            // 聚合根对象:Account
            .forAggregateType(aggregateType)
            // 事件处理
            .onEvent(AnsibleLabelUpdateEvent.class, this::updateAnsibleHandle)
            //
            .build();
    }

    public void updateAnsibleHandle(DomainEventEnvelope<AnsibleLabelUpdateEvent> event) {
        AnsibleLabelUpdateEvent updateHostsEvent = event.getEvent();
        logger.info("开始更新ansible处理，标签key为[{}]", updateHostsEvent.getLabelKey());
        // 主控机器
        Map<String, String> masterHostsAndDir = updateHostsEvent.getMasterHostsAndDir();
        // 被控机器列表信息
        List<String> instanceCodes = updateHostsEvent.getSlaveHostsList();
        // 标签
        String labelKey = updateHostsEvent.getLabelKey();

        // 创建hosts内容
        String hosts = generateHostsService.generateHosts(labelKey, instanceCodes);
        try {
            String homeDir = System.getProperty(JschConstants.HOME);
            String fileName = labelKey + JschConstants.FILE_SUFFIX;
            String sshTemplateHostsFile = String.format("%s%s%s%s", homeDir + File.separator,
                JschConstants.TEMPLATE_DIR + File.separator, JschConstants.LABEL_KEY + File.separator, fileName);
            File labelHostsFile = new File(sshTemplateHostsFile);

            FileUtils.forceMkdirParent(labelHostsFile);
            FileUtils.touch(labelHostsFile);
            IOUtils.write(hosts.getBytes(StandardCharsets.UTF_8), new FileOutputStream(labelHostsFile));
            // 临时文件的路径
            String filePath = labelHostsFile.getPath();
            // 给每个主控机器发送文件
            masterHostsAndDir.forEach((instanceCode, dir) -> {
                SecureCopyService jschServiceFactoryInstance =
                    jschServiceFactory.getInstance(instanceCode, SecureCopyService.class);
                try {
                    logger.info("准备上传文件:[{}]", filePath);
                    Upload upload = new Upload();
                    upload.setLocalFile(filePath);
                    upload.setRemoteDir(dir);
                    jschServiceFactoryInstance.upload(upload);
                    logger.info("文件[{}]上传成功，已上传到主机[{}],目录为[{}]", filePath,
                        jschServiceFactoryInstance.getJschProperties().getHost(), dir);
                } catch (Exception e) {
                    throw new RuntimeException("上传文件出现异常：" + e.getMessage(), e);
                }
            });
            labelHostsFile.delete();
        } catch (IOException e) {
            throw new RuntimeException("创建临时文件出现异常：" + e.getMessage(), e);
        }
    }
}
