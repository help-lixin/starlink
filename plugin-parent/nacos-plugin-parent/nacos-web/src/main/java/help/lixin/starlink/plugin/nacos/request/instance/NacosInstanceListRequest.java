package help.lixin.starlink.plugin.nacos.request.instance;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/19 12:31 下午
 * @Description
 */
public class NacosInstanceListRequest {

    @ApiModelProperty(value = "命名空间Id，默认为public")
    private String namespaceId;

    @ApiModelProperty(value = "分组名，默认为DEFAULT_GROUP")
    @Value("DEFAULT_GROUP")
    private String groupName;

    @ApiModelProperty(value = "服务名")
    private String serviceName;

    @ApiModelProperty(value = "集群名称，默认为DEFAULT")
    @Value("DEFAULT")
    private String clusterName;

    @ApiModelProperty(value = "IP地址，默认为空，表示不限制IP地址")
    private String ip;

    @ApiModelProperty(value = "端口号，默认为0，表示不限制端口号")
    @Value("0")
    private Integer port;

    @ApiModelProperty(value = "是否只获取健康实例，默认为false")
    @Value("false")
    private Boolean healthyOnly;

    @ApiModelProperty(value = "应用名，默认为空")
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
