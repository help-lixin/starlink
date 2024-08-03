package help.lixin.starlink.plugin.rocketmq.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Table: rocketmq_topic_sub_group
 */
public class RocketmqTopicSubGroup implements Serializable {
    /**
     * ID
     *
     * Column:    id
     * Nullable:  false
     */
    private Long id;

    /**
     * Column:    topic_id
     * Nullable:  true
     */
    private Long topicId;

    /**
     * 订阅组名称
     *
     * Column:    sub_group_name
     * Nullable:  true
     */
    private String subGroupName;

    /**
     * 延迟
     *
     * Column:    diff_total
     * Nullable:  true
     */
    private Integer diffTotal;

    /**
     * 最后消费时间
     *
     * Column:    last_time
     * Nullable:  true
     */
    private Date lastTime;

    /**
     * broker名称
     *
     * Column:    broker_name
     * Nullable:  true
     */
    private String brokerName;

    /**
     * 队列id
     *
     * Column:    queue_id
     * Nullable:  true
     */
    private Long queueId;

    /**
     * 消费者终端
     *
     * Column:    client_info
     * Nullable:  true
     */
    private String clientInfo;

    /**
     * broker位点
     *
     * Column:    broker_offset
     * Nullable:  true
     */
    private Integer brokerOffset;

    /**
     * 消费者位点
     *
     * Column:    consumer_offset
     * Nullable:  true
     */
    private Integer consumerOffset;

    /**
     * 消费时间
     *
     * Column:    consumer_last_time
     * Nullable:  true
     */
    private Date consumerLastTime;

    /**
     * 状态值
     *
     * Column:    status
     * Nullable:  true
     */
    private Byte status;

    /**
     * 创建人
     *
     * Column:    create_by
     * Nullable:  true
     */
    private String createBy;

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

    public String getSubGroupName() {
        return subGroupName;
    }

    public void setSubGroupName(String subGroupName) {
        this.subGroupName = subGroupName == null ? null : subGroupName.trim();
    }

    public Integer getDiffTotal() {
        return diffTotal;
    }

    public void setDiffTotal(Integer diffTotal) {
        this.diffTotal = diffTotal;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName == null ? null : brokerName.trim();
    }

    public Long getQueueId() {
        return queueId;
    }

    public void setQueueId(Long queueId) {
        this.queueId = queueId;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo == null ? null : clientInfo.trim();
    }

    public Integer getBrokerOffset() {
        return brokerOffset;
    }

    public void setBrokerOffset(Integer brokerOffset) {
        this.brokerOffset = brokerOffset;
    }

    public Integer getConsumerOffset() {
        return consumerOffset;
    }

    public void setConsumerOffset(Integer consumerOffset) {
        this.consumerOffset = consumerOffset;
    }

    public Date getConsumerLastTime() {
        return consumerLastTime;
    }

    public void setConsumerLastTime(Date consumerLastTime) {
        this.consumerLastTime = consumerLastTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
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