package help.lixin.starlink.plugin.km.api.response;

import java.io.Serializable;
import java.util.List;

public class MetricLine implements Serializable {
    private String name;

    private String metricName;

    private List<MetricPoint> metricPoints;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public List<MetricPoint> getMetricPoints() {
        return metricPoints;
    }

    public void setMetricPoints(List<MetricPoint> metricPoints) {
        this.metricPoints = metricPoints;
    }
}
