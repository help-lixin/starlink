package help.lixin.starlink.plugin.k8s.service.impl;

import help.lixin.starlink.plugin.k8s.service.INamespaceApiService;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;

import java.util.Map;

/**
 * @Author: 伍岳林
 * @Date: 2024/3/31 上午10:37
 * @Description
 */
public class NameSpaceApiService implements INamespaceApiService {

    private KubernetesClient client;


    public NameSpaceApiService(KubernetesClient client) {
        this.client = client;
    }

    @Override
    public Namespace createNameSpace(Namespace namespace) {
        return client.namespaces().resource(namespace).create();
    }

    @Override
    public Boolean deleteNameSpace(String name) {
        return client.namespaces().withName(name).delete().size() == 1;
    }

    @Override
    public NamespaceList queryNameSpaces() {
        NonNamespaceOperation<Namespace, NamespaceList, Resource<Namespace>> namespaces = client.namespaces();
        return namespaces.list();
    }

    @Override
    public Namespace queryNameSpaceByName(String name) {
        NonNamespaceOperation<Namespace, NamespaceList, Resource<Namespace>> namespaces = client.namespaces();
        return namespaces.withName(name).get();
    }

    @Override
    public Namespace saveLabels(String name, Map<String, String> labels) {
        return client.namespaces().withName(name).edit(n->
                new NamespaceBuilder(n)
                        .editOrNewMetadata()
                        .addToLabels(labels)
                        .endMetadata()
                        .build());
    }

    @Override
    public Namespace removeLabel(String name, String labelName) {
        NonNamespaceOperation<Namespace, NamespaceList, Resource<Namespace>> namespaces = client.namespaces();
        return namespaces.withName(name).edit(n->
                new NamespaceBuilder()
                        .editMetadata()
                        .removeFromLabels(labelName)
                        .endMetadata()
                        .build()
        );
    }
}
