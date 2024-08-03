package help.lixin.starlink.plugin.rocketmq.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Table: rocketmq_broker_info
 */
public class RocketmqBrokerInfo implements Serializable {
    /**
     * Column:    id
     * Nullable:  false
     */
    private Long id;

    /**
     * 分片名称
     *
     * Column:    broker_name
     * Nullable:  true
     */
    private String brokerName;

    /**
     * 集群id（编号）
     *
     * Column:    cluster_id
     * Nullable:  true
     */
    private Long clusterId;

    /**
     * 客户端地址
     *
     * Column:    client_addr
     * Nullable:  true
     */
    private String clientAddr;

    /**
     * Column:    msg_put_total_today_now
     * Nullable:  true
     */
    private String msgPutTotalTodayNow;

    /**
     * Column:    send_thread_pool_queue_head_wait_time_mills
     * Nullable:  true
     */
    private String sendThreadPoolQueueHeadWaitTimeMills;

    /**
     * Column:    put_message_distribute_time
     * Nullable:  true
     */
    private String putMessageDistributeTime;

    /**
     * Column:    query_thread_pool_queue_head_wait_time_mills
     * Nullable:  true
     */
    private String queryThreadPoolQueueHeadWaitTimeMills;

    /**
     * Column:    remain_how_many_data_to_flush
     * Nullable:  true
     */
    private String remainHowManyDataToFlush;

    /**
     * Column:    msg_get_total_today_now
     * Nullable:  true
     */
    private String msgGetTotalTodayNow;

    /**
     * Column:    query_thread_pool_queue_size
     * Nullable:  true
     */
    private String queryThreadPoolQueueSize;

    /**
     * Column:    boot_timestamp
     * Nullable:  true
     */
    private String bootTimestamp;

    /**
     * Column:    msg_put_total_yesterday_morning
     * Nullable:  true
     */
    private String msgPutTotalYesterdayMorning;

    /**
     * Column:    msg_get_total_yesterday_morning
     * Nullable:  true
     */
    private String msgGetTotalYesterdayMorning;

    /**
     * Column:    pull_thread_pool_queue_size
     * Nullable:  true
     */
    private String pullThreadPoolQueueSize;

    /**
     * Column:    commit_log_min_offset
     * Nullable:  true
     */
    private String commitLogMinOffset;

    /**
     * Column:    pull_thread_pool_queue_head_wait_time_mills
     * Nullable:  true
     */
    private String pullThreadPoolQueueHeadWaitTimeMills;

    /**
     * Column:    runtime
     * Nullable:  true
     */
    private String runtime;

    /**
     * Column:    dispatch_max_buffer
     * Nullable:  true
     */
    private String dispatchMaxBuffer;

    /**
     * Column:    broker_version
     * Nullable:  true
     */
    private String brokerVersion;

    /**
     * Column:    put_tps
     * Nullable:  true
     */
    private String putTps;

    /**
     * Column:    get_miss_tps
     * Nullable:  true
     */
    private String getMissTps;

    /**
     * Column:    get_transfered_tps
     * Nullable:  true
     */
    private String getTransferedTps;

    /**
     * Column:    end_transaction_queue_size
     * Nullable:  true
     */
    private String endTransactionQueueSize;

    /**
     * Column:    get_total_tps
     * Nullable:  true
     */
    private String getTotalTps;

    /**
     * Column:    consume_queue_disk_ratio
     * Nullable:  true
     */
    private String consumeQueueDiskRatio;

    /**
     * Column:    page_cache_lock_time_mills
     * Nullable:  true
     */
    private String pageCacheLockTimeMills;

    /**
     * Column:    commit_log_disk_ratio
     * Nullable:  true
     */
    private String commitLogDiskRatio;

    /**
     * Column:    get_found_tps
     * Nullable:  true
     */
    private String getFoundTps;

    /**
     * Column:    end_transaction_thread_pool_queue_capacity
     * Nullable:  true
     */
    private String endTransactionThreadPoolQueueCapacity;

    /**
     * Column:    commit_log_max_offset
     * Nullable:  true
     */
    private String commitLogMaxOffset;

