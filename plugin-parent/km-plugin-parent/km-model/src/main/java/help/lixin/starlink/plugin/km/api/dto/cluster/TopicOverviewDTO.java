package help.lixin.starlink.plugin.km.api.dto.cluster;

import help.lixin.starlink.plugin.km.api.response.MetricLineResponse;
import help.lixin.starlink.plugin.km.api.response.MetricsResponse;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/15 7:12 下午
 * @Description
 */
public class TopicOverviewDTO {

    @ApiModelProperty(value = "Topic名称", example = "know-streaming")
    private String topicName;

    @ApiModelProperty(value = "说明", example = "测试")
    private String description;

    @ApiModelProperty(value = "分区数", example = "3")
    private Integer partitionNum;

    @ApiModelProperty(value = "保存时间(ms)", example = "172800000")
    private Long retentionTimeUnitMs;

    @ApiModelProperty(value = "副本数", example = "2")
    private Integer replicaNum;

    @ApiModelProperty(value = "处于镜像复制中", example = "true")
    private Boolean inMirror;

    @ApiModelProperty(value = "多个指标的当前值, 包括健康分/LogSize等")
    private MetricsResponse latestMetrics;

    @ApiModelProperty(value = "多个指标的历史曲线值，包括LogSize/BytesIn等")
    private List<MetricLineResponse> metricLines;

    @ApiModelProperty(value = "创建时间(ms)", example = "1645608135717")
    private Date createTime;

    @ApiModelProperty(value = "更新时间(ms)", example = "1645608135717")
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

    public MetricsResponse getLatestMetrics() {
        return latestMetrics;
    }

    public void setLatestMetrics(MetricsResponse latestMetrics) {
        this.latestMetrics = latestMetrics;
    }

    public List<MetricLineResponse> getMetricLines() {
        return metricLines;
    }

    public void setMetricLines(List<MetricLineResponse> metricLines) {
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
