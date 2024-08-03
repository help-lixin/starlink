package help.lixin.starlink.plugin.k8s.service.impl;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.ANNOTATION_FLAB;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.SmartLifecycle;

import help.lixin.starlink.plugin.credential.adapter.event.namespace.NameSpaceCreateEvent;
import help.lixin.starlink.plugin.credential.adapter.event.namespace.NameSpaceDeleteEvent;
import help.lixin.starlink.plugin.credential.domain.namespace.NameSpaceCreate;
import help.lixin.starlink.plugin.credential.domain.namespace.NameSpaceDelete;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.Watch;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.WatcherException;

public class WatchNamespaceEventService implements SmartLifecycle, ApplicationEventPublisherAware {
    private final String instance;
    private final KubernetesClient kubernetesClient;
    private ApplicationEventPublisher applicationEventPublisher;
    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    private Watch watch;
    private Logger logger = LoggerFactory.getLogger(WatchNamespaceEventService.class);

    public WatchNamespaceEventService(String instance, //
                                      KubernetesClient kubernetesClient //
    ) {
        this.instance = instance;
        this.kubernetesClient = kubernetesClient;
    }

    @Override
    public void start() {
        if (isRunning.compareAndSet(false, true)) {
            watch = kubernetesClient.namespaces().watch(new Watcher<Namespace>() {
                @Override
                public boolean reconnecting() { // watch异常时,进行自动重连
                    return true;
                }

                @Override
                public void eventReceived(Action action, Namespace resource) {
                    ObjectMeta metadata = resource.getMetadata();
                    // 通过平台添加namespace时,会在:annotations添加一个元数据,如果,不是通过平台(比如:运维通过YAML)则代表这条数据,是需要同步到DB层.
                    if (!metadata.getAnnotations().containsKey(ANNOTATION_FLAB)) {
                        // 这里需要判断元数据中是否存在有凭证添加的数据，有则跳过
                        if (action.name().equals(Action.ADDED)) {
                            NameSpaceCreate nameSpaceCreate = new NameSpaceCreate();
                            nameSpaceCreate.setInstanceCode(instance);
                            nameSpaceCreate.setNameSpace(metadata.getName());
                            if (null != metadata.getCreationTimestamp()) {
                                try {
                                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                                    Date date = dateFormat.parse(metadata.getCreationTimestamp());
                                    nameSpaceCreate.setCreateTime(date);
                                } catch (ParseException ignore) {
                                    logger.error("日期格式转换异常:{}", metadata.getCreationTimestamp());
                                }
                            }
                            NameSpaceCreateEvent nameSpacecreateEvent = new NameSpaceCreateEvent(nameSpaceCreate);
                            getApplicationEventPublisher().publishEvent(nameSpacecreateEvent);
                        } else if (action.name().equals(Action.DELETED)) {
                            NameSpaceDelete nameSpaceDelete = new NameSpaceDelete();
                            nameSpaceDelete.setInstanceCode(instance);
                            nameSpaceDelete.setNameSpace(metadata.getName());
                            NameSpaceDeleteEvent nameSpaceDeleteEvent = new NameSpaceDeleteEvent(nameSpaceDelete);
                            getApplicationEventPublisher().publishEvent(nameSpaceDeleteEvent);
                        }
                    }
                }

                @Override
                public void onClose(WatcherException cause) {
                    logger.error("namespace监听异常:[{}]", cause.getMessage());
                    onClose();
                }
            });
        }
    }

    @Override
    public void stop() {
        isRunning.set(false);
        watch = null;
        applicationEventPublisher = null;
    }

    @Override
    public boolean isRunning() {
        return isRunning.get();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public ApplicationEventPublisher getApplicationEventPublisher() {
        return applicationEventPublisher;
    }
}
