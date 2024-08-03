package help.lixin.starlink.plugin.rocketmq.api.dto.cluster;

import java.util.Date;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/25 10:27 上午
 * @Description
 */
public class ClusterConfigInfoDTO {

    private String serverSelectorThreads;
    private String brokerRole;
    private String serverSocketRcvBufSize;
    private String osPageCacheBusyTimeOutMills;
    private String shortPollingTimeMills;
    private String clientSocketRcvBufSize;
    private String clusterTopicEnable;
    private String brokerTopicEnable;
    private String autoCreateTopicEnable;
    private String maxErrorRateOfBloomFilter;
    private String maxMsgsNumBatch;
    private String cleanResourceInterval;
    private String commercialBaseCount;
    private String maxTransferCountOnMessageInMemory;
    private String brokerFastFailureEnable;
    private String brokerClusterName;
    private String flushDiskType;
    private String commercialBigCount;
    private String mapedFileSizeCommitLog;
    private String consumerFallbehindThreshold;
    private String autoCreateSubscriptionGroup;
    private String transientStorePoolEnable;
    private String flushConsumerOffsetInterval;
    private String waitTimeMillsInHeartbeatQueue;
    private String diskMaxUsedSpaceRatio;
    private String cleanFileForciblyEnable;
    private String slaveReadEnable;
    private String flushCommitLogLeastPages;
    private String msgTraceTopicName;
    private String expectConsumerNumUseFilter;
    private String traceTopicEnable;
    private String useEpollNativeSelector;
    private String enablePropertyFilter;
    private String messageDelayLevel;
    private String deleteCommitLogFilesInterval;
    private String brokerName;
    private String maxTransferBytesOnMessageInDisk;
    private String mapedFileSizeConsumeQueue;
    private String listenPort;
    private String flushConsumeQueueLeastPages;
    private String pullMessageThreadPoolNums;
    private String useReentrantLockWhenPutMessage;
    private String flushIntervalConsumeQueue;
    private String sendThreadPoolQueueCapacity;
    private String debugLockEnable;
    private String haHousekeepingInterval;
    private String diskFallRecorded;
    private String messageIndexEnable;
    private String clientAsyncSemaphoreValue;
    private String clientCallbackExecutorThreads;
    private String putMsgIndexHightWater;
    private String sendMessageThreadPoolNums;
    private String clientManagerThreadPoolQueueCapacity;
    private String serverSocketSndBufSize;
    private String maxDelayTime;
    private String clientSocketSndBufSize;
    private String namesrvAddr;
    private String commercialEnable;
    private String maxHashSlotNum;
    private String heartbeatThreadPoolNums;
    private String transactionTimeOut;
    private String maxMessageSize;
    private String adminBrokerThreadPoolNums;
    private String defaultQueryMaxNum;
    private String forceRegister;
    private String maxTransferBytesOnMessageInMemory;
    private String enableConsumeQueueExt;
    private String longPollingEnable;
    private String serverWorkerThreads;
    private String messageIndexSafe;
    private String deleteConsumeQueueFilesInterval;
    private String haSlaveFallbehindMax;
    private String serverCallbackExecutorThreads;
    private String flushCommitLogThoroughInterval;
    private String commercialTimerCount;
    private String useTLS;
    private String redeleteHangedFileInterval;
    private String flushIntervalCommitLog;
    private String rocketmqHome;
    private String queryMessageThreadPoolNums;
    private String messageStorePlugIn;
    private String serverChannelMaxIdleTimeSeconds;
    private String maxIndexNum;
    private String filterDataCleanTimeSpan;
    private String filterServerNums;
    private String commitCommitLogLeastPages;
    private String waitTimeMillsInPullQueue;
    private String haSendHeartbeatInterval;
    private String clientChannelMaxIdleTimeSeconds;
    private String filterSupportRetry;
    private String flushDelayOffsetInterval;
    private String duplicationEnable;
    private String offsetCheckInSlave;
    private String clientCloseSocketIfTimeout;
    private String transientStorePoolSize;
    private String waitTimeMillsInSendQueue;
    private String warmMapedFileEnable;
    private String endTransactionThreadPoolNums;
    private String flushCommitLogTimed;
    private String flushLeastPagesWhenWarmMapedFile;
    private String clientWorkerThreads;
    private String endTransactionPoolQueueCapacity;
    private String registerNameServerPeriod;
    private String registerBrokerTimeoutMills;
    private String accessMessageInMemoryMaxRatio;
    private String highSpeedMode;
    private String transactionCheckMax;
    private String checkCRCOnRecover;
    private String destroyMapedFileIntervalForcibly;
    private String brokerIP2;
    private String brokerIP1;
    private String commitIntervalCommitLog;
    private String clientOnewaySemaphoreValue;
    private String traceOn;
    private String clientManageThreadPoolNums;
    private String channelNotActiveInterval;
    private String clusterName;
    private String mappedFileSizeConsumeQueueExt;
    private String consumerManagerThreadPoolQueueCapacity;
    private String serverOnewaySemaphoreValue;
    private String haListenPort;
    private String enableCalcFilterBitMap;
    private String clientPooledByteBufAllocatorEnable;
    private String aclEnable;
    private String storePathRootDir;
    private String syncFlushTimeout;
    private String rejectTransactionMessage;
    private String commitCommitLogThoroughInterval;
    private String connectTimeoutMillis;
    private String queryThreadPoolQueueCapacity;
    private String regionId;
    private String consumerManageThreadPoolNums;
    private String disableConsumeIfConsumerReadSlowly;
    private String flushConsumerOffsetHistoryInterval;
    private String fetchNamesrvAddrByAddressServer;
    private String haTransferBatchSize;
    private String compressedRegister;
    private String storePathCommitLog;
    private String commercialTransCount;
    private String transactionCheckInterval;
    private String startAcceptSendRequestTimeStamp;
    private String serverPooledByteBufAllocatorEnable;
    private String serverAsyncSemaphoreValue;
    private String heartbeatThreadPoolQueueCapacity;
    private String waitTimeMillsInTransactionQueue;
    private String deleteWhen;
    private String bitMapLengthConsumeQueueExt;
    private String fastFailIfNoBufferInStorePool;
    private String defaultTopicQueueNums;
    private String notifyConsumerIdsChangedEnable;
    private String flushConsumeQueueThoroughInterval;
    private String fileReservedTime;
    private String brokerPermission;
    private String transferMsgByHeap;
    private String pullThreadPoolQueueCapacity;
    private String brokerId;
    private String maxTransferCountOnMessageInDisk;

