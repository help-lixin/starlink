package help.lixin.starlink.plugin.k8s.credentials;

import help.lixin.starlink.core.credentials.AbstractCredentials;


public class KubeFileCredentials extends AbstractCredentials {
    protected String apiVersion;
    protected String caCertData;
    protected String clientCertData;
    protected String clientKeyData;

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getCaCertData() {
        return caCertData;
    }

    public void setCaCertData(String caCertData) {
        this.caCertData = caCertData;
    }

    public String getClientCertData() {
        return clientCertData;
    }

    public void setClientCertData(String clientCertData) {
        this.clientCertData = clientCertData;
    }

    public String getClientKeyData() {
        return clientKeyData;
    }

    public void setClientKeyData(String clientKeyData) {
        this.clientKeyData = clientKeyData;
    }
}
