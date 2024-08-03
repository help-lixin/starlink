package help.lixin.starlink.plugin.k8s.mq.consumer.pods;

import static help.lixin.starlink.plugin.k8s.mq.constants.K8sConstant.SYNC_PODS_EVENT_TOPIC;

import java.util.List;

import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.k8s.domain.KubernetesNameSpace;
import help.lixin.starlink.plugin.k8s.mq.event.K8sSyncPodEvent;
import help.lixin.starlink.plugin.k8s.service.IK8sPodService;
import help.lixin.starlink.plugin.k8s.service.impl.PodApiService;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import io.fabric8.kubernetes.api.model.Pod;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/22 上午10:17
 * @Description
 */
public class K8sSyncPodsEventHandler {
    private Logger logger = LoggerFactory.getLogger(K8sSyncPodsEventHandler.class);

    private final AbstractServiceFactory k8sServiceFactory;
    private IK8sPodService podService;

    public K8sSyncPodsEventHandler(AbstractServiceFactory k8sServiceFactory, IK8sPodService podService) {
        this.k8sServiceFactory = k8sServiceFactory;
        this.podService = podService;
    }

    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
            // 聚合根对象
            .forAggregateType(SYNC_PODS_EVENT_TOPIC)
            // 事件处理
            .onEvent(K8sSyncPodEvent.class, this::syncK8sPods)
            //
            .build();
    }

    public void syncK8sPods(DomainEventEnvelope<K8sSyncPodEvent> event) {
        K8sSyncPodEvent sysOpaqueCreateCredentialEvent = event.getEvent();
        String instanceCode = sysOpaqueCreateCredentialEvent.getInstanceCode();
        String name = sysOpaqueCreateCredentialEvent.getName();
        String nameSpace = sysOpaqueCreateCredentialEvent.getNameSpace();
        String userName = sysOpaqueCreateCredentialEvent.getUserName();

        logger.info("k8s同步Pods[{}]开始消费倒计时1分钟", name);
        try {
            Thread.sleep(1l);
        } catch (InterruptedException e) {
            throw new ServiceException("启动休眠出现异常");
        }

        PodApiService instance = k8sServiceFactory.getInstance(instanceCode, PodApiService.class);
        List<Pod> pods = instance.queryPodList(nameSpace);

        if (CollectionUtils.isEmpty(pods)) {
            return;
        }

        podService.deleteKubernetesAppByName(instanceCode, name);

        KubernetesNameSpace kubernetesNameSpace = podService.queryNameSpace(instanceCode, nameSpace);

        for (Pod pod : pods) {
            if (pod.getMetadata().getGenerateName().contains(name + "-")) {
                K8sAppDTO k8sAppDTO = new K8sAppDTO();
                k8sAppDTO.setUserName(userName);
                k8sAppDTO.setKind("Pod");
                k8sAppDTO.setInstanceCode(instanceCode);
                k8sAppDTO.setName(pod.getMetadata().getName());
                k8sAppDTO.setNamespace(nameSpace);
                k8sAppDTO.setNameSpaceId(kubernetesNameSpace.getId());

                podService.saveKubernetesApp(k8sAppDTO);
            }
        }

        logger.info("k8s同步Pods[{}]创建成功", name);

    }
}
