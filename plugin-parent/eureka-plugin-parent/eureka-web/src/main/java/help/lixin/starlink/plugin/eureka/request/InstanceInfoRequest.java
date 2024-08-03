package help.lixin.starlink.plugin.eureka.request;

import java.io.Serializable;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/26 5:57 下午
 * @Description
 */
@Api(tags = "注册实例请求对象")
public class InstanceInfoRequest implements Serializable {

    @Valid
    private EnvRequest envRequest;

    @ApiModelProperty(value = "（可选）：要注册的服务实例的唯一ID。如果未提供，Eureka服务器将自动生成一个唯一的ID。")
    private String instanceId;

    @NotBlank(message = "应用程序名称不能为空")
    @ApiModelProperty(value = "注册的应用程序名称，它是服务实例所属的应用程序的名称。")
    private String appName;

    @NotBlank(message = "服务实例的IP地址不能为空")
    @ApiModelProperty(value = "服务实例的IP地址。")
    private String ipAddr;

    @NotNull(message = "端口号不能为空")
    @ApiModelProperty(value = "服务实例的端口号。")
    private Integer port;

    @ApiModelProperty(value = "(可选）：服务实例的安全端口号，如果应用程序使用了安全连接。")
    private Integer securePort;

    @ApiModelProperty(value = "（可选）：服务实例的主页URL。")
    private String homePageUrl;

    @ApiModelProperty(value = "（可选）：服务实例的状态页URL。")
    private String statusPageUrl;

    @ApiModelProperty(value = "（可选）：服务实例的健康检查URL。")
    private String healthCheckUrl;

    @ApiModelProperty(value = "（可选）：安全的虚拟IP地址，用于安全连接的服务之间负载均衡。")
    private String secureVipAddress;

    @ApiModelProperty(value = "（可选）：虚拟IP地址，用于服务之间的负载均衡。")
    private String vipAddress;

    @ApiModelProperty(value = "（可选）：服务实例所在的数据中心信息。")
    private DataCenterName dataCenterInfo;

    @NotBlank(message = "主机名不能为空")
    @ApiModelProperty(value = "服务实例的主机名")
    private String hostName;

    @ApiModelProperty(value = "（可选）：元数据。")
    private Map<String, String> metadata;

    // private InstanceStatus overriddenStatus;
    // private InstanceStatus status;
    // private String secureHealthCheckUrl;
    // private String appGroupName;
    // private Set<String> healthCheckUrls;
    // private LeaseInfo leaseInfo;
    // private Long lastUpdatedTimestamp;
    // private Long lastDirtyTimestamp;
    // private ActionType actionType;
    // private String asgName;

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public EnvRequest getEnvRequest() {
        return envRequest;
    }

    public void setEnvRequest(EnvRequest envRequest) {
        this.envRequest = envRequest;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getSecurePort() {
        return securePort;
    }

    public void setSecurePort(Integer securePort) {
        this.securePort = securePort;
    }

    public String getHomePageUrl() {
        return homePageUrl;
    }

    public void setHomePageUrl(String homePageUrl) {
        this.homePageUrl = homePageUrl;
    }

    public String getStatusPageUrl() {
        return statusPageUrl;
    }

    public void setStatusPageUrl(String statusPageUrl) {
        this.statusPageUrl = statusPageUrl;
    }

    public String getHealthCheckUrl() {
        return healthCheckUrl;
    }

    public void setHealthCheckUrl(String healthCheckUrl) {
        this.healthCheckUrl = healthCheckUrl;
    }

    public String getSecureVipAddress() {
        return secureVipAddress;
    }

    public void setSecureVipAddress(String secureVipAddress) {
        this.secureVipAddress = secureVipAddress;
    }

    public String getVipAddress() {
        return vipAddress;
    }

    public void setVipAddress(String vipAddress) {
        this.vipAddress = vipAddress;
    }

    public DataCenterName getDataCenterInfo() {
        return dataCenterInfo;
    }

    public void setDataCenterInfo(DataCenterName dataCenterInfo) {
        this.dataCenterInfo = dataCenterInfo;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}
