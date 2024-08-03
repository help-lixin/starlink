package help.lixin.starlink.plugin.km.api.dto.cluster;

import help.lixin.starlink.plugin.km.api.model.JmxConfig;

import java.util.Properties;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/15 10:14 上午
 * @Description
 */
public class ClusterPhyAdd {

    // 集群名称
    protected String name;

    // 描述
    protected String description;

    // 集群的kafka版本
    protected String kafkaVersion;

    // ZK地址, 不允许修改
    protected String zookeeper;

    // bootstrap地址
    protected String bootstrapServers;

    // KM连接集群时使用的客户端配置
    protected Properties clientProperties;

    // Jmx配置
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
