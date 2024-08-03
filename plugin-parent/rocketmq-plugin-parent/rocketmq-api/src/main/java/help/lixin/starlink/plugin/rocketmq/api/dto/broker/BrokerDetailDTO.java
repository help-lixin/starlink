package help.lixin.starlink.plugin.rocketmq.api.dto.broker;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/24 6:54 下午
 * @Description
 */
public class BrokerDetailDTO {
    private String msgPutTotalTodayNow;
    private String commitLogDiskRatio;
    private String getFoundTps;

    @JsonProperty("EndTransactionThreadPoolQueueCapacity")
    private String endTransactionThreadPoolQueueCapacity;
    private String sendThreadPoolQueueHeadWaitTimeMills;
    private String putMessageDistributeTime;
    private String queryThreadPoolQueueHeadWaitTimeMills;
    private String remainHowManyDataToFlush;
    private String msgGetTotalTodayNow;
    private String commitLogMaxOffset;
    private String queryThreadPoolQueueSize;
    private String getMessageEntireTimeMax;
    private String msgPutTotalTodayMorning;
    private String putMessageTimesTotal;
    private String bootTimestamp;
    private String msgGetTotalTodayMorning;
    private String msgPutTotalYesterdayMorning;
    private String msgGetTotalYesterdayMorning;
    private String pullThreadPoolQueueSize;
    private String brokerVersionDesc;
    private String sendThreadPoolQueueSize;
    private String startAcceptSendRequestTimeStamp;
    private String commitLogMinOffset;
    private String putMessageEntireTimeMax;
    private String pullThreadPoolQueueHeadWaitTimeMills;
    private String runtime;
    private String earliestMessageTimeStamp;
    private String commitLogDirCapacity;
    private String dispatchMaxBuffer;
    private String brokerVersion;
    private String putTps;
    private String remainTransientStoreBufferNumbs;
    private String getMissTps;
    private String queryThreadPoolQueueCapacity;
    private String putMessageAverageSize;
    private String getTransferedTps;
    private String dispatchBehindBytes;
    private String putMessageSizeTotal;
    private String sendThreadPoolQueueCapacity;
    @JsonProperty("EndTransactionQueueSize")
    private String endTransactionQueueSize;
    private String getTotalTps;
    private String pullThreadPoolQueueCapacity;
    private String consumeQueueDiskRatio;
    private String pageCacheLockTimeMills;

    public String getMsgPutTotalTodayNow() {
        return msgPutTotalTodayNow;
    }

    public void setMsgPutTotalTodayNow(String msgPutTotalTodayNow) {
        this.msgPutTotalTodayNow = msgPutTotalTodayNow;
    }

    public String getCommitLogDiskRatio() {
        return commitLogDiskRatio;
    }

    public void setCommitLogDiskRatio(String commitLogDiskRatio) {
        this.commitLogDiskRatio = commitLogDiskRatio;
    }

    public String getGetFoundTps() {
        return getFoundTps;
    }

    public void setGetFoundTps(String getFoundTps) {
        this.getFoundTps = getFoundTps;
    }


    public String getSendThreadPoolQueueHeadWaitTimeMills() {
        return sendThreadPoolQueueHeadWaitTimeMills;
    }

    public void setSendThreadPoolQueueHeadWaitTimeMills(String sendThreadPoolQueueHeadWaitTimeMills) {
        this.sendThreadPoolQueueHeadWaitTimeMills = sendThreadPoolQueueHeadWaitTimeMills;
    }

    public String getPutMessageDistributeTime() {
        return putMessageDistributeTime;
    }

    public void setPutMessageDistributeTime(String putMessageDistributeTime) {
        this.putMessageDistributeTime = putMessageDistributeTime;
    }

    public String getQueryThreadPoolQueueHeadWaitTimeMills() {
        return queryThreadPoolQueueHeadWaitTimeMills;
    }

    public void setQueryThreadPoolQueueHeadWaitTimeMills(String queryThreadPoolQueueHeadWaitTimeMills) {
        this.queryThreadPoolQueueHeadWaitTimeMills = queryThreadPoolQueueHeadWaitTimeMills;
    }

    public String getRemainHowManyDataToFlush() {
        return remainHowManyDataToFlush;
    }

    public void setRemainHowManyDataToFlush(String remainHowManyDataToFlush) {
        this.remainHowManyDataToFlush = remainHowManyDataToFlush;
    }

    public String getMsgGetTotalTodayNow() {
        return msgGetTotalTodayNow;
    }

