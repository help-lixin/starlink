package help.lixin.starlink.plugin.k8s.service.impl;

import help.lixin.starlink.plugin.k8s.service.IServiceApiService;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServiceBuilder;
import io.fabric8.kubernetes.api.model.ServiceList;
import io.fabric8.kubernetes.client.KubernetesClient;

/**
 * @Author: 伍岳林
 * @Date: 2024/4/2 下午4:53
 * @Description
 */
public class ServiceApiService implements IServiceApiService {

    private KubernetesClient client;

    public ServiceApiService(KubernetesClient client) {
        this.client = client;
    }

    @Override
    public Service createService(Service service) {
        return client.services().inNamespace(service.getMetadata().getNamespace()).resource(service).create();
    }

    @Override
    public Service updateService(Service service) {
        return client.services().inNamespace(service.getMetadata().getNamespace())
                .withName(service.getMetadata().getName())
                .edit(s->
                        new ServiceBuilder(service)
                                .build()
                );
    }

    @Override
    public Service queryService(String namespace, String serviceName) {
        return client.services().inNamespace(namespace).withName(serviceName).get();
    }

    @Override
    public ServiceList queryServices(String namespace) {
        return client.services().inNamespace(namespace).list();
    }

    @Override
    public Boolean deleteService(String namespace, String serviceName) {
        return client.services().inNamespace(namespace).withName(serviceName).delete().size() == 1;
    }
}
