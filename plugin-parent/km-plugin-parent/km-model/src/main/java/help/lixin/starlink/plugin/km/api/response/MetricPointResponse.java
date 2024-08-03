package help.lixin.starlink.plugin.km.api.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


public class MetricPointResponse implements Comparable<MetricPointResponse> , Serializable {
    @ApiModelProperty(value = "指标名", example = "HealthScore")
    private String  name;

    @ApiModelProperty(value = "指标时间，毫秒时间戳", example = "13459484543")
    private Long    timeStamp;

    @ApiModelProperty(value = "指标值", example = "12.345")
    private String  value;

    @ApiModelProperty(value = "指标值聚合方式：avg、max、min、sum")
    private String  aggType;

    public MetricPointResponse(String name, Long timeStamp, String value, String aggType) {
        this.name = name;
        this.timeStamp = timeStamp;
        this.value = value;
        this.aggType = aggType;
    }

    public MetricPointResponse(){}

    @Override
    public int compareTo(MetricPointResponse o) {
        if(null == o){return 0;}
        if(null == this.getTimeStamp()
                || null == o.getTimeStamp()){
            return 0;
        }

        return this.getTimeStamp().intValue() - o.getTimeStamp().intValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAggType() {
        return aggType;
    }

    public void setAggType(String aggType) {
        this.aggType = aggType;
    }
}
