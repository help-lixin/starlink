package help.lixin.starlink.plugin.rocketmq.request.topic;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/23 6:03 下午
 * @Description
 */
public class TopicInfoRequest {

    @ApiModelProperty(value = "集群名称列表")
    private List<String> clusterNameList;

    @ApiModelProperty(value = "broker名称列表")
    private List<String> brokerNameList;

    @ApiModelProperty(value = "topic名称")
    private String topicName;

    @ApiModelProperty(value = "写队列数量")
    private Integer writeQueueNums;

    @ApiModelProperty(value = "读队列数量")
    private Integer readQueueNums;

    @ApiModelProperty(value = "perm")
    private Integer perm;

    private Boolean order;

    public List<String> getClusterNameList() {
        return clusterNameList;
    }

    public void setClusterNameList(List<String> clusterNameList) {
        this.clusterNameList = clusterNameList;
    }

    public List<String> getBrokerNameList() {
        return brokerNameList;
    }

    public void setBrokerNameList(List<String> brokerNameList) {
        this.brokerNameList = brokerNameList;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getWriteQueueNums() {
        return writeQueueNums;
    }

    public void setWriteQueueNums(Integer writeQueueNums) {
        this.writeQueueNums = writeQueueNums;
    }

    public Integer getReadQueueNums() {
        return readQueueNums;
    }

    public void setReadQueueNums(Integer readQueueNums) {
        this.readQueueNums = readQueueNums;
    }

    public Integer getPerm() {
        return perm;
    }

    public void setPerm(Integer perm) {
        this.perm = perm;
    }

    public Boolean getOrder() {
        return order;
    }

    public void setOrder(Boolean order) {
        this.order = order;
    }
}
