package help.lixin.starlink.plugin.rocketmq.api.dto.topic;


import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/23 6:03 下午
 * @Description
 */
public class TopicInfoDTO {

    private List<String> clusterNameList;

    private List<String> brokerNameList;

    private String topicName;

    private Integer writeQueueNums;

    private Integer readQueueNums;

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
