package help.lixin.starlink.plugin.k8s.service.impl;

import help.lixin.starlink.plugin.k8s.service.IDaemonSetApiService;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.apps.DaemonSet;
import io.fabric8.kubernetes.api.model.apps.DaemonSetBuilder;
import io.fabric8.kubernetes.api.model.apps.DaemonSetList;
import io.fabric8.kubernetes.client.KubernetesClient;

/**
 * @Author: 伍岳林
 * @Date: 2024/4/2 下午7:28
 * @Description
 */
public class DaemonSetApiService implements IDaemonSetApiService {

    private KubernetesClient client;

    public DaemonSetApiService(KubernetesClient client) {
        this.client = client;
    }

    @Override
    public DaemonSet createDaemonSet(DaemonSet daemonSet) {
        return client.apps().daemonSets().resource(daemonSet).createOrReplace();
    }

    @Override
    public DaemonSet updateDaemonSet(DaemonSet daemonSet) {
        ObjectMeta metadata = daemonSet.getMetadata();
        return client.apps().daemonSets().inNamespace(metadata.getNamespace()).withName(metadata.getName())
            .edit(d -> new DaemonSetBuilder(daemonSet).build());
    }

    @Override
    public DaemonSet queryDaemonSet(String namespace, String daemonSetName) {
        return client.apps().daemonSets().inNamespace(namespace).withName(daemonSetName).get();
    }

    @Override
    public DaemonSetList queryDaemonSets(String namespace) {
        return client.apps().daemonSets().inNamespace(namespace).list();
    }

    @Override
    public Boolean deleteDaemonSet(String namespace, String daemonSetName) {
        return client.apps().daemonSets().inNamespace(namespace).withName(daemonSetName).delete().size() == 1;
    }
}