    public void setMsgGetTotalTodayNow(String msgGetTotalTodayNow) {
        this.msgGetTotalTodayNow = msgGetTotalTodayNow;
    }

    public String getCommitLogMaxOffset() {
        return commitLogMaxOffset;
    }

    public void setCommitLogMaxOffset(String commitLogMaxOffset) {
        this.commitLogMaxOffset = commitLogMaxOffset;
    }

    public String getQueryThreadPoolQueueSize() {
        return queryThreadPoolQueueSize;
    }

    public void setQueryThreadPoolQueueSize(String queryThreadPoolQueueSize) {
        this.queryThreadPoolQueueSize = queryThreadPoolQueueSize;
    }

    public String getGetMessageEntireTimeMax() {
        return getMessageEntireTimeMax;
    }

    public void setGetMessageEntireTimeMax(String getMessageEntireTimeMax) {
        this.getMessageEntireTimeMax = getMessageEntireTimeMax;
    }

    public String getMsgPutTotalTodayMorning() {
        return msgPutTotalTodayMorning;
    }

    public void setMsgPutTotalTodayMorning(String msgPutTotalTodayMorning) {
        this.msgPutTotalTodayMorning = msgPutTotalTodayMorning;
    }

    public String getPutMessageTimesTotal() {
        return putMessageTimesTotal;
    }

    public void setPutMessageTimesTotal(String putMessageTimesTotal) {
        this.putMessageTimesTotal = putMessageTimesTotal;
    }

    public String getBootTimestamp() {
        return bootTimestamp;
    }

    public void setBootTimestamp(String bootTimestamp) {
        this.bootTimestamp = bootTimestamp;
    }

    public String getMsgGetTotalTodayMorning() {
        return msgGetTotalTodayMorning;
    }

    public void setMsgGetTotalTodayMorning(String msgGetTotalTodayMorning) {
        this.msgGetTotalTodayMorning = msgGetTotalTodayMorning;
    }

    public String getMsgPutTotalYesterdayMorning() {
        return msgPutTotalYesterdayMorning;
    }

    public void setMsgPutTotalYesterdayMorning(String msgPutTotalYesterdayMorning) {
        this.msgPutTotalYesterdayMorning = msgPutTotalYesterdayMorning;
    }

    public String getMsgGetTotalYesterdayMorning() {
        return msgGetTotalYesterdayMorning;
    }

    public void setMsgGetTotalYesterdayMorning(String msgGetTotalYesterdayMorning) {
        this.msgGetTotalYesterdayMorning = msgGetTotalYesterdayMorning;
    }

    public String getPullThreadPoolQueueSize() {
        return pullThreadPoolQueueSize;
    }

    public void setPullThreadPoolQueueSize(String pullThreadPoolQueueSize) {
        this.pullThreadPoolQueueSize = pullThreadPoolQueueSize;
    }

    public String getBrokerVersionDesc() {
        return brokerVersionDesc;
    }

    public void setBrokerVersionDesc(String brokerVersionDesc) {
        this.brokerVersionDesc = brokerVersionDesc;
    }

    public String getSendThreadPoolQueueSize() {
        return sendThreadPoolQueueSize;
    }

    public void setSendThreadPoolQueueSize(String sendThreadPoolQueueSize) {
        this.sendThreadPoolQueueSize = sendThreadPoolQueueSize;
    }

    public String getStartAcceptSendRequestTimeStamp() {
        return startAcceptSendRequestTimeStamp;
    }

    public void setStartAcceptSendRequestTimeStamp(String startAcceptSendRequestTimeStamp) {
        this.startAcceptSendRequestTimeStamp = startAcceptSendRequestTimeStamp;
    }

    public String getCommitLogMinOffset() {
        return commitLogMinOffset;
    }

    public void setCommitLogMinOffset(String commitLogMinOffset) {
        this.commitLogMinOffset = commitLogMinOffset;
    }

    public String getPutMessageEntireTimeMax() {
        return putMessageEntireTimeMax;
    }

    public void setPutMessageEntireTimeMax(String putMessageEntireTimeMax) {
        this.putMessageEntireTimeMax = putMessageEntireTimeMax;
    }

    public String getPullThreadPoolQueueHeadWaitTimeMills() {
        return pullThreadPoolQueueHeadWaitTimeMills;
    }

