package help.lixin.starlink.plugin.km.api.dto.cluster;

import help.lixin.starlink.plugin.km.api.response.MetricLine;
import help.lixin.starlink.plugin.km.api.response.Metrics;

import java.util.Date;
import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/15 7:12 下午
 * @Description
 */
public class TopicOverview {

    private String topicName;

    private String description;

    private Integer partitionNum;

    private Long retentionTimeUnitMs;

    private Integer replicaNum;

    private Boolean inMirror;

    private Metrics latestMetrics;

    private List<MetricLine> metricLines;

    private Date createTime;

    private Date updateTime;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPartitionNum() {
        return partitionNum;
    }

    public void setPartitionNum(Integer partitionNum) {
        this.partitionNum = partitionNum;
    }

    public Long getRetentionTimeUnitMs() {
        return retentionTimeUnitMs;
    }

    public void setRetentionTimeUnitMs(Long retentionTimeUnitMs) {
        this.retentionTimeUnitMs = retentionTimeUnitMs;
    }

    public Integer getReplicaNum() {
        return replicaNum;
    }

    public void setReplicaNum(Integer replicaNum) {
        this.replicaNum = replicaNum;
    }

    public Boolean getInMirror() {
        return inMirror;
    }

    public void setInMirror(Boolean inMirror) {
        this.inMirror = inMirror;
    }

    public Metrics getLatestMetrics() {
        return latestMetrics;
    }

    public void setLatestMetrics(Metrics latestMetrics) {
        this.latestMetrics = latestMetrics;
    }

    public List<MetricLine> getMetricLines() {
        return metricLines;
    }

    public void setMetricLines(List<MetricLine> metricLines) {
        this.metricLines = metricLines;
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