    /**
     * Column:    get_message_entire_time_max
     * Nullable:  true
     */
    private String getMessageEntireTimeMax;

    /**
     * Column:    msg_put_total_today_morning
     * Nullable:  true
     */
    private String msgPutTotalTodayMorning;

    /**
     * Column:    put_message_times_total
     * Nullable:  true
     */
    private String putMessageTimesTotal;

    /**
     * Column:    msg_get_total_today_morning
     * Nullable:  true
     */
    private String msgGetTotalTodayMorning;

    /**
     * Column:    broker_version_desc
     * Nullable:  true
     */
    private String brokerVersionDesc;

    /**
     * Column:    send_thread_pool_queue_size
     * Nullable:  true
     */
    private String sendThreadPoolQueueSize;

    /**
     * Column:    start_accept_send_request_time_stamp
     * Nullable:  true
     */
    private String startAcceptSendRequestTimeStamp;

    /**
     * Column:    put_message_entire_time_max
     * Nullable:  true
     */
    private String putMessageEntireTimeMax;

    /**
     * Column:    earliest_message_time_stamp
     * Nullable:  true
     */
    private String earliestMessageTimeStamp;

    /**
     * Column:    commit_log_dir_capacity
     * Nullable:  true
     */
    private String commitLogDirCapacity;

    /**
     * Column:    remain_transient_store_buffer_numbs
     * Nullable:  true
     */
    private String remainTransientStoreBufferNumbs;

    /**
     * Column:    query_thread_pool_queue_capacity
     * Nullable:  true
     */
    private String queryThreadPoolQueueCapacity;

    /**
     * Column:    put_message_average_size
     * Nullable:  true
     */
    private String putMessageAverageSize;

    /**
     * Column:    dispatch_behind_bytes
     * Nullable:  true
     */
    private String dispatchBehindBytes;

    /**
     * Column:    put_message_size_total
     * Nullable:  true
     */
    private String putMessageSizeTotal;

    /**
     * Column:    send_thread_pool_queue_capacity
     * Nullable:  true
     */
    private String sendThreadPoolQueueCapacity;

    /**
     * Column:    pull_thread_pool_queue_capacity
     * Nullable:  true
     */
    private String pullThreadPoolQueueCapacity;

    /**
     * Column:    status
     * Nullable:  true
     */
    private Integer status;

    /**
     * Column:    create_by
     * Nullable:  true
     */
    private String createBy;

    /**
     * Column:    update_by
     * Nullable:  true
     */
    private String updateBy;

    /**
     * Column:    create_time
     * Nullable:  true
     */
    private Date createTime;

