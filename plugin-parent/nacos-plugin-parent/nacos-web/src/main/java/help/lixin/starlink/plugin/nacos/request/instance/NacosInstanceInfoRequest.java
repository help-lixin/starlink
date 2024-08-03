/*
 * Copyright 1999-2022 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package help.lixin.starlink.plugin.nacos.request.instance;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;

import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * InstanceForm.
 * 
 * @author dongyafei
 * @date 2022/9/7
 */
@Api(tags = "服务管理实例请求对象")
public class NacosInstanceInfoRequest implements Serializable {

    private static final long serialVersionUID = -3760300561436525429L;

    @Valid
    private EnvRequest envRequest;

    @ApiModelProperty(value = "命名空间id")
    private String namespaceId;

    @ApiModelProperty(value = "命名空间id")
    @Value("DEFAULT_GROUP")
    private String groupName;

    @ApiModelProperty(value = "服务名")
    private String serviceName;

    @ApiModelProperty(value = "IP地址")
    private String ip;

    @ApiModelProperty(value = "集群名称，默认为DEFAULT")
    @Value("默认为DEFAULT")
    private String clusterName;

    @ApiModelProperty(value = "端口号")
    private Integer port;

    @ApiModelProperty(value = "是否只查找健康实例，默认为true")
    @Value("true")
    private Boolean healthy;

    @ApiModelProperty(value = "实例权重，默认为1.0")
    @Value("1.0")
    private Double weight;

    @ApiModelProperty(value = "是否可用，默认为true")
    @Value("true")
    private Boolean enabled = true;

    @ApiModelProperty(value = "实例元数据")
    private String metadata;

    @ApiModelProperty(value = "是否为临时实例")
    private Boolean ephemeral;

    public NacosInstanceInfoRequest() {}

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

    public Boolean getHealthy() {
        return healthy;
    }

    public void setHealthy(Boolean healthy) {
        this.healthy = healthy;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Boolean getEphemeral() {
        return ephemeral;
    }

    public void setEphemeral(Boolean ephemeral) {
        this.ephemeral = ephemeral;
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespaceId, groupName, serviceName, ip, clusterName, port, healthy, weight, enabled,
            metadata, ephemeral);
    }

    @Override
    public String toString() {
        return "InstanceForm{" + "namespaceId='" + namespaceId + '\'' + ", groupName='" + groupName + '\''
            + ", serviceName='" + serviceName + '\'' + ", ip='" + ip + '\'' + ", clusterName='" + clusterName + '\''
            + ", port=" + port + ", healthy=" + healthy + ", weight=" + weight + ", enabled=" + enabled + ", metadata='"
            + metadata + '\'' + ", ephemeral=" + ephemeral + '}';
    }
}