    public void setPullThreadPoolQueueHeadWaitTimeMills(String pullThreadPoolQueueHeadWaitTimeMills) {
        this.pullThreadPoolQueueHeadWaitTimeMills = pullThreadPoolQueueHeadWaitTimeMills;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getEarliestMessageTimeStamp() {
        return earliestMessageTimeStamp;
    }

    public void setEarliestMessageTimeStamp(String earliestMessageTimeStamp) {
        this.earliestMessageTimeStamp = earliestMessageTimeStamp;
    }

    public String getCommitLogDirCapacity() {
        return commitLogDirCapacity;
    }

    public void setCommitLogDirCapacity(String commitLogDirCapacity) {
        this.commitLogDirCapacity = commitLogDirCapacity;
    }

    public String getDispatchMaxBuffer() {
        return dispatchMaxBuffer;
    }

    public void setDispatchMaxBuffer(String dispatchMaxBuffer) {
        this.dispatchMaxBuffer = dispatchMaxBuffer;
    }

    public String getBrokerVersion() {
        return brokerVersion;
    }

    public void setBrokerVersion(String brokerVersion) {
        this.brokerVersion = brokerVersion;
    }

    public String getPutTps() {
        return putTps;
    }

    public void setPutTps(String putTps) {
        this.putTps = putTps;
    }

    public String getRemainTransientStoreBufferNumbs() {
        return remainTransientStoreBufferNumbs;
    }

    public void setRemainTransientStoreBufferNumbs(String remainTransientStoreBufferNumbs) {
        this.remainTransientStoreBufferNumbs = remainTransientStoreBufferNumbs;
    }

    public String getGetMissTps() {
        return getMissTps;
    }

    public void setGetMissTps(String getMissTps) {
        this.getMissTps = getMissTps;
    }

    public String getQueryThreadPoolQueueCapacity() {
        return queryThreadPoolQueueCapacity;
    }

    public void setQueryThreadPoolQueueCapacity(String queryThreadPoolQueueCapacity) {
        this.queryThreadPoolQueueCapacity = queryThreadPoolQueueCapacity;
    }

    public String getPutMessageAverageSize() {
        return putMessageAverageSize;
    }

    public void setPutMessageAverageSize(String putMessageAverageSize) {
        this.putMessageAverageSize = putMessageAverageSize;
    }

    public String getGetTransferedTps() {
        return getTransferedTps;
    }

    public void setGetTransferedTps(String getTransferedTps) {
        this.getTransferedTps = getTransferedTps;
    }

    public String getDispatchBehindBytes() {
        return dispatchBehindBytes;
    }

    public void setDispatchBehindBytes(String dispatchBehindBytes) {
        this.dispatchBehindBytes = dispatchBehindBytes;
    }

    public String getPutMessageSizeTotal() {
        return putMessageSizeTotal;
    }

    public void setPutMessageSizeTotal(String putMessageSizeTotal) {
        this.putMessageSizeTotal = putMessageSizeTotal;
    }

    public String getSendThreadPoolQueueCapacity() {
        return sendThreadPoolQueueCapacity;
    }

    public void setSendThreadPoolQueueCapacity(String sendThreadPoolQueueCapacity) {
        this.sendThreadPoolQueueCapacity = sendThreadPoolQueueCapacity;
    }

    public String getEndTransactionThreadPoolQueueCapacity() {
        return endTransactionThreadPoolQueueCapacity;
    }

    public void setEndTransactionThreadPoolQueueCapacity(String endTransactionThreadPoolQueueCapacity) {
        this.endTransactionThreadPoolQueueCapacity = endTransactionThreadPoolQueueCapacity;
    }

    public String getEndTransactionQueueSize() {
        return endTransactionQueueSize;
    }

    public void setEndTransactionQueueSize(String endTransactionQueueSize) {
        this.endTransactionQueueSize = endTransactionQueueSize;
    }

    public String getGetTotalTps() {
        return getTotalTps;
    }

    public void setGetTotalTps(String getTotalTps) {
        this.getTotalTps = getTotalTps;
    }

    public String getPullThreadPoolQueueCapacity() {
        return pullThreadPoolQueueCapacity;
    }

    public void setPullThreadPoolQueueCapacity(String pullThreadPoolQueueCapacity) {
        this.pullThreadPoolQueueCapacity = pullThreadPoolQueueCapacity;
    }

    public String getConsumeQueueDiskRatio() {
        return consumeQueueDiskRatio;
    }

    public void setConsumeQueueDiskRatio(String consumeQueueDiskRatio) {
        this.consumeQueueDiskRatio = consumeQueueDiskRatio;
    }

    public String getPageCacheLockTimeMills() {
        return pageCacheLockTimeMills;
    }

    public void setPageCacheLockTimeMills(String pageCacheLockTimeMills) {
        this.pageCacheLockTimeMills = pageCacheLockTimeMills;
    }
}
