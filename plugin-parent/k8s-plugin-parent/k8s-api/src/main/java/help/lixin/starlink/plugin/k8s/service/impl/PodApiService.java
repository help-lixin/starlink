package help.lixin.starlink.plugin.k8s.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import help.lixin.starlink.plugin.k8s.service.IPodApiService;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.api.model.StatusDetails;
import io.fabric8.kubernetes.client.KubernetesClient;

/**
 * @Author: 伍岳林
 * @Date: 2024/6/21 下午4:56
 * @Description
 */
public class PodApiService implements IPodApiService {

    private KubernetesClient client;

    public PodApiService(KubernetesClient client) {
        this.client = client;
    }

    @Override
    public Pod queryPod(String namespace, String podName) {
        return client.pods().inNamespace(namespace).withName(podName).get();
    }

    @Override
    public List<Pod> queryPodGroup(String namespace, Map<String, String> labels) {
        PodList app = client.pods().inNamespace(namespace).withLabels(labels).list();
        return app.getItems().stream().collect(Collectors.toList());
    }

    @Override
    public String queryLog(String namespace, String podName, String containerName) {
        return client.pods().inNamespace(namespace).withName(podName).inContainer(containerName).getLog();
    }

    @Override
    public List<Pod> queryPodList(String namespace) {
        return client.pods().inNamespace(namespace).list().getItems();
    }

    @Override
    public Boolean deletePod(String namespace, String podName) {
        List<StatusDetails> statusDetails = client.pods().inNamespace(namespace).withName(podName).delete();
        return null != statusDetails && statusDetails.size() > 0;
    }

}
