package help.lixin.starlink.plugin.k8s.action.api;

import io.fabric8.kubernetes.api.builder.Visitor;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.ServiceResource;
import org.junit.Assert;
import org.junit.Test;

public class ServiceTest extends BaseTest {

    @Test
    public void testListService() {
        NonNamespaceOperation<Service, ServiceList, ServiceResource<Service>> serviceService = client.services().inNamespace("default");
        ServiceList list = serviceService.list();
        Assert.assertNotNull(list);
    }

    @Test
    public void testSaveService() {
        NonNamespaceOperation<Service, ServiceList, ServiceResource<Service>> serviceService = client.services().inNamespace("default");
        Service service = serviceService.create(new ServiceBuilder()
                //
                .withApiVersion("v1")
                //
                .withKind("Service")
                //
                .withMetadata(new ObjectMetaBuilder()
                        .withName("nginx-service")
                        .withNamespace("default")
                        .addToLabels("app", "nginx-service")
                        .build())
                //
                .withSpec(new ServiceSpecBuilder()
                        //
                        .withType("NodePort")
                        //
                        .addToSelector("app", "nginx-deploy")
                        //
                        .addToPorts(new ServicePortBuilder()
                                .withPort(9090)      // 容器内部访问时的端口
                                .withNodePort(30008) //  当是NodePort,指定在宿主机上创建的端口,可以通过nginx直接全部打过来.
                                .withTargetPort(new IntOrString(80)) // 目标容器的端口
                                .withProtocol("TCP")
                                .build())
                        //
                        .build())
                .build());
        Assert.assertNotNull(service);
    }


    @Test
    public void testUpdateService() {
        client.services()
                //
                .inNamespace("default")
                //
                .withName("nginx-service")
                //
                .edit(new Visitor<ServiceBuilder>() {
                    @Override
                    public void visit(ServiceBuilder builder) {
                        builder.editMetadata()
                                //
                                    .addToLabels("test", "test")
                                //
                                .endMetadata();
                    }
                });
    }

    @Test
    public void testDeleteService() {
        client.services().inNamespace("default").withName("nginx-service").delete();
    }

}
