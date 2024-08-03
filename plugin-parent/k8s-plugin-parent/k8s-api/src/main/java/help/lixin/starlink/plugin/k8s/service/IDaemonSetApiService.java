package help.lixin.starlink.plugin.k8s.service;

import io.fabric8.kubernetes.api.model.apps.DaemonSet;
import io.fabric8.kubernetes.api.model.apps.DaemonSetList;

public interface IDaemonSetApiService {

    DaemonSet createDaemonSet(DaemonSet daemonSet);

    DaemonSet updateDaemonSet(DaemonSet daemonSet);

    DaemonSet queryDaemonSet(String namespace,String daemonSetName);

    DaemonSetList queryDaemonSets(String namespace);

    Boolean deleteDaemonSet(String namespace,String daemonSetName);
}
