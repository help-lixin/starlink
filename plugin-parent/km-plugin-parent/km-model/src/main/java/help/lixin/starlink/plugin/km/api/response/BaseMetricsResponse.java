package help.lixin.starlink.plugin.km.api.response;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BaseMetricsResponse implements Serializable {
    protected Long  clusterPhyId;

    protected Long  timestamp = System.currentTimeMillis() - System.currentTimeMillis() % 60000;

    protected Map<String, Float> metrics = new ConcurrentHashMap<>();

    protected String kafkaVersion;

    public String getKafkaVersion() {
        return kafkaVersion;
    }

    public void setKafkaVersion(String kafkaVersion) {
        this.kafkaVersion = kafkaVersion;
    }

    public Long getClusterPhyId() {
        return clusterPhyId;
    }

    public void setClusterPhyId(Long clusterPhyId) {
        this.clusterPhyId = clusterPhyId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Float> getMetrics() {
        return metrics;
    }

    public void setMetrics(Map<String, Float> metrics) {
        this.metrics = metrics;
    }
}