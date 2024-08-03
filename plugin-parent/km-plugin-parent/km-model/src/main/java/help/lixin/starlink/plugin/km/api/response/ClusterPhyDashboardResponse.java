package help.lixin.starlink.plugin.km.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "集群大盘信息")
public class ClusterPhyDashboardResponse extends ClusterPhyBaseResponse implements Serializable {
    @ApiModelProperty(value = "多个指标的当前值, 包括健康分/Brokers/LogSize等")
    private BaseMetricsResponse latestMetrics;

    @ApiModelProperty(value = "多个指标的历史曲线值，包括LogSize/BytesIn等")
    private List<MetricLineResponse> metricLines;

    @ApiModelProperty(value="是否存活，1：Live，0：Down", example = "1")
    private Integer alive;

    public BaseMetricsResponse getLatestMetrics() {
        return latestMetrics;
    }

    public void setLatestMetrics(BaseMetricsResponse latestMetrics) {
        this.latestMetrics = latestMetrics;
    }

    public List<MetricLineResponse> getMetricLines() {
        return metricLines;
    }

    public void setMetricLines(List<MetricLineResponse> metricLines) {
        this.metricLines = metricLines;
    }

    public Integer getAlive() {
        return alive;
    }

    public void setAlive(Integer alive) {
        this.alive = alive;
    }
}
