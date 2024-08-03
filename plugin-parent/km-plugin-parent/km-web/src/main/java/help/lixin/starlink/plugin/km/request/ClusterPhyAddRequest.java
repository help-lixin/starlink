package help.lixin.starlink.plugin.km.request;

import help.lixin.starlink.plugin.km.api.model.JmxConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Properties;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/15 10:14 上午
 * @Description
 */
@Api(tags = "添加集群请求")
public class ClusterPhyAddRequest {

    @NotBlank(message = "name不允许为空串")
    @ApiModelProperty(value="集群名称", example = "KnowStreaming")
    protected String name;

    @NotNull(message = "description不允许为空")
    @ApiModelProperty(value="描述", example = "测试")
    protected String description;

    @NotBlank(message = "kafkaVersion不允许为空")
    @ApiModelProperty(value="集群的kafka版本", example = "2.5.1")
    protected String kafkaVersion;

    @NotNull(message = "zookeeper不允许为null")
    @ApiModelProperty(value="ZK地址, 不允许修改", example = "127.0.0.1:2181")
    protected String zookeeper;

    @NotBlank(message = "bootstrapServers不允许为空串")
    @ApiModelProperty(value="bootstrap地址", example = "127.0.0.1:9093")
    protected String bootstrapServers;

    @NotNull(message = "clientProperties不允许为空")
    @ApiModelProperty(value="KM连接集群时使用的客户端配置")
    protected Properties clientProperties;

    @NotNull(message = "jmxProperties不允许为空")
    @ApiModelProperty(value="Jmx配置")
    protected JmxConfig jmxProperties;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKafkaVersion() {
        return kafkaVersion;
    }

    public void setKafkaVersion(String kafkaVersion) {
        this.kafkaVersion = kafkaVersion;
    }

    public String getZookeeper() {
        return zookeeper;
    }

    public void setZookeeper(String zookeeper) {
        this.zookeeper = zookeeper;
    }

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public Properties getClientProperties() {
        return clientProperties;
    }

    public void setClientProperties(Properties clientProperties) {
        this.clientProperties = clientProperties;
    }

    public JmxConfig getJmxProperties() {
        return jmxProperties;
    }

    public void setJmxProperties(JmxConfig jmxProperties) {
        this.jmxProperties = jmxProperties;
    }
}
