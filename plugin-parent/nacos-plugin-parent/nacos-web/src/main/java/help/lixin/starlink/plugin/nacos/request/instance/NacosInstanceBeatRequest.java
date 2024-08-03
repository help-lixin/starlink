package help.lixin.starlink.plugin.nacos.request.instance;

import javax.validation.Valid;

import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/19 11:37 上午
 * @Description
 */
@Api(tags = "发布Beat")
public class NacosInstanceBeatRequest {

    @Valid
    private EnvRequest envRequest;

    @ApiModelProperty(value = "命名空间id")
    private String namespaceId;

    @ApiModelProperty(value = "服务名格式:组名@@服务名")
    private String serviceName;

    @ApiModelProperty(value = "ip地址")
    private String ip;

    @ApiModelProperty(value = "集群名称")
    private String clusterName;

    @ApiModelProperty(value = "端口")
    private Integer port;

    @ApiModelProperty(value = "beat配置")
    private String beat;

    public EnvRequest getEnvRequest() {
        return envRequest;
    }

    public void setEnvRequest(EnvRequest envRequest) {
        this.envRequest = envRequest;
    }

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
