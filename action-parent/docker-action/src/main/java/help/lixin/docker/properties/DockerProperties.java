package help.lixin.docker.properties;

import help.lixin.core.credentials.AbstractCredentials;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("docker")
public class DockerProperties extends AbstractCredentials {
    // 仓库用户名
    private String registryUser;
    // 仓库密码
    private String registryPwd;

    // unix:///var/run/docker.sock
    private String host;

    private String apiVersion;

    private int maxConnections = 2000;

    private int connectionTimeout = 5;

    private int responseTimeout = 5;

    // 是否开启tsl验证
    private boolean dockerTlsVerify = false;

    // 仓库的URL
    public void setRegistryUrl(String url) {
        super.setUrl(url);
    }

    public String getRegistryUrl() {
        return super.getUrl();
    }

    public String getRegistryUser() {
        return registryUser;
    }

    public void setRegistryUser(String registryUser) {
        this.registryUser = registryUser;
    }

    public String getRegistryPwd() {
        return registryPwd;
    }

    public void setRegistryPwd(String registryPwd) {
        this.registryPwd = registryPwd;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isDockerTlsVerify() {
        return dockerTlsVerify;
    }

    public void setDockerTlsVerify(boolean dockerTlsVerify) {
        this.dockerTlsVerify = dockerTlsVerify;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getResponseTimeout() {
        return responseTimeout;
    }

    public void setResponseTimeout(int responseTimeout) {
        this.responseTimeout = responseTimeout;
    }
}
