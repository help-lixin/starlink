package help.lixin.starlink.plugin.nacos.api.model.instance;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/19 11:37 上午
 * @Description
 */
public class NacosInstanceBeat {
     private String namespaceId;
     private String serviceName;
     private String ip;
     private String clusterName;
     private Integer port;
     private String beat;

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getBeat() {
        return beat;
    }

    public void setBeat(String beat) {
        this.beat = beat;
    }
}
