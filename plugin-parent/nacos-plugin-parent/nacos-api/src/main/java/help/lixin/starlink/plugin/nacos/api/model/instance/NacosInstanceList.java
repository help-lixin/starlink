package help.lixin.starlink.plugin.nacos.api.model.instance;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/19 12:31 下午
 * @Description
 */
public class NacosInstanceList {

     private String namespaceId;
     private String groupName;
     private String serviceName;
     private String clusterName;
     private String ip;
     private Integer port;
     private Boolean healthyOnly;
     private String app;
     private String userAgent;
     private String clientVersion;

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Boolean getHealthyOnly() {
        return healthyOnly;
    }

    public void setHealthyOnly(Boolean healthyOnly) {
        this.healthyOnly = healthyOnly;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }
}
