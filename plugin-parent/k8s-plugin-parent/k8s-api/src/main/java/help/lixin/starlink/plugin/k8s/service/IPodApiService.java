package help.lixin.starlink.plugin.k8s.service;

import java.util.List;
import java.util.Map;

import io.fabric8.kubernetes.api.model.Pod;

public interface IPodApiService {
    Pod queryPod(String namespace, String podName);

    List<Pod> queryPodGroup(String namespace, Map<String, String> labels);

    String queryLog(String namespace, String podName, String containerName);

    List<Pod> queryPodList(String namespace);

    Boolean deletePod(String namespace, String podName);
}
