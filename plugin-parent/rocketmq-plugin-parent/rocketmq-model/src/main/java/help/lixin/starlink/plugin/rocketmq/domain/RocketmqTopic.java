package help.lixin.starlink.plugin.rocketmq.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Table: rocketmq_topic
 */
public class RocketmqTopic implements Serializable {
    /**
     * ID
     *
     * Column:    id
     * Nullable:  false
     */
    private Long id;

    /**
     * brokerId
     *
     * Column:    broker_id
     * Nullable:  true
     */
    private Long brokerId;

    /**
     * topic名称
     *
     * Column:    topic_name
     * Nullable:  true
     */
    private String topicName;

    /**
     * 写队列数量
     *
     * Column:    write_queue_nums
     * Nullable:  true
     */
    private Integer writeQueueNums;

    /**
     * 读队列数量
     *
     * Column:    read_queue_nums
     * Nullable:  true
     */
    private Integer readQueueNums;

    /**
     * perm
     *
     * Column:    perm
     * Nullable:  true
     */
    private Integer perm;

    /**
     * 是否排序
     *
     * Column:    order
     * Nullable:  true
     */
    private Integer order;

    /**
     * 状态值
     *
     * Column:    status
     * Nullable:  true
     */
    private Integer status;

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

    public Long getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Long brokerId) {
        this.brokerId = brokerId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName == null ? null : topicName.trim();
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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