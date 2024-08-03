package help.lixin.starlink.plugin.k8s.service.impl;

import help.lixin.starlink.plugin.k8s.service.INodeApiService;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;

import java.util.Map;

/**
 * @Author: 伍岳林
 * @Date: 2024/3/29 下午4:52
 * @Description
 */
public class NodeApiService implements INodeApiService {

    private KubernetesClient client;


    public NodeApiService(KubernetesClient client) {
        this.client = client;
    }

    @Override
    public NodeList queryNods() {
        NonNamespaceOperation<Node, NodeList, Resource<Node>> nodes = client.nodes();
        return nodes.list();
    }

    @Override
    public Node queryNod(String nodeName) {
        NonNamespaceOperation<Node, NodeList, Resource<Node>> nodes = client.nodes();
        return nodes.withName(nodeName).get();
    }

    @Override
    public Node saveNodeLabel(String nodeName, Map<String, String> labels) {
        NonNamespaceOperation<Node, NodeList, Resource<Node>> nodes = client.nodes();
        return nodes.withName(nodeName).edit(label->
                        new NodeBuilder(label)
                        .editOrNewMetadata()
                        .addToLabels(labels)
                        .endMetadata()
                        .build()
        );
    }

    @Override
    public Node deleteNodeLabel(String nodeName, String labelName) {
        NonNamespaceOperation<Node, NodeList, Resource<Node>> nodes = client.nodes();
        return nodes.withName(nodeName).edit(node->
                new NodeBuilder(node)
                        .editMetadata()
                        .removeFromLabels(labelName)
                        .endMetadata()
                        .build()
            );
    }

}
