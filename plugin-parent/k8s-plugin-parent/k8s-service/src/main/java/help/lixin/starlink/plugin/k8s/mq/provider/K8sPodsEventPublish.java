package help.lixin.starlink.plugin.k8s.mq.provider;

import static help.lixin.starlink.plugin.k8s.mq.constants.K8sConstant.SYNC_PODS_EVENT_TOPIC;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import help.lixin.starlink.plugin.k8s.mq.event.K8sSyncPodEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/24 下午5:01
 * @Description
 */
public class K8sPodsEventPublish {
    private Logger logger = LoggerFactory.getLogger(K8sPodsEventPublish.class);

    private DomainEventPublisher domainEventPublisher;

    public void syncPods(K8sSyncPodEvent k8SSyncPodEvent) {
        String aggregateType = SYNC_PODS_EVENT_TOPIC;

        String credentialKey = k8SSyncPodEvent.getName();

        // 发布事件.
        domainEventPublisher.publish(
            //
            aggregateType,
            //
            credentialKey,
            //
            Collections.singletonList(k8SSyncPodEvent));
        logger.info("发送同步[{}]相关Pods的消息成功", credentialKey);
    }

    public K8sPodsEventPublish(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }
}
