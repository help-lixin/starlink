package help.lixin.starlink.plugin.km.api.dto.cluster;

import java.util.List;

/**
 * @author didi
 */

public class Metric {

    private Long startTime;

    private Long endTime;

    private String aggType = "avg";

    private List<String> metricsNames;

    private Integer topNu = 5;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getAggType() {
        return aggType;
    }

    public void setAggType(String aggType) {
        this.aggType = aggType;
    }

    public List<String> getMetricsNames() {
        return metricsNames;
    }

    public void setMetricsNames(List<String> metricsNames) {
        this.metricsNames = metricsNames;
    }

    public Integer getTopNu() {
        return topNu;
    }

    public void setTopNu(Integer topNu) {
        this.topNu = topNu;
    }
}
