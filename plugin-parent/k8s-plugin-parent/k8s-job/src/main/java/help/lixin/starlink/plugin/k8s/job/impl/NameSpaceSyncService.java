package help.lixin.starlink.plugin.k8s.job.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.credential.adapter.event.namespace.NameSpaceCreateEvent;
import help.lixin.starlink.plugin.credential.domain.namespace.NameSpaceCreate;
import help.lixin.starlink.plugin.k8s.domain.KubernetesNameSpace;
import help.lixin.starlink.plugin.k8s.job.INameSpaceSyncService;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesNameSpaceMapper;
import help.lixin.starlink.plugin.k8s.service.impl.NameSpaceApiService;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceList;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/21 下午4:30
 * @Description
 */
public class NameSpaceSyncService implements INameSpaceSyncService, ApplicationEventPublisherAware {
    private Logger logger = LoggerFactory.getLogger(NameSpaceSyncService.class);
    private ApplicationEventPublisher applicationEventPublisher;
    private KubernetesNameSpaceMapper nameSpaceMapper;
    private final AbstractServiceFactory k8sServiceFactory;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void syncNameSpace() {
        // 获取插件列表
        Set<String> contextNames = k8sServiceFactory.getPluginNamedContextFactory().getContextNames();
        if (CollectionUtils.isEmpty(contextNames)) {
            logger.info("插件列表为空");
            return;
        }

        // 遍历插件列表
        contextNames.forEach(instanceCode -> {
            logger.info("instanceCode：{} 开始拉取命名空间", instanceCode);
            NameSpaceApiService nameSpaceApiService =
                k8sServiceFactory.getInstance(instanceCode, NameSpaceApiService.class);
            NamespaceList namespaceList = nameSpaceApiService.queryNameSpaces();
            List<Namespace> items = namespaceList.getItems();
            if (CollectionUtils.isEmpty(items)) {
                logger.info("命名空间列表为空");
                return;
            }
            List<String> namespaces = items.stream().map(v -> v.getMetadata().getName()).collect(Collectors.toList());

            List<KubernetesNameSpace> kubernetesNameSpaces = nameSpaceMapper.queryInNameSpace(namespaces);

            Set<String> existName = kubernetesNameSpaces.stream().map(v -> v.getName()).collect(Collectors.toSet());
            List<String> diffName =
                namespaces.stream().filter(v -> !existName.contains(v)).collect(Collectors.toList());

            for (String nameSpace : diffName) {
                KubernetesNameSpace kubernetesNameSpace = new KubernetesNameSpace();

                kubernetesNameSpace.setName(nameSpace);
                kubernetesNameSpace.setInstanceCode(instanceCode);
                nameSpaceMapper.insertSelective(kubernetesNameSpace);

                NameSpaceCreate nameSpaceCreate = new NameSpaceCreate();
                nameSpaceCreate.setInstanceCode(instanceCode);
                nameSpaceCreate.setNameSpace(nameSpace);
                nameSpaceCreate.setCreateTime(new Date());

                NameSpaceCreateEvent nameSpacecreateEvent = new NameSpaceCreateEvent(nameSpaceCreate);
                applicationEventPublisher.publishEvent(nameSpacecreateEvent);
            }

            logger.info("instanceCode：{} 开始拉取命名空间完成", instanceCode);

        });
    }

    public NameSpaceSyncService(KubernetesNameSpaceMapper nameSpaceMapper, AbstractServiceFactory k8sServiceFactory) {
        this.nameSpaceMapper = nameSpaceMapper;
        this.k8sServiceFactory = k8sServiceFactory;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
