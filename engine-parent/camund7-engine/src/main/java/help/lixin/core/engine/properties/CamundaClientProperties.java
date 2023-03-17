package help.lixin.core.engine.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("camunda.bpm.client")
public class CamundaClientProperties {
    private String baseUrl;
    private long asyncResponseTimeout = 1000 * 20L;
    private String workerId;
    private CamundaAuthProperties basicAuth;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public long getAsyncResponseTimeout() {
        return asyncResponseTimeout;
    }

    public void setAsyncResponseTimeout(long asyncResponseTimeout) {
        this.asyncResponseTimeout = asyncResponseTimeout;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public CamundaAuthProperties getBasicAuth() {
        return basicAuth;
    }

    public void setBasicAuth(CamundaAuthProperties basicAuth) {
        this.basicAuth = basicAuth;
    }

    @Override
    public String toString() {
        return "CamundaClientProperties{" + "baseUrl='" + baseUrl + '\'' + ", asyncResponseTimeout=" + asyncResponseTimeout + ", workerId='" + workerId + '\'' + ", basicAuth=" + basicAuth + '}';
    }
}
