package help.lixin.starlink.plugin.k8s.action.api;

import io.fabric8.kubernetes.api.builder.Visitor;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import org.junit.Test;

/**
 * @Author: 伍岳林
 * @Date: 2024/3/29 上午11:26
 * @Description
 */
public class NodeTest extends BaseTest{

    @Test
    public void getNodes(){
        NonNamespaceOperation<Node, NodeList, Resource<Node>> nodes = client.nodes();
        Node node = nodes.withName("k8s-slave-1").edit((Visitor<NodeBuilder>) nodeBuilder->{
            nodeBuilder.editMetadata()
                    .addToLabels("intellijTest","success")
                    .endMetadata();
        });

        System.out.println(node);
    }
}