    public void setServerSelectorThreads(String serverSelectorThreads) {
        this.serverSelectorThreads = serverSelectorThreads;
    }

    public String getServerSelectorThreads() {
        return serverSelectorThreads;
    }

    public void setBrokerRole(String brokerRole) {
        this.brokerRole = brokerRole;
    }

    public String getBrokerRole() {
        return brokerRole;
    }

    public void setServerSocketRcvBufSize(String serverSocketRcvBufSize) {
        this.serverSocketRcvBufSize = serverSocketRcvBufSize;
    }

    public String getServerSocketRcvBufSize() {
        return serverSocketRcvBufSize;
    }

    public void setOsPageCacheBusyTimeOutMills(String osPageCacheBusyTimeOutMills) {
        this.osPageCacheBusyTimeOutMills = osPageCacheBusyTimeOutMills;
    }

    public String getOsPageCacheBusyTimeOutMills() {
        return osPageCacheBusyTimeOutMills;
    }

    public void setShortPollingTimeMills(String shortPollingTimeMills) {
        this.shortPollingTimeMills = shortPollingTimeMills;
    }

    public String getShortPollingTimeMills() {
        return shortPollingTimeMills;
    }

    public void setClientSocketRcvBufSize(String clientSocketRcvBufSize) {
        this.clientSocketRcvBufSize = clientSocketRcvBufSize;
    }

    public String getClientSocketRcvBufSize() {
        return clientSocketRcvBufSize;
    }

    public void setClusterTopicEnable(String clusterTopicEnable) {
        this.clusterTopicEnable = clusterTopicEnable;
    }

    public String getClusterTopicEnable() {
        return clusterTopicEnable;
    }

    public void setBrokerTopicEnable(String brokerTopicEnable) {
        this.brokerTopicEnable = brokerTopicEnable;
    }

    public String getBrokerTopicEnable() {
        return brokerTopicEnable;
    }

    public void setAutoCreateTopicEnable(String autoCreateTopicEnable) {
        this.autoCreateTopicEnable = autoCreateTopicEnable;
    }

    public String getAutoCreateTopicEnable() {
        return autoCreateTopicEnable;
    }

    public void setMaxErrorRateOfBloomFilter(String maxErrorRateOfBloomFilter) {
        this.maxErrorRateOfBloomFilter = maxErrorRateOfBloomFilter;
    }

    public String getMaxErrorRateOfBloomFilter() {
        return maxErrorRateOfBloomFilter;
    }

    public void setMaxMsgsNumBatch(String maxMsgsNumBatch) {
        this.maxMsgsNumBatch = maxMsgsNumBatch;
    }

    public String getMaxMsgsNumBatch() {
        return maxMsgsNumBatch;
    }

    public void setCleanResourceInterval(String cleanResourceInterval) {
        this.cleanResourceInterval = cleanResourceInterval;
    }

    public String getCleanResourceInterval() {
        return cleanResourceInterval;
    }

    public void setCommercialBaseCount(String commercialBaseCount) {
        this.commercialBaseCount = commercialBaseCount;
    }

    public String getCommercialBaseCount() {
        return commercialBaseCount;
    }

    public void setMaxTransferCountOnMessageInMemory(String maxTransferCountOnMessageInMemory) {
        this.maxTransferCountOnMessageInMemory = maxTransferCountOnMessageInMemory;
    }

    public String getMaxTransferCountOnMessageInMemory() {
        return maxTransferCountOnMessageInMemory;
    }

    public void setBrokerFastFailureEnable(String brokerFastFailureEnable) {
        this.brokerFastFailureEnable = brokerFastFailureEnable;
    }

    public String getBrokerFastFailureEnable() {
        return brokerFastFailureEnable;
    }

    public void setBrokerClusterName(String brokerClusterName) {
        this.brokerClusterName = brokerClusterName;
    }

    public String getBrokerClusterName() {
        return brokerClusterName;
    }

    public void setFlushDiskType(String flushDiskType) {
        this.flushDiskType = flushDiskType;
    }

    public String getFlushDiskType() {
        return flushDiskType;
    }

    public void setCommercialBigCount(String commercialBigCount) {
        this.commercialBigCount = commercialBigCount;
    }

    public String getCommercialBigCount() {
        return commercialBigCount;
    }

    public void setMapedFileSizeCommitLog(String mapedFileSizeCommitLog) {
        this.mapedFileSizeCommitLog = mapedFileSizeCommitLog;
    }

    public String getMapedFileSizeCommitLog() {
        return mapedFileSizeCommitLog;
    }

    public void setConsumerFallbehindThreshold(String consumerFallbehindThreshold) {
        this.consumerFallbehindThreshold = consumerFallbehindThreshold;
    }

    public String getConsumerFallbehindThreshold() {
        return consumerFallbehindThreshold;
    }

