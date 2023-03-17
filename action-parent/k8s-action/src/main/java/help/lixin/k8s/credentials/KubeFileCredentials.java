package help.lixin.k8s.credentials;

import help.lixin.core.credentials.AbstractCredentials;


public class KubeFileCredentials extends AbstractCredentials {
    private String kubeconfigPath;

    public String getKubeconfigPath() {
        return kubeconfigPath;
    }

    public void setKubeconfigPath(String kubeconfigPath) {
        this.kubeconfigPath = kubeconfigPath;
    }
}
