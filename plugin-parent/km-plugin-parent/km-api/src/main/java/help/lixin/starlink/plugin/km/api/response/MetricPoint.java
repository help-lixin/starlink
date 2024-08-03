package help.lixin.starlink.plugin.km.api.response;

import java.io.Serializable;


public class MetricPoint implements Comparable<MetricPoint> , Serializable {
    private String  name;

    private Long    timeStamp;

    private String  value;

    private String  aggType;

    public MetricPoint(String name, Long timeStamp, String value, String aggType) {
        this.name = name;
        this.timeStamp = timeStamp;
        this.value = value;
        this.aggType = aggType;
    }

    public MetricPoint(){}

    @Override
    public int compareTo(MetricPoint o) {
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
