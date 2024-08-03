package help.lixin.starlink.plugin.k8s.service;

import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServiceList;

public interface IServiceApiService {

    Service createService(Service service);

    Service updateService(Service service);

    Service queryService(String namespace,String serviceName);

    ServiceList queryServices(String namespace);

    Boolean deleteService(String namespace,String serviceName);
}
