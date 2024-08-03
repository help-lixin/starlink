package help.lixin.starlink.plugin.k8s.action.api;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.PodResource;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: 伍岳林
 * @Date: 2024/3/29 上午11:20
 * @Description
 */
public class PodTest extends BaseTest{

    @Test
    public void getPods(){
        MixedOperation<Pod, PodList, PodResource> pods = client.pods();
        NonNamespaceOperation<Pod, PodList, PodResource> podPodListPodResourceNonNamespaceOperation = pods.inNamespace("lab-k");
        PodList list = podPodListPodResourceNonNamespaceOperation.list();
        Assert.assertNotNull(list);
    }
}
