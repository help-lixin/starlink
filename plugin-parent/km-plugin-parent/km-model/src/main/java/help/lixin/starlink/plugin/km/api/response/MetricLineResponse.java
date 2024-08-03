package help.lixin.starlink.plugin.km.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "单值指标线，一条线就代表一个指标的多个指标点")
public class MetricLineResponse implements Serializable {
    @ApiModelProperty(value = "指标对象名称：brokerId、topicName")
    private String name;

    @ApiModelProperty(value = "指标名称")
    private String metricName;

    @ApiModelProperty(value = "指标点集合")
    private List<MetricPointResponse> metricPoints;

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

    public List<MetricPointResponse> getMetricPoints() {
        return metricPoints;
    }

    public void setMetricPoints(List<MetricPointResponse> metricPoints) {
        this.metricPoints = metricPoints;
    }
}
