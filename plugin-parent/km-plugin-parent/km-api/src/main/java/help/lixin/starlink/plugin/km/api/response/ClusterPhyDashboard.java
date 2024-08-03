package help.lixin.starlink.plugin.km.api.response;

import java.io.Serializable;
import java.util.List;

public class ClusterPhyDashboard extends ClusterPhyBase implements Serializable {
    private BaseMetrics latestMetrics;

    private List<MetricLine> metricLines;

    private Integer alive;

    public BaseMetrics getLatestMetrics() {
        return latestMetrics;
    }

    public void setLatestMetrics(BaseMetrics latestMetrics) {
        this.latestMetrics = latestMetrics;
    }

    public List<MetricLine> getMetricLines() {
        return metricLines;
    }

    public void setMetricLines(List<MetricLine> metricLines) {
        this.metricLines = metricLines;
    }

    public Integer getAlive() {
        return alive;
    }

    public void setAlive(Integer alive) {
        this.alive = alive;
    }
}