    /**
     * Column:    update_time
     * Nullable:  true
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName == null ? null : brokerName.trim();
    }

    public Long getClusterId() {
        return clusterId;
    }

    public void setClusterId(Long clusterId) {
        this.clusterId = clusterId;
    }

    public String getClientAddr() {
        return clientAddr;
    }

    public void setClientAddr(String clientAddr) {
        this.clientAddr = clientAddr == null ? null : clientAddr.trim();
    }

    public String getMsgPutTotalTodayNow() {
        return msgPutTotalTodayNow;
    }

    public void setMsgPutTotalTodayNow(String msgPutTotalTodayNow) {
        this.msgPutTotalTodayNow = msgPutTotalTodayNow == null ? null : msgPutTotalTodayNow.trim();
    }

    public String getSendThreadPoolQueueHeadWaitTimeMills() {
        return sendThreadPoolQueueHeadWaitTimeMills;
    }

    public void setSendThreadPoolQueueHeadWaitTimeMills(String sendThreadPoolQueueHeadWaitTimeMills) {
        this.sendThreadPoolQueueHeadWaitTimeMills = sendThreadPoolQueueHeadWaitTimeMills == null ? null : sendThreadPoolQueueHeadWaitTimeMills.trim();
    }

    public String getPutMessageDistributeTime() {
        return putMessageDistributeTime;
    }

    public void setPutMessageDistributeTime(String putMessageDistributeTime) {
        this.putMessageDistributeTime = putMessageDistributeTime == null ? null : putMessageDistributeTime.trim();
    }

    public String getQueryThreadPoolQueueHeadWaitTimeMills() {
        return queryThreadPoolQueueHeadWaitTimeMills;
    }

    public void setQueryThreadPoolQueueHeadWaitTimeMills(String queryThreadPoolQueueHeadWaitTimeMills) {
        this.queryThreadPoolQueueHeadWaitTimeMills = queryThreadPoolQueueHeadWaitTimeMills == null ? null : queryThreadPoolQueueHeadWaitTimeMills.trim();
    }

    public String getRemainHowManyDataToFlush() {
        return remainHowManyDataToFlush;
    }

    public void setRemainHowManyDataToFlush(String remainHowManyDataToFlush) {
        this.remainHowManyDataToFlush = remainHowManyDataToFlush == null ? null : remainHowManyDataToFlush.trim();
    }

    public String getMsgGetTotalTodayNow() {
        return msgGetTotalTodayNow;
    }

    public void setMsgGetTotalTodayNow(String msgGetTotalTodayNow) {
        this.msgGetTotalTodayNow = msgGetTotalTodayNow == null ? null : msgGetTotalTodayNow.trim();
    }

    public String getQueryThreadPoolQueueSize() {
        return queryThreadPoolQueueSize;
    }

    public void setQueryThreadPoolQueueSize(String queryThreadPoolQueueSize) {
        this.queryThreadPoolQueueSize = queryThreadPoolQueueSize == null ? null : queryThreadPoolQueueSize.trim();
    }

    public String getBootTimestamp() {
        return bootTimestamp;
    }

    public void setBootTimestamp(String bootTimestamp) {
        this.bootTimestamp = bootTimestamp == null ? null : bootTimestamp.trim();
    }

    public String getMsgPutTotalYesterdayMorning() {
        return msgPutTotalYesterdayMorning;
    }

    public void setMsgPutTotalYesterdayMorning(String msgPutTotalYesterdayMorning) {
        this.msgPutTotalYesterdayMorning = msgPutTotalYesterdayMorning == null ? null : msgPutTotalYesterdayMorning.trim();
    }

    public String getMsgGetTotalYesterdayMorning() {
        return msgGetTotalYesterdayMorning;
    }

    public void setMsgGetTotalYesterdayMorning(String msgGetTotalYesterdayMorning) {
        this.msgGetTotalYesterdayMorning = msgGetTotalYesterdayMorning == null ? null : msgGetTotalYesterdayMorning.trim();
    }

    public String getPullThreadPoolQueueSize() {
        return pullThreadPoolQueueSize;
    }

    public void setPullThreadPoolQueueSize(String pullThreadPoolQueueSize) {
        this.pullThreadPoolQueueSize = pullThreadPoolQueueSize == null ? null : pullThreadPoolQueueSize.trim();
    }

    public String getCommitLogMinOffset() {
        return commitLogMinOffset;
    }

    public void setCommitLogMinOffset(String commitLogMinOffset) {
        this.commitLogMinOffset = commitLogMinOffset == null ? null : commitLogMinOffset.trim();
    }

    public String getPullThreadPoolQueueHeadWaitTimeMills() {
        return pullThreadPoolQueueHeadWaitTimeMills;
    }

    public void setPullThreadPoolQueueHeadWaitTimeMills(String pullThreadPoolQueueHeadWaitTimeMills) {
        this.pullThreadPoolQueueHeadWaitTimeMills = pullThreadPoolQueueHeadWaitTimeMills == null ? null : pullThreadPoolQueueHeadWaitTimeMills.trim();
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime == null ? null : runtime.trim();
    }

    public String getDispatchMaxBuffer() {
        return dispatchMaxBuffer;
    }

    public void setDispatchMaxBuffer(String dispatchMaxBuffer) {
        this.dispatchMaxBuffer = dispatchMaxBuffer == null ? null : dispatchMaxBuffer.trim();
    }

    public String getBrokerVersion() {
        return brokerVersion;
    }

    public void setBrokerVersion(String brokerVersion) {
        this.brokerVersion = brokerVersion == null ? null : brokerVersion.trim();
    }

    public String getPutTps() {
        return putTps;
    }

    public void setPutTps(String putTps) {
        this.putTps = putTps == null ? null : putTps.trim();
    }

    public String getGetMissTps() {
        return getMissTps;
    }

    public void setGetMissTps(String getMissTps) {
        this.getMissTps = getMissTps == null ? null : getMissTps.trim();
    }

    public String getGetTransferedTps() {
        return getTransferedTps;
    }

    public void setGetTransferedTps(String getTransferedTps) {
        this.getTransferedTps = getTransferedTps == null ? null : getTransferedTps.trim();
    }

    public String getEndTransactionQueueSize() {
        return endTransactionQueueSize;
    }

    public void setEndTransactionQueueSize(String endTransactionQueueSize) {
        this.endTransactionQueueSize = endTransactionQueueSize == null ? null : endTransactionQueueSize.trim();
    }

    public String getGetTotalTps() {
        return getTotalTps;
    }

    public void setGetTotalTps(String getTotalTps) {
        this.getTotalTps = getTotalTps == null ? null : getTotalTps.trim();
    }

    public String getConsumeQueueDiskRatio() {
        return consumeQueueDiskRatio;
    }

    public void setConsumeQueueDiskRatio(String consumeQueueDiskRatio) {
        this.consumeQueueDiskRatio = consumeQueueDiskRatio == null ? null : consumeQueueDiskRatio.trim();
    }

    public String getPageCacheLockTimeMills() {
        return pageCacheLockTimeMills;
    }

    public void setPageCacheLockTimeMills(String pageCacheLockTimeMills) {
        this.pageCacheLockTimeMills = pageCacheLockTimeMills == null ? null : pageCacheLockTimeMills.trim();
    }

    public String getCommitLogDiskRatio() {
        return commitLogDiskRatio;
    }

    public void setCommitLogDiskRatio(String commitLogDiskRatio) {
        this.commitLogDiskRatio = commitLogDiskRatio == null ? null : commitLogDiskRatio.trim();
    }

    public String getGetFoundTps() {
        return getFoundTps;
    }

    public void setGetFoundTps(String getFoundTps) {
        this.getFoundTps = getFoundTps == null ? null : getFoundTps.trim();
    }

    public String getEndTransactionThreadPoolQueueCapacity() {
        return endTransactionThreadPoolQueueCapacity;
    }

    public void setEndTransactionThreadPoolQueueCapacity(String endTransactionThreadPoolQueueCapacity) {
        this.endTransactionThreadPoolQueueCapacity = endTransactionThreadPoolQueueCapacity == null ? null : endTransactionThreadPoolQueueCapacity.trim();
    }

    public String getCommitLogMaxOffset() {
        return commitLogMaxOffset;
    }

    public void setCommitLogMaxOffset(String commitLogMaxOffset) {
        this.commitLogMaxOffset = commitLogMaxOffset == null ? null : commitLogMaxOffset.trim();
    }

    public String getGetMessageEntireTimeMax() {
        return getMessageEntireTimeMax;
    }

    public void setGetMessageEntireTimeMax(String getMessageEntireTimeMax) {
        this.getMessageEntireTimeMax = getMessageEntireTimeMax == null ? null : getMessageEntireTimeMax.trim();
    }

    public String getMsgPutTotalTodayMorning() {
        return msgPutTotalTodayMorning;
    }

    public void setMsgPutTotalTodayMorning(String msgPutTotalTodayMorning) {
        this.msgPutTotalTodayMorning = msgPutTotalTodayMorning == null ? null : msgPutTotalTodayMorning.trim();
    }

    public String getPutMessageTimesTotal() {
        return putMessageTimesTotal;
    }

    public void setPutMessageTimesTotal(String putMessageTimesTotal) {
        this.putMessageTimesTotal = putMessageTimesTotal == null ? null : putMessageTimesTotal.trim();
    }

    public String getMsgGetTotalTodayMorning() {
        return msgGetTotalTodayMorning;
    }

    public void setMsgGetTotalTodayMorning(String msgGetTotalTodayMorning) {
        this.msgGetTotalTodayMorning = msgGetTotalTodayMorning == null ? null : msgGetTotalTodayMorning.trim();
    }

    public String getBrokerVersionDesc() {
        return brokerVersionDesc;
    }

    public void setBrokerVersionDesc(String brokerVersionDesc) {
        this.brokerVersionDesc = brokerVersionDesc == null ? null : brokerVersionDesc.trim();
    }

    public String getSendThreadPoolQueueSize() {
        return sendThreadPoolQueueSize;
    }

    public void setSendThreadPoolQueueSize(String sendThreadPoolQueueSize) {
        this.sendThreadPoolQueueSize = sendThreadPoolQueueSize == null ? null : sendThreadPoolQueueSize.trim();
    }

    public String getStartAcceptSendRequestTimeStamp() {
        return startAcceptSendRequestTimeStamp;
    }

    public void setStartAcceptSendRequestTimeStamp(String startAcceptSendRequestTimeStamp) {
        this.startAcceptSendRequestTimeStamp = startAcceptSendRequestTimeStamp == null ? null : startAcceptSendRequestTimeStamp.trim();
    }

    public String getPutMessageEntireTimeMax() {
        return putMessageEntireTimeMax;
    }

    public void setPutMessageEntireTimeMax(String putMessageEntireTimeMax) {
        this.putMessageEntireTimeMax = putMessageEntireTimeMax == null ? null : putMessageEntireTimeMax.trim();
    }

    public String getEarliestMessageTimeStamp() {
        return earliestMessageTimeStamp;
    }

    public void setEarliestMessageTimeStamp(String earliestMessageTimeStamp) {
        this.earliestMessageTimeStamp = earliestMessageTimeStamp == null ? null : earliestMessageTimeStamp.trim();
    }

    public String getCommitLogDirCapacity() {
        return commitLogDirCapacity;
    }

    public void setCommitLogDirCapacity(String commitLogDirCapacity) {
        this.commitLogDirCapacity = commitLogDirCapacity == null ? null : commitLogDirCapacity.trim();
    }

    public String getRemainTransientStoreBufferNumbs() {
        return remainTransientStoreBufferNumbs;
    }

    public void setRemainTransientStoreBufferNumbs(String remainTransientStoreBufferNumbs) {
        this.remainTransientStoreBufferNumbs = remainTransientStoreBufferNumbs == null ? null : remainTransientStoreBufferNumbs.trim();
    }

    public String getQueryThreadPoolQueueCapacity() {
        return queryThreadPoolQueueCapacity;
    }

    public void setQueryThreadPoolQueueCapacity(String queryThreadPoolQueueCapacity) {
        this.queryThreadPoolQueueCapacity = queryThreadPoolQueueCapacity == null ? null : queryThreadPoolQueueCapacity.trim();
    }

    public String getPutMessageAverageSize() {
        return putMessageAverageSize;
    }

    public void setPutMessageAverageSize(String putMessageAverageSize) {
        this.putMessageAverageSize = putMessageAverageSize == null ? null : putMessageAverageSize.trim();
    }

    public String getDispatchBehindBytes() {
        return dispatchBehindBytes;
    }

    public void setDispatchBehindBytes(String dispatchBehindBytes) {
        this.dispatchBehindBytes = dispatchBehindBytes == null ? null : dispatchBehindBytes.trim();
    }

    public String getPutMessageSizeTotal() {
        return putMessageSizeTotal;
    }

    public void setPutMessageSizeTotal(String putMessageSizeTotal) {
        this.putMessageSizeTotal = putMessageSizeTotal == null ? null : putMessageSizeTotal.trim();
    }

    public String getSendThreadPoolQueueCapacity() {
        return sendThreadPoolQueueCapacity;
    }

    public void setSendThreadPoolQueueCapacity(String sendThreadPoolQueueCapacity) {
        this.sendThreadPoolQueueCapacity = sendThreadPoolQueueCapacity == null ? null : sendThreadPoolQueueCapacity.trim();
    }

    public String getPullThreadPoolQueueCapacity() {
        return pullThreadPoolQueueCapacity;
    }

    public void setPullThreadPoolQueueCapacity(String pullThreadPoolQueueCapacity) {
        this.pullThreadPoolQueueCapacity = pullThreadPoolQueueCapacity == null ? null : pullThreadPoolQueueCapacity.trim();
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