    public void setAutoCreateSubscriptionGroup(String autoCreateSubscriptionGroup) {
        this.autoCreateSubscriptionGroup = autoCreateSubscriptionGroup;
    }

    public String getAutoCreateSubscriptionGroup() {
        return autoCreateSubscriptionGroup;
    }

    public void setTransientStorePoolEnable(String transientStorePoolEnable) {
        this.transientStorePoolEnable = transientStorePoolEnable;
    }

    public String getTransientStorePoolEnable() {
        return transientStorePoolEnable;
    }

    public void setFlushConsumerOffsetInterval(String flushConsumerOffsetInterval) {
        this.flushConsumerOffsetInterval = flushConsumerOffsetInterval;
    }

    public String getFlushConsumerOffsetInterval() {
        return flushConsumerOffsetInterval;
    }

    public void setWaitTimeMillsInHeartbeatQueue(String waitTimeMillsInHeartbeatQueue) {
        this.waitTimeMillsInHeartbeatQueue = waitTimeMillsInHeartbeatQueue;
    }

    public String getWaitTimeMillsInHeartbeatQueue() {
        return waitTimeMillsInHeartbeatQueue;
    }

    public void setDiskMaxUsedSpaceRatio(String diskMaxUsedSpaceRatio) {
        this.diskMaxUsedSpaceRatio = diskMaxUsedSpaceRatio;
    }

    public String getDiskMaxUsedSpaceRatio() {
        return diskMaxUsedSpaceRatio;
    }

    public void setCleanFileForciblyEnable(String cleanFileForciblyEnable) {
        this.cleanFileForciblyEnable = cleanFileForciblyEnable;
    }

    public String getCleanFileForciblyEnable() {
        return cleanFileForciblyEnable;
    }

    public void setSlaveReadEnable(String slaveReadEnable) {
        this.slaveReadEnable = slaveReadEnable;
    }

    public String getSlaveReadEnable() {
        return slaveReadEnable;
    }

    public void setFlushCommitLogLeastPages(String flushCommitLogLeastPages) {
        this.flushCommitLogLeastPages = flushCommitLogLeastPages;
    }

    public String getFlushCommitLogLeastPages() {
        return flushCommitLogLeastPages;
    }

    public void setMsgTraceTopicName(String msgTraceTopicName) {
        this.msgTraceTopicName = msgTraceTopicName;
    }

    public String getMsgTraceTopicName() {
        return msgTraceTopicName;
    }

    public void setExpectConsumerNumUseFilter(String expectConsumerNumUseFilter) {
        this.expectConsumerNumUseFilter = expectConsumerNumUseFilter;
    }

    public String getExpectConsumerNumUseFilter() {
        return expectConsumerNumUseFilter;
    }

    public void setTraceTopicEnable(String traceTopicEnable) {
        this.traceTopicEnable = traceTopicEnable;
    }

    public String getTraceTopicEnable() {
        return traceTopicEnable;
    }

    public void setUseEpollNativeSelector(String useEpollNativeSelector) {
        this.useEpollNativeSelector = useEpollNativeSelector;
    }

    public String getUseEpollNativeSelector() {
        return useEpollNativeSelector;
    }

    public void setEnablePropertyFilter(String enablePropertyFilter) {
        this.enablePropertyFilter = enablePropertyFilter;
    }

    public String getEnablePropertyFilter() {
        return enablePropertyFilter;
    }

    public void setMessageDelayLevel(String messageDelayLevel) {
        this.messageDelayLevel = messageDelayLevel;
    }

    public String getMessageDelayLevel() {
        return messageDelayLevel;
    }

    public void setDeleteCommitLogFilesInterval(String deleteCommitLogFilesInterval) {
        this.deleteCommitLogFilesInterval = deleteCommitLogFilesInterval;
    }

