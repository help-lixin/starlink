package help.lixin.starlink.plugin.rocketmq.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Table: rocketmq_topic_route
 */
public class RocketmqTopicRoute implements Serializable {
    /**
     * ID
     *
     * Column:    id
     * Nullable:  false
     */
    private Long id;

    /**
     * topic_id
     *
     * Column:    topic_id
     * Nullable:  true
     */
    private Long topicId;

    /**
     * broker
     *
     * Column:    broker
     * Nullable:  true
     */
    private String broker;

    /**
     * broker地址;broker地址
     *
     * Column:    broker_addrs
     * Nullable:  true
     */
    private String brokerAddrs;

    /**
     * 读队列数量;读队列数量
     *
     * Column:    read_queue_nums
     * Nullable:  true
     */
    private Integer readQueueNums;

    /**
     * 写队列数量;写队列数量
     *
     * Column:    write_queue_nums
     * Nullable:  true
     */
    private Integer writeQueueNums;

    /**
     * perm
     *
     * Column:    perm
     * Nullable:  true
     */
    private Integer perm;

    /**
     * Column:    topic_syn_flag
     * Nullable:  true
     */
    private Byte topicSynFlag;

    /**
     * Column:    cluster
     * Nullable:  true
     */
    private String cluster;

    /**
     * 创建人
     *
     * Column:    create_by
     * Nullable:  true
     */
    private String createBy;

    /**
     * 状态值
     *
     * Column:    status
     * Nullable:  true
     */
    private Byte status;

    /**
     * 更新人
     *
     * Column:    update_by
     * Nullable:  true
     */
    private String updateBy;

    /**
     * 创建时间
     *
     * Column:    created_time
     * Nullable:  true
     */
    private Date createdTime;

    /**
     * 更新时间
     *
     * Column:    updated_time
     * Nullable:  true
     */
    private Date updatedTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker == null ? null : broker.trim();
    }

    public String getBrokerAddrs() {
        return brokerAddrs;
    }

    public void setBrokerAddrs(String brokerAddrs) {
        this.brokerAddrs = brokerAddrs == null ? null : brokerAddrs.trim();
    }

    public Integer getReadQueueNums() {
        return readQueueNums;
    }

    public void setReadQueueNums(Integer readQueueNums) {
        this.readQueueNums = readQueueNums;
    }

    public Integer getWriteQueueNums() {
        return writeQueueNums;
    }

    public void setWriteQueueNums(Integer writeQueueNums) {
        this.writeQueueNums = writeQueueNums;
    }

    public Integer getPerm() {
        return perm;
    }

    public void setPerm(Integer perm) {
        this.perm = perm;
    }

    public Byte getTopicSynFlag() {
        return topicSynFlag;
    }

    public void setTopicSynFlag(Byte topicSynFlag) {
        this.topicSynFlag = topicSynFlag;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster == null ? null : cluster.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}