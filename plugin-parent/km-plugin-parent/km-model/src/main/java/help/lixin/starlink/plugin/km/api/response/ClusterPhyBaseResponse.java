package help.lixin.starlink.plugin.km.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;


@ApiModel(description = "集群信息")
public class ClusterPhyBaseResponse {
    @ApiModelProperty(value="集群ID", example = "1")
    protected Long id;

    @ApiModelProperty(value="集群名称", example = "KnowStreaming")
    protected String name;

    @ApiModelProperty(value="ZK地址, 不允许修改", example = "127.0.0.1:2181")
    protected String zookeeper;

    @ApiModelProperty(value="bootstrap地址", example = "127.0.0.1:9093")
    protected String bootstrapServers;

    @ApiModelProperty(value="KM连接集群时使用的客户端配置", example = "{}")
    protected String clientProperties;

    @ApiModelProperty(value="Jmx配置", example = "{}")
    protected String jmxProperties;

    @ApiModelProperty(value="ZK配置", example = "{}")
    protected String zkProperties;

    @ApiModelProperty(value="描述", example = "测试")
    protected String description;

    @ApiModelProperty(value="集群的kafka版本", example = "2.5.1")
    protected String kafkaVersion;

    @ApiModelProperty(value="集群的运行模式", example = "2：raft模式，其他是ZK模式")
    private Integer runState;

    @ApiModelProperty(value = "创建时间(ms)", example = "1645608135717")
    private Date createTime;

    @ApiModelProperty(value = "更新时间(ms)", example = "1645608135717")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getClientProperties() {
        return clientProperties;
    }

    public void setClientProperties(String clientProperties) {
        this.clientProperties = clientProperties;
    }

    public String getJmxProperties() {
        return jmxProperties;
    }

    public void setJmxProperties(String jmxProperties) {
        this.jmxProperties = jmxProperties;
    }

    public String getZkProperties() {
        return zkProperties;
    }

    public void setZkProperties(String zkProperties) {
        this.zkProperties = zkProperties;
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

    public Integer getRunState() {
        return runState;
    }

    public void setRunState(Integer runState) {
        this.runState = runState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