    public String getDeleteCommitLogFilesInterval() {
        return deleteCommitLogFilesInterval;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setMaxTransferBytesOnMessageInDisk(String maxTransferBytesOnMessageInDisk) {
        this.maxTransferBytesOnMessageInDisk = maxTransferBytesOnMessageInDisk;
    }

    public String getMaxTransferBytesOnMessageInDisk() {
        return maxTransferBytesOnMessageInDisk;
    }

    public void setMapedFileSizeConsumeQueue(String mapedFileSizeConsumeQueue) {
        this.mapedFileSizeConsumeQueue = mapedFileSizeConsumeQueue;
    }

    public String getMapedFileSizeConsumeQueue() {
        return mapedFileSizeConsumeQueue;
    }

    public void setListenPort(String listenPort) {
        this.listenPort = listenPort;
    }

    public String getListenPort() {
        return listenPort;
    }

    public void setFlushConsumeQueueLeastPages(String flushConsumeQueueLeastPages) {
        this.flushConsumeQueueLeastPages = flushConsumeQueueLeastPages;
    }

    public String getFlushConsumeQueueLeastPages() {
        return flushConsumeQueueLeastPages;
    }

    public void setPullMessageThreadPoolNums(String pullMessageThreadPoolNums) {
        this.pullMessageThreadPoolNums = pullMessageThreadPoolNums;
    }

    public String getPullMessageThreadPoolNums() {
        return pullMessageThreadPoolNums;
    }

    public void setUseReentrantLockWhenPutMessage(String useReentrantLockWhenPutMessage) {
        this.useReentrantLockWhenPutMessage = useReentrantLockWhenPutMessage;
    }

    public String getUseReentrantLockWhenPutMessage() {
        return useReentrantLockWhenPutMessage;
    }

    public void setFlushIntervalConsumeQueue(String flushIntervalConsumeQueue) {
        this.flushIntervalConsumeQueue = flushIntervalConsumeQueue;
    }

    public String getFlushIntervalConsumeQueue() {
        return flushIntervalConsumeQueue;
    }

    public void setSendThreadPoolQueueCapacity(String sendThreadPoolQueueCapacity) {
        this.sendThreadPoolQueueCapacity = sendThreadPoolQueueCapacity;
    }

    public String getSendThreadPoolQueueCapacity() {
        return sendThreadPoolQueueCapacity;
    }

    public void setDebugLockEnable(String debugLockEnable) {
        this.debugLockEnable = debugLockEnable;
    }

    public String getDebugLockEnable() {
        return debugLockEnable;
    }

    public void setHaHousekeepingInterval(String haHousekeepingInterval) {
        this.haHousekeepingInterval = haHousekeepingInterval;
    }

    public String getHaHousekeepingInterval() {
        return haHousekeepingInterval;
    }

    public void setDiskFallRecorded(String diskFallRecorded) {
        this.diskFallRecorded = diskFallRecorded;
    }

    public String getDiskFallRecorded() {
        return diskFallRecorded;
    }

    public void setMessageIndexEnable(String messageIndexEnable) {
        this.messageIndexEnable = messageIndexEnable;
    }

    public String getMessageIndexEnable() {
        return messageIndexEnable;
    }

    public void setClientAsyncSemaphoreValue(String clientAsyncSemaphoreValue) {
        this.clientAsyncSemaphoreValue = clientAsyncSemaphoreValue;
    }

    public String getClientAsyncSemaphoreValue() {
        return clientAsyncSemaphoreValue;
    }

    public void setClientCallbackExecutorThreads(String clientCallbackExecutorThreads) {
        this.clientCallbackExecutorThreads = clientCallbackExecutorThreads;
    }

    public String getClientCallbackExecutorThreads() {
        return clientCallbackExecutorThreads;
    }

    public void setPutMsgIndexHightWater(String putMsgIndexHightWater) {
        this.putMsgIndexHightWater = putMsgIndexHightWater;
    }

    public String getPutMsgIndexHightWater() {
        return putMsgIndexHightWater;
    }

    public void setSendMessageThreadPoolNums(String sendMessageThreadPoolNums) {
        this.sendMessageThreadPoolNums = sendMessageThreadPoolNums;
    }

    public String getSendMessageThreadPoolNums() {
        return sendMessageThreadPoolNums;
    }

    public void setClientManagerThreadPoolQueueCapacity(String clientManagerThreadPoolQueueCapacity) {
        this.clientManagerThreadPoolQueueCapacity = clientManagerThreadPoolQueueCapacity;
    }

    public String getClientManagerThreadPoolQueueCapacity() {
        return clientManagerThreadPoolQueueCapacity;
    }

    public void setServerSocketSndBufSize(String serverSocketSndBufSize) {
        this.serverSocketSndBufSize = serverSocketSndBufSize;
    }

    public String getServerSocketSndBufSize() {
        return serverSocketSndBufSize;
    }

    public void setMaxDelayTime(String maxDelayTime) {
        this.maxDelayTime = maxDelayTime;
    }

    public String getMaxDelayTime() {
        return maxDelayTime;
    }

    public void setClientSocketSndBufSize(String clientSocketSndBufSize) {
        this.clientSocketSndBufSize = clientSocketSndBufSize;
    }

    public String getClientSocketSndBufSize() {
        return clientSocketSndBufSize;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setCommercialEnable(String commercialEnable) {
        this.commercialEnable = commercialEnable;
    }

    public String getCommercialEnable() {
        return commercialEnable;
    }

    public void setMaxHashSlotNum(String maxHashSlotNum) {
        this.maxHashSlotNum = maxHashSlotNum;
    }

    public String getMaxHashSlotNum() {
        return maxHashSlotNum;
    }

    public void setHeartbeatThreadPoolNums(String heartbeatThreadPoolNums) {
        this.heartbeatThreadPoolNums = heartbeatThreadPoolNums;
    }

    public String getHeartbeatThreadPoolNums() {
        return heartbeatThreadPoolNums;
    }

    public void setTransactionTimeOut(String transactionTimeOut) {
        this.transactionTimeOut = transactionTimeOut;
    }

    public String getTransactionTimeOut() {
        return transactionTimeOut;
    }

    public void setMaxMessageSize(String maxMessageSize) {
        this.maxMessageSize = maxMessageSize;
    }

    public String getMaxMessageSize() {
        return maxMessageSize;
    }

    public void setAdminBrokerThreadPoolNums(String adminBrokerThreadPoolNums) {
        this.adminBrokerThreadPoolNums = adminBrokerThreadPoolNums;
    }

    public String getAdminBrokerThreadPoolNums() {
        return adminBrokerThreadPoolNums;
    }

    public void setDefaultQueryMaxNum(String defaultQueryMaxNum) {
        this.defaultQueryMaxNum = defaultQueryMaxNum;
    }

    public String getDefaultQueryMaxNum() {
        return defaultQueryMaxNum;
    }

    public void setForceRegister(String forceRegister) {
        this.forceRegister = forceRegister;
    }

    public String getForceRegister() {
        return forceRegister;
    }

    public void setMaxTransferBytesOnMessageInMemory(String maxTransferBytesOnMessageInMemory) {
        this.maxTransferBytesOnMessageInMemory = maxTransferBytesOnMessageInMemory;
    }

    public String getMaxTransferBytesOnMessageInMemory() {
        return maxTransferBytesOnMessageInMemory;
    }

    public void setEnableConsumeQueueExt(String enableConsumeQueueExt) {
        this.enableConsumeQueueExt = enableConsumeQueueExt;
    }

    public String getEnableConsumeQueueExt() {
        return enableConsumeQueueExt;
    }

    public void setLongPollingEnable(String longPollingEnable) {
        this.longPollingEnable = longPollingEnable;
    }

    public String getLongPollingEnable() {
        return longPollingEnable;
    }

    public void setServerWorkerThreads(String serverWorkerThreads) {
        this.serverWorkerThreads = serverWorkerThreads;
    }

    public String getServerWorkerThreads() {
        return serverWorkerThreads;
    }

    public void setMessageIndexSafe(String messageIndexSafe) {
        this.messageIndexSafe = messageIndexSafe;
    }

    public String getMessageIndexSafe() {
        return messageIndexSafe;
    }

    public void setDeleteConsumeQueueFilesInterval(String deleteConsumeQueueFilesInterval) {
        this.deleteConsumeQueueFilesInterval = deleteConsumeQueueFilesInterval;
    }

    public String getDeleteConsumeQueueFilesInterval() {
        return deleteConsumeQueueFilesInterval;
    }

    public void setHaSlaveFallbehindMax(String haSlaveFallbehindMax) {
        this.haSlaveFallbehindMax = haSlaveFallbehindMax;
    }

    public String getHaSlaveFallbehindMax() {
        return haSlaveFallbehindMax;
    }

    public void setServerCallbackExecutorThreads(String serverCallbackExecutorThreads) {
        this.serverCallbackExecutorThreads = serverCallbackExecutorThreads;
    }

    public String getServerCallbackExecutorThreads() {
        return serverCallbackExecutorThreads;
    }

    public void setFlushCommitLogThoroughInterval(String flushCommitLogThoroughInterval) {
        this.flushCommitLogThoroughInterval = flushCommitLogThoroughInterval;
    }

    public String getFlushCommitLogThoroughInterval() {
        return flushCommitLogThoroughInterval;
    }

    public void setCommercialTimerCount(String commercialTimerCount) {
        this.commercialTimerCount = commercialTimerCount;
    }

    public String getCommercialTimerCount() {
        return commercialTimerCount;
    }

    public void setUseTLS(String useTLS) {
        this.useTLS = useTLS;
    }

    public String getUseTLS() {
        return useTLS;
    }

    public void setRedeleteHangedFileInterval(String redeleteHangedFileInterval) {
        this.redeleteHangedFileInterval = redeleteHangedFileInterval;
    }

    public String getRedeleteHangedFileInterval() {
        return redeleteHangedFileInterval;
    }

    public void setFlushIntervalCommitLog(String flushIntervalCommitLog) {
        this.flushIntervalCommitLog = flushIntervalCommitLog;
    }

    public String getFlushIntervalCommitLog() {
        return flushIntervalCommitLog;
    }

    public String getRocketmqHome() {
        return rocketmqHome;
    }

    public void setRocketmqHome(String rocketmqHome) {
        this.rocketmqHome = rocketmqHome;
    }

    public void setQueryMessageThreadPoolNums(String queryMessageThreadPoolNums) {
        this.queryMessageThreadPoolNums = queryMessageThreadPoolNums;
    }

    public String getQueryMessageThreadPoolNums() {
        return queryMessageThreadPoolNums;
    }

    public void setMessageStorePlugIn(String messageStorePlugIn) {
        this.messageStorePlugIn = messageStorePlugIn;
    }

    public String getMessageStorePlugIn() {
        return messageStorePlugIn;
    }

    public void setServerChannelMaxIdleTimeSeconds(String serverChannelMaxIdleTimeSeconds) {
        this.serverChannelMaxIdleTimeSeconds = serverChannelMaxIdleTimeSeconds;
    }

    public String getServerChannelMaxIdleTimeSeconds() {
        return serverChannelMaxIdleTimeSeconds;
    }

    public void setMaxIndexNum(String maxIndexNum) {
        this.maxIndexNum = maxIndexNum;
    }

    public String getMaxIndexNum() {
        return maxIndexNum;
    }

    public void setFilterDataCleanTimeSpan(String filterDataCleanTimeSpan) {
        this.filterDataCleanTimeSpan = filterDataCleanTimeSpan;
    }

    public String getFilterDataCleanTimeSpan() {
        return filterDataCleanTimeSpan;
    }

    public void setFilterServerNums(String filterServerNums) {
        this.filterServerNums = filterServerNums;
    }

    public String getFilterServerNums() {
        return filterServerNums;
    }

    public void setCommitCommitLogLeastPages(String commitCommitLogLeastPages) {
        this.commitCommitLogLeastPages = commitCommitLogLeastPages;
    }

    public String getCommitCommitLogLeastPages() {
        return commitCommitLogLeastPages;
    }

    public void setWaitTimeMillsInPullQueue(String waitTimeMillsInPullQueue) {
        this.waitTimeMillsInPullQueue = waitTimeMillsInPullQueue;
    }

    public String getWaitTimeMillsInPullQueue() {
        return waitTimeMillsInPullQueue;
    }

    public void setHaSendHeartbeatInterval(String haSendHeartbeatInterval) {
        this.haSendHeartbeatInterval = haSendHeartbeatInterval;
    }

    public String getHaSendHeartbeatInterval() {
        return haSendHeartbeatInterval;
    }

    public void setClientChannelMaxIdleTimeSeconds(String clientChannelMaxIdleTimeSeconds) {
        this.clientChannelMaxIdleTimeSeconds = clientChannelMaxIdleTimeSeconds;
    }

    public String getClientChannelMaxIdleTimeSeconds() {
        return clientChannelMaxIdleTimeSeconds;
    }

    public void setFilterSupportRetry(String filterSupportRetry) {
        this.filterSupportRetry = filterSupportRetry;
    }

    public String getFilterSupportRetry() {
        return filterSupportRetry;
    }

    public void setFlushDelayOffsetInterval(String flushDelayOffsetInterval) {
        this.flushDelayOffsetInterval = flushDelayOffsetInterval;
    }

    public String getFlushDelayOffsetInterval() {
        return flushDelayOffsetInterval;
    }

    public void setDuplicationEnable(String duplicationEnable) {
        this.duplicationEnable = duplicationEnable;
    }

    public String getDuplicationEnable() {
        return duplicationEnable;
    }

    public void setOffsetCheckInSlave(String offsetCheckInSlave) {
        this.offsetCheckInSlave = offsetCheckInSlave;
    }

    public String getOffsetCheckInSlave() {
        return offsetCheckInSlave;
    }

    public void setClientCloseSocketIfTimeout(String clientCloseSocketIfTimeout) {
        this.clientCloseSocketIfTimeout = clientCloseSocketIfTimeout;
    }

    public String getClientCloseSocketIfTimeout() {
        return clientCloseSocketIfTimeout;
    }

    public void setTransientStorePoolSize(String transientStorePoolSize) {
        this.transientStorePoolSize = transientStorePoolSize;
    }

    public String getTransientStorePoolSize() {
        return transientStorePoolSize;
    }

    public void setWaitTimeMillsInSendQueue(String waitTimeMillsInSendQueue) {
        this.waitTimeMillsInSendQueue = waitTimeMillsInSendQueue;
    }

    public String getWaitTimeMillsInSendQueue() {
        return waitTimeMillsInSendQueue;
    }

    public void setWarmMapedFileEnable(String warmMapedFileEnable) {
        this.warmMapedFileEnable = warmMapedFileEnable;
    }

    public String getWarmMapedFileEnable() {
        return warmMapedFileEnable;
    }

    public void setEndTransactionThreadPoolNums(String endTransactionThreadPoolNums) {
        this.endTransactionThreadPoolNums = endTransactionThreadPoolNums;
    }

    public String getEndTransactionThreadPoolNums() {
        return endTransactionThreadPoolNums;
    }

    public void setFlushCommitLogTimed(String flushCommitLogTimed) {
        this.flushCommitLogTimed = flushCommitLogTimed;
    }

    public String getFlushCommitLogTimed() {
        return flushCommitLogTimed;
    }

    public void setFlushLeastPagesWhenWarmMapedFile(String flushLeastPagesWhenWarmMapedFile) {
        this.flushLeastPagesWhenWarmMapedFile = flushLeastPagesWhenWarmMapedFile;
    }

    public String getFlushLeastPagesWhenWarmMapedFile() {
        return flushLeastPagesWhenWarmMapedFile;
    }

    public void setClientWorkerThreads(String clientWorkerThreads) {
        this.clientWorkerThreads = clientWorkerThreads;
    }

    public String getClientWorkerThreads() {
        return clientWorkerThreads;
    }

    public void setEndTransactionPoolQueueCapacity(String endTransactionPoolQueueCapacity) {
        this.endTransactionPoolQueueCapacity = endTransactionPoolQueueCapacity;
    }

    public String getEndTransactionPoolQueueCapacity() {
        return endTransactionPoolQueueCapacity;
    }

    public void setRegisterNameServerPeriod(String registerNameServerPeriod) {
        this.registerNameServerPeriod = registerNameServerPeriod;
    }

    public String getRegisterNameServerPeriod() {
        return registerNameServerPeriod;
    }

    public void setRegisterBrokerTimeoutMills(String registerBrokerTimeoutMills) {
        this.registerBrokerTimeoutMills = registerBrokerTimeoutMills;
    }

    public String getRegisterBrokerTimeoutMills() {
        return registerBrokerTimeoutMills;
    }

    public void setAccessMessageInMemoryMaxRatio(String accessMessageInMemoryMaxRatio) {
        this.accessMessageInMemoryMaxRatio = accessMessageInMemoryMaxRatio;
    }

    public String getAccessMessageInMemoryMaxRatio() {
        return accessMessageInMemoryMaxRatio;
    }

    public void setHighSpeedMode(String highSpeedMode) {
        this.highSpeedMode = highSpeedMode;
    }

    public String getHighSpeedMode() {
        return highSpeedMode;
    }

    public void setTransactionCheckMax(String transactionCheckMax) {
        this.transactionCheckMax = transactionCheckMax;
    }

    public String getTransactionCheckMax() {
        return transactionCheckMax;
    }

    public void setCheckCRCOnRecover(String checkCRCOnRecover) {
        this.checkCRCOnRecover = checkCRCOnRecover;
    }

    public String getCheckCRCOnRecover() {
        return checkCRCOnRecover;
    }

    public void setDestroyMapedFileIntervalForcibly(String destroyMapedFileIntervalForcibly) {
        this.destroyMapedFileIntervalForcibly = destroyMapedFileIntervalForcibly;
    }

    public String getDestroyMapedFileIntervalForcibly() {
        return destroyMapedFileIntervalForcibly;
    }

    public void setBrokerIP2(String brokerIP2) {
        this.brokerIP2 = brokerIP2;
    }

    public String getBrokerIP2() {
        return brokerIP2;
    }

    public void setBrokerIP1(String brokerIP1) {
        this.brokerIP1 = brokerIP1;
    }

    public String getBrokerIP1() {
        return brokerIP1;
    }

    public void setCommitIntervalCommitLog(String commitIntervalCommitLog) {
        this.commitIntervalCommitLog = commitIntervalCommitLog;
    }

    public String getCommitIntervalCommitLog() {
        return commitIntervalCommitLog;
    }

    public void setClientOnewaySemaphoreValue(String clientOnewaySemaphoreValue) {
        this.clientOnewaySemaphoreValue = clientOnewaySemaphoreValue;
    }

    public String getClientOnewaySemaphoreValue() {
        return clientOnewaySemaphoreValue;
    }

    public void setTraceOn(String traceOn) {
        this.traceOn = traceOn;
    }

    public String getTraceOn() {
        return traceOn;
    }

    public void setClientManageThreadPoolNums(String clientManageThreadPoolNums) {
        this.clientManageThreadPoolNums = clientManageThreadPoolNums;
    }

    public String getClientManageThreadPoolNums() {
        return clientManageThreadPoolNums;
    }

    public void setChannelNotActiveInterval(String channelNotActiveInterval) {
        this.channelNotActiveInterval = channelNotActiveInterval;
    }

    public String getChannelNotActiveInterval() {
        return channelNotActiveInterval;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setMappedFileSizeConsumeQueueExt(String mappedFileSizeConsumeQueueExt) {
        this.mappedFileSizeConsumeQueueExt = mappedFileSizeConsumeQueueExt;
    }

    public String getMappedFileSizeConsumeQueueExt() {
        return mappedFileSizeConsumeQueueExt;
    }

    public void setConsumerManagerThreadPoolQueueCapacity(String consumerManagerThreadPoolQueueCapacity) {
        this.consumerManagerThreadPoolQueueCapacity = consumerManagerThreadPoolQueueCapacity;
    }

    public String getConsumerManagerThreadPoolQueueCapacity() {
        return consumerManagerThreadPoolQueueCapacity;
    }

    public void setServerOnewaySemaphoreValue(String serverOnewaySemaphoreValue) {
        this.serverOnewaySemaphoreValue = serverOnewaySemaphoreValue;
    }

    public String getServerOnewaySemaphoreValue() {
        return serverOnewaySemaphoreValue;
    }

    public void setHaListenPort(String haListenPort) {
        this.haListenPort = haListenPort;
    }

    public String getHaListenPort() {
        return haListenPort;
    }

    public void setEnableCalcFilterBitMap(String enableCalcFilterBitMap) {
        this.enableCalcFilterBitMap = enableCalcFilterBitMap;
    }

    public String getEnableCalcFilterBitMap() {
        return enableCalcFilterBitMap;
    }

    public void setClientPooledByteBufAllocatorEnable(String clientPooledByteBufAllocatorEnable) {
        this.clientPooledByteBufAllocatorEnable = clientPooledByteBufAllocatorEnable;
    }

    public String getClientPooledByteBufAllocatorEnable() {
        return clientPooledByteBufAllocatorEnable;
    }

    public void setAclEnable(String aclEnable) {
        this.aclEnable = aclEnable;
    }

    public String getAclEnable() {
        return aclEnable;
    }

    public void setStorePathRootDir(String storePathRootDir) {
        this.storePathRootDir = storePathRootDir;
    }

    public String getStorePathRootDir() {
        return storePathRootDir;
    }

    public void setSyncFlushTimeout(String syncFlushTimeout) {
        this.syncFlushTimeout = syncFlushTimeout;
    }

    public String getSyncFlushTimeout() {
        return syncFlushTimeout;
    }

    public void setRejectTransactionMessage(String rejectTransactionMessage) {
        this.rejectTransactionMessage = rejectTransactionMessage;
    }

    public String getRejectTransactionMessage() {
        return rejectTransactionMessage;
    }

    public void setCommitCommitLogThoroughInterval(String commitCommitLogThoroughInterval) {
        this.commitCommitLogThoroughInterval = commitCommitLogThoroughInterval;
    }

    public String getCommitCommitLogThoroughInterval() {
        return commitCommitLogThoroughInterval;
    }

    public void setConnectTimeoutMillis(String connectTimeoutMillis) {
        this.connectTimeoutMillis = connectTimeoutMillis;
    }

    public String getConnectTimeoutMillis() {
        return connectTimeoutMillis;
    }

    public void setQueryThreadPoolQueueCapacity(String queryThreadPoolQueueCapacity) {
        this.queryThreadPoolQueueCapacity = queryThreadPoolQueueCapacity;
    }

    public String getQueryThreadPoolQueueCapacity() {
        return queryThreadPoolQueueCapacity;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setConsumerManageThreadPoolNums(String consumerManageThreadPoolNums) {
        this.consumerManageThreadPoolNums = consumerManageThreadPoolNums;
    }

    public String getConsumerManageThreadPoolNums() {
        return consumerManageThreadPoolNums;
    }

    public void setDisableConsumeIfConsumerReadSlowly(String disableConsumeIfConsumerReadSlowly) {
        this.disableConsumeIfConsumerReadSlowly = disableConsumeIfConsumerReadSlowly;
    }

    public String getDisableConsumeIfConsumerReadSlowly() {
        return disableConsumeIfConsumerReadSlowly;
    }

    public void setFlushConsumerOffsetHistoryInterval(String flushConsumerOffsetHistoryInterval) {
        this.flushConsumerOffsetHistoryInterval = flushConsumerOffsetHistoryInterval;
    }

    public String getFlushConsumerOffsetHistoryInterval() {
        return flushConsumerOffsetHistoryInterval;
    }

    public void setFetchNamesrvAddrByAddressServer(String fetchNamesrvAddrByAddressServer) {
        this.fetchNamesrvAddrByAddressServer = fetchNamesrvAddrByAddressServer;
    }

    public String getFetchNamesrvAddrByAddressServer() {
        return fetchNamesrvAddrByAddressServer;
    }

    public void setHaTransferBatchSize(String haTransferBatchSize) {
        this.haTransferBatchSize = haTransferBatchSize;
    }

    public String getHaTransferBatchSize() {
        return haTransferBatchSize;
    }

    public void setCompressedRegister(String compressedRegister) {
        this.compressedRegister = compressedRegister;
    }

    public String getCompressedRegister() {
        return compressedRegister;
    }

    public void setStorePathCommitLog(String storePathCommitLog) {
        this.storePathCommitLog = storePathCommitLog;
    }

    public String getStorePathCommitLog() {
        return storePathCommitLog;
    }

    public void setCommercialTransCount(String commercialTransCount) {
        this.commercialTransCount = commercialTransCount;
    }

    public String getCommercialTransCount() {
        return commercialTransCount;
    }

    public void setTransactionCheckInterval(String transactionCheckInterval) {
        this.transactionCheckInterval = transactionCheckInterval;
    }

    public String getTransactionCheckInterval() {
        return transactionCheckInterval;
    }

    public void setStartAcceptSendRequestTimeStamp(String startAcceptSendRequestTimeStamp) {
        this.startAcceptSendRequestTimeStamp = startAcceptSendRequestTimeStamp;
    }

    public String getStartAcceptSendRequestTimeStamp() {
        return startAcceptSendRequestTimeStamp;
    }

    public void setServerPooledByteBufAllocatorEnable(String serverPooledByteBufAllocatorEnable) {
        this.serverPooledByteBufAllocatorEnable = serverPooledByteBufAllocatorEnable;
    }

    public String getServerPooledByteBufAllocatorEnable() {
        return serverPooledByteBufAllocatorEnable;
    }

    public void setServerAsyncSemaphoreValue(String serverAsyncSemaphoreValue) {
        this.serverAsyncSemaphoreValue = serverAsyncSemaphoreValue;
    }

    public String getServerAsyncSemaphoreValue() {
        return serverAsyncSemaphoreValue;
    }

    public void setHeartbeatThreadPoolQueueCapacity(String heartbeatThreadPoolQueueCapacity) {
        this.heartbeatThreadPoolQueueCapacity = heartbeatThreadPoolQueueCapacity;
    }

    public String getHeartbeatThreadPoolQueueCapacity() {
        return heartbeatThreadPoolQueueCapacity;
    }

    public void setWaitTimeMillsInTransactionQueue(String waitTimeMillsInTransactionQueue) {
        this.waitTimeMillsInTransactionQueue = waitTimeMillsInTransactionQueue;
    }

    public String getWaitTimeMillsInTransactionQueue() {
        return waitTimeMillsInTransactionQueue;
    }

    public void setDeleteWhen(String deleteWhen) {
        this.deleteWhen = deleteWhen;
    }

    public String getDeleteWhen() {
        return deleteWhen;
    }

    public void setBitMapLengthConsumeQueueExt(String bitMapLengthConsumeQueueExt) {
        this.bitMapLengthConsumeQueueExt = bitMapLengthConsumeQueueExt;
    }

    public String getBitMapLengthConsumeQueueExt() {
        return bitMapLengthConsumeQueueExt;
    }

    public void setFastFailIfNoBufferInStorePool(String fastFailIfNoBufferInStorePool) {
        this.fastFailIfNoBufferInStorePool = fastFailIfNoBufferInStorePool;
    }

    public String getFastFailIfNoBufferInStorePool() {
        return fastFailIfNoBufferInStorePool;
    }

    public void setDefaultTopicQueueNums(String defaultTopicQueueNums) {
        this.defaultTopicQueueNums = defaultTopicQueueNums;
    }

    public String getDefaultTopicQueueNums() {
        return defaultTopicQueueNums;
    }

    public void setNotifyConsumerIdsChangedEnable(String notifyConsumerIdsChangedEnable) {
        this.notifyConsumerIdsChangedEnable = notifyConsumerIdsChangedEnable;
    }

    public String getNotifyConsumerIdsChangedEnable() {
        return notifyConsumerIdsChangedEnable;
    }

    public void setFlushConsumeQueueThoroughInterval(String flushConsumeQueueThoroughInterval) {
        this.flushConsumeQueueThoroughInterval = flushConsumeQueueThoroughInterval;
    }

    public String getFlushConsumeQueueThoroughInterval() {
        return flushConsumeQueueThoroughInterval;
    }

    public void setFileReservedTime(String fileReservedTime) {
        this.fileReservedTime = fileReservedTime;
    }

    public String getFileReservedTime() {
        return fileReservedTime;
    }

    public void setBrokerPermission(String brokerPermission) {
        this.brokerPermission = brokerPermission;
    }

    public String getBrokerPermission() {
        return brokerPermission;
    }

    public void setTransferMsgByHeap(String transferMsgByHeap) {
        this.transferMsgByHeap = transferMsgByHeap;
    }

    public String getTransferMsgByHeap() {
        return transferMsgByHeap;
    }

    public void setPullThreadPoolQueueCapacity(String pullThreadPoolQueueCapacity) {
        this.pullThreadPoolQueueCapacity = pullThreadPoolQueueCapacity;
    }

    public String getPullThreadPoolQueueCapacity() {
        return pullThreadPoolQueueCapacity;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }

    public String getBrokerId() {
        return brokerId;
    }

    public void setMaxTransferCountOnMessageInDisk(String maxTransferCountOnMessageInDisk) {
        this.maxTransferCountOnMessageInDisk = maxTransferCountOnMessageInDisk;
    }

    public String getMaxTransferCountOnMessageInDisk() {
        return maxTransferCountOnMessageInDisk;
    }
}
