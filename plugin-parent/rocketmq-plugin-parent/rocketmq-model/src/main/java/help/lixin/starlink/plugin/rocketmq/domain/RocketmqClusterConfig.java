package help.lixin.starlink.plugin.rocketmq.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Table: rocketmq_cluster_config
 */
public class RocketmqClusterConfig implements Serializable {
    /**
     * Column:    id
     * Nullable:  false
     */
    private Long id;

    /**
     * Column:    server_selector_threads
     * Nullable:  true
     */
    private String serverSelectorThreads;

    /**
     * Column:    broker_role
     * Nullable:  true
     */
    private String brokerRole;

    /**
     * Column:    server_socket_rcv_buf_size
     * Nullable:  true
     */
    private String serverSocketRcvBufSize;

    /**
     * Column:    os_page_cache_busy_time_out_mills
     * Nullable:  true
     */
    private String osPageCacheBusyTimeOutMills;

    /**
     * Column:    short_polling_time_mills
     * Nullable:  true
     */
    private String shortPollingTimeMills;

    /**
     * Column:    client_socket_rcv_buf_size
     * Nullable:  true
     */
    private String clientSocketRcvBufSize;

    /**
     * Column:    cluster_topic_enable
     * Nullable:  true
     */
    private String clusterTopicEnable;

    /**
     * Column:    broker_topic_enable
     * Nullable:  true
     */
    private String brokerTopicEnable;

    /**
     * Column:    auto_create_topic_enable
     * Nullable:  true
     */
    private String autoCreateTopicEnable;

    /**
     * Column:    max_error_rate_of_bloom_filter
     * Nullable:  true
     */
    private String maxErrorRateOfBloomFilter;

    /**
     * Column:    max_msgs_num_batch
     * Nullable:  true
     */
    private String maxMsgsNumBatch;

    /**
     * Column:    clean_resource_interval
     * Nullable:  true
     */
    private String cleanResourceInterval;

    /**
     * Column:    commercial_base_count
     * Nullable:  true
     */
    private String commercialBaseCount;

    /**
     * Column:    max_transfer_count_on_message_in_memory
     * Nullable:  true
     */
    private String maxTransferCountOnMessageInMemory;

    /**
     * Column:    broker_fast_failure_enable
     * Nullable:  true
     */
    private String brokerFastFailureEnable;

    /**
     * Column:    broker_cluster_name
     * Nullable:  true
     */
    private String brokerClusterName;

    /**
     * Column:    flush_disk_type
     * Nullable:  true
     */
    private String flushDiskType;

    /**
     * Column:    commercial_big_count
     * Nullable:  true
     */
    private String commercialBigCount;

    /**
     * Column:    maped_file_size_commit_log
     * Nullable:  true
     */
    private String mapedFileSizeCommitLog;

    /**
     * Column:    consumer_fallbehind_threshold
     * Nullable:  true
     */
    private String consumerFallbehindThreshold;

    /**
     * Column:    auto_create_subscription_group
     * Nullable:  true
     */
    private String autoCreateSubscriptionGroup;

    /**
     * Column:    transient_store_pool_enable
     * Nullable:  true
     */
    private String transientStorePoolEnable;

    /**
     * Column:    flush_consumer_offset_interval
     * Nullable:  true
     */
    private String flushConsumerOffsetInterval;

    /**
     * Column:    wait_time_mills_in_heartbeat_queue
     * Nullable:  true
     */
    private String waitTimeMillsInHeartbeatQueue;

    /**
     * Column:    disk_max_used_space_ratio
     * Nullable:  true
     */
    private String diskMaxUsedSpaceRatio;

    /**
     * Column:    clean_file_forcibly_enable
     * Nullable:  true
     */
    private String cleanFileForciblyEnable;

    /**
     * Column:    slave_read_enable
     * Nullable:  true
     */
    private String slaveReadEnable;

    /**
     * Column:    flush_commit_log_least_pages
     * Nullable:  true
     */
    private String flushCommitLogLeastPages;

    /**
     * Column:    msg_trace_topic_name
     * Nullable:  true
     */
    private String msgTraceTopicName;

    /**
     * Column:    expect_consumer_num_use_filter
     * Nullable:  true
     */
    private String expectConsumerNumUseFilter;

    /**
     * Column:    trace_topic_enable
     * Nullable:  true
     */
    private String traceTopicEnable;

    /**
     * Column:    use_epoll_native_selector
     * Nullable:  true
     */
    private String useEpollNativeSelector;

    /**
     * Column:    enable_property_filter
     * Nullable:  true
     */
    private String enablePropertyFilter;

    /**
     * Column:    message_delay_level
     * Nullable:  true
     */
    private String messageDelayLevel;

    /**
     * Column:    delete_commit_log_files_interval
     * Nullable:  true
     */
    private String deleteCommitLogFilesInterval;

    /**
     * Column:    broker_name
     * Nullable:  true
     */
    private String brokerName;

    /**
     * Column:    max_transfer_bytes_on_message_in_disk
     * Nullable:  true
     */
    private String maxTransferBytesOnMessageInDisk;

    /**
     * Column:    maped_file_size_consume_queue
     * Nullable:  true
     */
    private String mapedFileSizeConsumeQueue;

    /**
     * Column:    listen_port
     * Nullable:  true
     */
    private String listenPort;

    /**
     * Column:    flush_consume_queue_least_pages
     * Nullable:  true
     */
    private String flushConsumeQueueLeastPages;

    /**
     * Column:    pull_message_thread_pool_nums
     * Nullable:  true
     */
    private String pullMessageThreadPoolNums;

    /**
     * Column:    use_reentrant_lock_when_put_message
     * Nullable:  true
     */
    private String useReentrantLockWhenPutMessage;

    /**
     * Column:    flush_interval_consume_queue
     * Nullable:  true
     */
    private String flushIntervalConsumeQueue;

    /**
     * Column:    send_thread_pool_queue_capacity
     * Nullable:  true
     */
    private String sendThreadPoolQueueCapacity;

    /**
     * Column:    debug_lock_enable
     * Nullable:  true
     */
    private String debugLockEnable;

    /**
     * Column:    ha_housekeeping_interval
     * Nullable:  true
     */
    private String haHousekeepingInterval;

    /**
     * Column:    disk_fall_recorded
     * Nullable:  true
     */
    private String diskFallRecorded;

    /**
     * Column:    message_index_enable
     * Nullable:  true
     */
    private String messageIndexEnable;

    /**
     * Column:    client_async_semaphore_value
     * Nullable:  true
     */
    private String clientAsyncSemaphoreValue;

    /**
     * Column:    client_callback_executor_threads
     * Nullable:  true
     */
    private String clientCallbackExecutorThreads;

    /**
     * Column:    put_msg_index_hight_water
     * Nullable:  true
     */
    private String putMsgIndexHightWater;

    /**
     * Column:    send_message_thread_pool_nums
     * Nullable:  true
     */
    private String sendMessageThreadPoolNums;

    /**
     * Column:    client_manager_thread_pool_queue_capacity
     * Nullable:  true
     */
    private String clientManagerThreadPoolQueueCapacity;

    /**
     * Column:    server_socket_snd_buf_size
     * Nullable:  true
     */
    private String serverSocketSndBufSize;

    /**
     * Column:    max_delay_time
     * Nullable:  true
     */
    private String maxDelayTime;

    /**
     * Column:    client_socket_snd_buf_size
     * Nullable:  true
     */
    private String clientSocketSndBufSize;

    /**
     * Column:    namesrv_addr
     * Nullable:  true
     */
    private String namesrvAddr;

    /**
     * Column:    commercial_enable
     * Nullable:  true
     */
    private String commercialEnable;

    /**
     * Column:    max_hash_slot_num
     * Nullable:  true
     */
    private String maxHashSlotNum;

    /**
     * Column:    heartbeat_thread_pool_nums
     * Nullable:  true
     */
    private String heartbeatThreadPoolNums;

    /**
     * Column:    transaction_time_out
     * Nullable:  true
     */
    private String transactionTimeOut;

    /**
     * Column:    max_message_size
     * Nullable:  true
     */
    private String maxMessageSize;

    /**
     * Column:    admin_broker_thread_pool_nums
     * Nullable:  true
     */
    private String adminBrokerThreadPoolNums;

    /**
     * Column:    default_query_max_num
     * Nullable:  true
     */
    private String defaultQueryMaxNum;

    /**
     * Column:    force_register
     * Nullable:  true
     */
    private String forceRegister;

    /**
     * Column:    max_transfer_bytes_on_message_in_memory
     * Nullable:  true
     */
    private String maxTransferBytesOnMessageInMemory;

    /**
     * Column:    enable_consume_queue_ext
     * Nullable:  true
     */
    private String enableConsumeQueueExt;

    /**
     * Column:    long_polling_enable
     * Nullable:  true
     */
    private String longPollingEnable;

    /**
     * Column:    server_worker_threads
     * Nullable:  true
     */
    private String serverWorkerThreads;

    /**
     * Column:    message_index_safe
     * Nullable:  true
     */
    private String messageIndexSafe;

    /**
     * Column:    delete_consume_queue_files_interval
     * Nullable:  true
     */
    private String deleteConsumeQueueFilesInterval;

    /**
     * Column:    ha_slave_fallbehind_max
     * Nullable:  true
     */
    private String haSlaveFallbehindMax;

    /**
     * Column:    server_callback_executor_threads
     * Nullable:  true
     */
    private String serverCallbackExecutorThreads;

    /**
     * Column:    flush_commit_log_thorough_interval
     * Nullable:  true
     */
    private String flushCommitLogThoroughInterval;

    /**
     * Column:    commercial_timer_count
     * Nullable:  true
     */
    private String commercialTimerCount;

    /**
     * Column:    use_tls
     * Nullable:  true
     */
    private String useTls;

    /**
     * Column:    redelete_hanged_file_interval
     * Nullable:  true
     */
    private String redeleteHangedFileInterval;

    /**
     * Column:    flush_interval_commit_log
     * Nullable:  true
     */
    private String flushIntervalCommitLog;

    /**
     * Column:    rocketmq_home
     * Nullable:  true
     */
    private String rocketmqHome;

    /**
     * Column:    query_message_thread_pool_nums
     * Nullable:  true
     */
    private String queryMessageThreadPoolNums;

    /**
     * Column:    message_store_plugin
     * Nullable:  true
     */
    private String messageStorePlugin;

    /**
     * Column:    server_channel_max_idle_time_seconds
     * Nullable:  true
     */
    private String serverChannelMaxIdleTimeSeconds;

    /**
     * Column:    max_index_num
     * Nullable:  true
     */
    private String maxIndexNum;

    /**
     * Column:    filter_data_clean_time_span
     * Nullable:  true
     */
    private String filterDataCleanTimeSpan;

    /**
     * Column:    filter_server_nums
     * Nullable:  true
     */
    private String filterServerNums;

    /**
     * Column:    commit_commit_log_least_pages
     * Nullable:  true
     */
    private String commitCommitLogLeastPages;

    /**
     * Column:    wait_time_mills_in_pull_queue
     * Nullable:  true
     */
    private String waitTimeMillsInPullQueue;

    /**
     * Column:    ha_send_heartbeat_interval
     * Nullable:  true
     */
    private String haSendHeartbeatInterval;

    /**
     * Column:    client_channel_max_idle_time_seconds
     * Nullable:  true
     */
    private String clientChannelMaxIdleTimeSeconds;

    /**
     * Column:    filter_support_retry
     * Nullable:  true
     */
    private String filterSupportRetry;

    /**
     * Column:    flush_delay_offset_interval
     * Nullable:  true
     */
    private String flushDelayOffsetInterval;

    /**
     * Column:    duplication_enable
     * Nullable:  true
     */
    private String duplicationEnable;

    /**
     * Column:    offset_check_in_slave
     * Nullable:  true
     */
    private String offsetCheckInSlave;

    /**
     * Column:    client_close_socket_if_timeout
     * Nullable:  true
     */
    private String clientCloseSocketIfTimeout;

    /**
     * Column:    transient_store_pool_size
     * Nullable:  true
     */
    private String transientStorePoolSize;

    /**
     * Column:    wait_time_mills_in_send_queue
     * Nullable:  true
     */
    private String waitTimeMillsInSendQueue;

    /**
     * Column:    warm_maped_file_enable
     * Nullable:  true
     */
    private String warmMapedFileEnable;

    /**
     * Column:    end_transaction_thread_pool_nums
     * Nullable:  true
     */
    private String endTransactionThreadPoolNums;

    /**
     * Column:    flush_commit_log_timed
     * Nullable:  true
     */
    private String flushCommitLogTimed;

    /**
     * Column:    flush_least_pages_when_warm_maped_file
     * Nullable:  true
     */
    private String flushLeastPagesWhenWarmMapedFile;

    /**
     * Column:    client_worker_threads
     * Nullable:  true
     */
    private String clientWorkerThreads;

    /**
     * Column:    end_transaction_pool_queue_capacity
     * Nullable:  true
     */
    private String endTransactionPoolQueueCapacity;

    /**
     * Column:    register_name_server_period
     * Nullable:  true
     */
    private String registerNameServerPeriod;

    /**
     * Column:    register_broker_timeout_mills
     * Nullable:  true
     */
    private String registerBrokerTimeoutMills;

    /**
     * Column:    access_message_in_memory_max_ratio
     * Nullable:  true
     */
    private String accessMessageInMemoryMaxRatio;

    /**
     * Column:    high_speed_mode
     * Nullable:  true
     */
    private String highSpeedMode;

    /**
     * Column:    transaction_check_max
     * Nullable:  true
     */
    private String transactionCheckMax;

    /**
     * Column:    check_crc_on_recover
     * Nullable:  true
     */
    private String checkCrcOnRecover;

    /**
     * Column:    destroy_maped_file_interval_forcibly
     * Nullable:  true
     */
    private String destroyMapedFileIntervalForcibly;

    /**
     * Column:    broker_ip2
     * Nullable:  true
     */
    private String brokerIp2;

    /**
     * Column:    broker_ip1
     * Nullable:  true
     */
    private String brokerIp1;

    /**
     * Column:    commit_interval_commit_log
     * Nullable:  true
     */
    private String commitIntervalCommitLog;

    /**
     * Column:    client_oneway_semaphore_value
     * Nullable:  true
     */
    private String clientOnewaySemaphoreValue;

    /**
     * Column:    trace_on
     * Nullable:  true
     */
    private String traceOn;

    /**
     * Column:    client_manage_thread_pool_nums
     * Nullable:  true
     */
    private String clientManageThreadPoolNums;

    /**
     * Column:    channel_not_active_interval
     * Nullable:  true
     */
    private String channelNotActiveInterval;

    /**
     * Column:    cluster_name
     * Nullable:  true
     */
    private String clusterName;

    /**
     * Column:    mapped_file_size_consume_queue_ext
     * Nullable:  true
     */
    private String mappedFileSizeConsumeQueueExt;

    /**
     * Column:    consumer_manager_thread_pool_queue_capacity
     * Nullable:  true
     */
    private String consumerManagerThreadPoolQueueCapacity;

    /**
     * Column:    server_oneway_semaphore_value
     * Nullable:  true
     */
    private String serverOnewaySemaphoreValue;

    /**
     * Column:    ha_listen_port
     * Nullable:  true
     */
    private String haListenPort;

    /**
     * Column:    enable_calc_filter_bitmap
     * Nullable:  true
     */
    private String enableCalcFilterBitmap;

    /**
     * Column:    client_pooled_byte_buf_allocator_enable
     * Nullable:  true
     */
    private String clientPooledByteBufAllocatorEnable;

    /**
     * Column:    acl_enable
     * Nullable:  true
     */
    private String aclEnable;

    /**
     * Column:    store_path_root_dir
     * Nullable:  true
     */
    private String storePathRootDir;

    /**
     * Column:    sync_flush_timeout
     * Nullable:  true
     */
    private String syncFlushTimeout;

    /**
     * Column:    reject_transaction_message
     * Nullable:  true
     */
    private String rejectTransactionMessage;

    /**
     * Column:    commit_commit_log_thorough_interval
     * Nullable:  true
     */
    private String commitCommitLogThoroughInterval;

    /**
     * Column:    connect_timeout_millis
     * Nullable:  true
     */
    private String connectTimeoutMillis;

    /**
     * Column:    query_thread_pool_queue_capacity
     * Nullable:  true
     */
    private String queryThreadPoolQueueCapacity;

    /**
     * Column:    region_id
     * Nullable:  true
     */
    private String regionId;

    /**
     * Column:    consumer_manage_thread_pool_nums
     * Nullable:  true
     */
    private String consumerManageThreadPoolNums;

    /**
     * Column:    disable_consume_if_consumer_read_slowly
     * Nullable:  true
     */
    private String disableConsumeIfConsumerReadSlowly;

    /**
     * Column:    flush_consumer_offset_history_interval
     * Nullable:  true
     */
    private String flushConsumerOffsetHistoryInterval;

    /**
     * Column:    fetch_namesrv_addr_by_address_server
     * Nullable:  true
     */
    private String fetchNamesrvAddrByAddressServer;

    /**
     * Column:    ha_transfer_batch_size
     * Nullable:  true
     */
    private String haTransferBatchSize;

    /**
     * Column:    compressed_register
     * Nullable:  true
     */
    private String compressedRegister;

    /**
     * Column:    store_path_commit_log
     * Nullable:  true
     */
    private String storePathCommitLog;

    /**
     * Column:    commercial_trans_count
     * Nullable:  true
     */
    private String commercialTransCount;

    /**
     * Column:    transaction_check_interval
     * Nullable:  true
     */
    private String transactionCheckInterval;

    /**
     * Column:    start_accept_send_request_time_stamp
     * Nullable:  true
     */
    private String startAcceptSendRequestTimeStamp;

    /**
     * Column:    server_pooled_byte_buf_allocator_enable
     * Nullable:  true
     */
    private String serverPooledByteBufAllocatorEnable;

    /**
     * Column:    server_async_semaphore_value
     * Nullable:  true
     */
    private String serverAsyncSemaphoreValue;

    /**
     * Column:    heartbeat_thread_pool_queue_capacity
     * Nullable:  true
     */
    private String heartbeatThreadPoolQueueCapacity;

    /**
     * Column:    wait_time_mills_in_transaction_queue
     * Nullable:  true
     */
    private String waitTimeMillsInTransactionQueue;

    /**
     * Column:    delete_when
     * Nullable:  true
     */
    private String deleteWhen;

    /**
     * Column:    bit_map_length_consume_queue_ext
     * Nullable:  true
     */
    private String bitMapLengthConsumeQueueExt;

    /**
     * Column:    fast_fail_if_no_buffer_in_store_pool
     * Nullable:  true
     */
    private String fastFailIfNoBufferInStorePool;

    /**
     * Column:    default_topic_queue_nums
     * Nullable:  true
     */
    private String defaultTopicQueueNums;

    /**
     * Column:    notify_consumer_ids_changed_enable
     * Nullable:  true
     */
    private String notifyConsumerIdsChangedEnable;

    /**
     * Column:    flush_consume_queue_thorough_interval
     * Nullable:  true
     */
    private String flushConsumeQueueThoroughInterval;

    /**
     * Column:    file_reserved_time
     * Nullable:  true
     */
    private String fileReservedTime;

    /**
     * Column:    broker_permission
     * Nullable:  true
     */
    private String brokerPermission;

    /**
     * Column:    transfer_msg_by_heap
     * Nullable:  true
     */
    private String transferMsgByHeap;

    /**
     * Column:    pull_thread_pool_queue_capacity
     * Nullable:  true
     */
    private String pullThreadPoolQueueCapacity;

    /**
     * Column:    broker_id
     * Nullable:  true
     */
    private String brokerId;

    /**
     * Column:    max_transfer_count_on_message_in_disk
     * Nullable:  true
     */
    private String maxTransferCountOnMessageInDisk;

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

    public String getServerSelectorThreads() {
        return serverSelectorThreads;
    }

    public void setServerSelectorThreads(String serverSelectorThreads) {
        this.serverSelectorThreads = serverSelectorThreads == null ? null : serverSelectorThreads.trim();
    }

    public String getBrokerRole() {
        return brokerRole;
    }

    public void setBrokerRole(String brokerRole) {
        this.brokerRole = brokerRole == null ? null : brokerRole.trim();
    }

    public String getServerSocketRcvBufSize() {
        return serverSocketRcvBufSize;
    }

    public void setServerSocketRcvBufSize(String serverSocketRcvBufSize) {
        this.serverSocketRcvBufSize = serverSocketRcvBufSize == null ? null : serverSocketRcvBufSize.trim();
    }

    public String getOsPageCacheBusyTimeOutMills() {
        return osPageCacheBusyTimeOutMills;
    }

    public void setOsPageCacheBusyTimeOutMills(String osPageCacheBusyTimeOutMills) {
        this.osPageCacheBusyTimeOutMills = osPageCacheBusyTimeOutMills == null ? null : osPageCacheBusyTimeOutMills.trim();
    }

    public String getShortPollingTimeMills() {
        return shortPollingTimeMills;
    }

    public void setShortPollingTimeMills(String shortPollingTimeMills) {
        this.shortPollingTimeMills = shortPollingTimeMills == null ? null : shortPollingTimeMills.trim();
    }

    public String getClientSocketRcvBufSize() {
        return clientSocketRcvBufSize;
    }

    public void setClientSocketRcvBufSize(String clientSocketRcvBufSize) {
        this.clientSocketRcvBufSize = clientSocketRcvBufSize == null ? null : clientSocketRcvBufSize.trim();
    }

    public String getClusterTopicEnable() {
        return clusterTopicEnable;
    }

    public void setClusterTopicEnable(String clusterTopicEnable) {
        this.clusterTopicEnable = clusterTopicEnable == null ? null : clusterTopicEnable.trim();
    }

    public String getBrokerTopicEnable() {
        return brokerTopicEnable;
    }

    public void setBrokerTopicEnable(String brokerTopicEnable) {
        this.brokerTopicEnable = brokerTopicEnable == null ? null : brokerTopicEnable.trim();
    }

    public String getAutoCreateTopicEnable() {
        return autoCreateTopicEnable;
    }

    public void setAutoCreateTopicEnable(String autoCreateTopicEnable) {
        this.autoCreateTopicEnable = autoCreateTopicEnable == null ? null : autoCreateTopicEnable.trim();
    }

    public String getMaxErrorRateOfBloomFilter() {
        return maxErrorRateOfBloomFilter;
    }

    public void setMaxErrorRateOfBloomFilter(String maxErrorRateOfBloomFilter) {
        this.maxErrorRateOfBloomFilter = maxErrorRateOfBloomFilter == null ? null : maxErrorRateOfBloomFilter.trim();
    }

    public String getMaxMsgsNumBatch() {
        return maxMsgsNumBatch;
    }

    public void setMaxMsgsNumBatch(String maxMsgsNumBatch) {
        this.maxMsgsNumBatch = maxMsgsNumBatch == null ? null : maxMsgsNumBatch.trim();
    }

    public String getCleanResourceInterval() {
        return cleanResourceInterval;
    }

    public void setCleanResourceInterval(String cleanResourceInterval) {
        this.cleanResourceInterval = cleanResourceInterval == null ? null : cleanResourceInterval.trim();
    }

    public String getCommercialBaseCount() {
        return commercialBaseCount;
    }

    public void setCommercialBaseCount(String commercialBaseCount) {
        this.commercialBaseCount = commercialBaseCount == null ? null : commercialBaseCount.trim();
    }

    public String getMaxTransferCountOnMessageInMemory() {
        return maxTransferCountOnMessageInMemory;
    }

    public void setMaxTransferCountOnMessageInMemory(String maxTransferCountOnMessageInMemory) {
        this.maxTransferCountOnMessageInMemory = maxTransferCountOnMessageInMemory == null ? null : maxTransferCountOnMessageInMemory.trim();
    }

    public String getBrokerFastFailureEnable() {
        return brokerFastFailureEnable;
    }

    public void setBrokerFastFailureEnable(String brokerFastFailureEnable) {
        this.brokerFastFailureEnable = brokerFastFailureEnable == null ? null : brokerFastFailureEnable.trim();
    }

    public String getBrokerClusterName() {
        return brokerClusterName;
    }

    public void setBrokerClusterName(String brokerClusterName) {
        this.brokerClusterName = brokerClusterName == null ? null : brokerClusterName.trim();
    }

    public String getFlushDiskType() {
        return flushDiskType;
    }

    public void setFlushDiskType(String flushDiskType) {
        this.flushDiskType = flushDiskType == null ? null : flushDiskType.trim();
    }

    public String getCommercialBigCount() {
        return commercialBigCount;
    }

    public void setCommercialBigCount(String commercialBigCount) {
        this.commercialBigCount = commercialBigCount == null ? null : commercialBigCount.trim();
    }

    public String getMapedFileSizeCommitLog() {
        return mapedFileSizeCommitLog;
    }

    public void setMapedFileSizeCommitLog(String mapedFileSizeCommitLog) {
        this.mapedFileSizeCommitLog = mapedFileSizeCommitLog == null ? null : mapedFileSizeCommitLog.trim();
    }

    public String getConsumerFallbehindThreshold() {
        return consumerFallbehindThreshold;
    }

    public void setConsumerFallbehindThreshold(String consumerFallbehindThreshold) {
        this.consumerFallbehindThreshold = consumerFallbehindThreshold == null ? null : consumerFallbehindThreshold.trim();
    }

    public String getAutoCreateSubscriptionGroup() {
        return autoCreateSubscriptionGroup;
    }

    public void setAutoCreateSubscriptionGroup(String autoCreateSubscriptionGroup) {
        this.autoCreateSubscriptionGroup = autoCreateSubscriptionGroup == null ? null : autoCreateSubscriptionGroup.trim();
    }

    public String getTransientStorePoolEnable() {
        return transientStorePoolEnable;
    }

    public void setTransientStorePoolEnable(String transientStorePoolEnable) {
        this.transientStorePoolEnable = transientStorePoolEnable == null ? null : transientStorePoolEnable.trim();
    }

    public String getFlushConsumerOffsetInterval() {
        return flushConsumerOffsetInterval;
    }

    public void setFlushConsumerOffsetInterval(String flushConsumerOffsetInterval) {
        this.flushConsumerOffsetInterval = flushConsumerOffsetInterval == null ? null : flushConsumerOffsetInterval.trim();
    }

    public String getWaitTimeMillsInHeartbeatQueue() {
        return waitTimeMillsInHeartbeatQueue;
    }

    public void setWaitTimeMillsInHeartbeatQueue(String waitTimeMillsInHeartbeatQueue) {
        this.waitTimeMillsInHeartbeatQueue = waitTimeMillsInHeartbeatQueue == null ? null : waitTimeMillsInHeartbeatQueue.trim();
    }

    public String getDiskMaxUsedSpaceRatio() {
        return diskMaxUsedSpaceRatio;
    }

    public void setDiskMaxUsedSpaceRatio(String diskMaxUsedSpaceRatio) {
        this.diskMaxUsedSpaceRatio = diskMaxUsedSpaceRatio == null ? null : diskMaxUsedSpaceRatio.trim();
    }

    public String getCleanFileForciblyEnable() {
        return cleanFileForciblyEnable;
    }

    public void setCleanFileForciblyEnable(String cleanFileForciblyEnable) {
        this.cleanFileForciblyEnable = cleanFileForciblyEnable == null ? null : cleanFileForciblyEnable.trim();
    }

    public String getSlaveReadEnable() {
        return slaveReadEnable;
    }

    public void setSlaveReadEnable(String slaveReadEnable) {
        this.slaveReadEnable = slaveReadEnable == null ? null : slaveReadEnable.trim();
    }

    public String getFlushCommitLogLeastPages() {
        return flushCommitLogLeastPages;
    }

    public void setFlushCommitLogLeastPages(String flushCommitLogLeastPages) {
        this.flushCommitLogLeastPages = flushCommitLogLeastPages == null ? null : flushCommitLogLeastPages.trim();
    }

    public String getMsgTraceTopicName() {
        return msgTraceTopicName;
    }

    public void setMsgTraceTopicName(String msgTraceTopicName) {
        this.msgTraceTopicName = msgTraceTopicName == null ? null : msgTraceTopicName.trim();
    }

    public String getExpectConsumerNumUseFilter() {
        return expectConsumerNumUseFilter;
    }

    public void setExpectConsumerNumUseFilter(String expectConsumerNumUseFilter) {
        this.expectConsumerNumUseFilter = expectConsumerNumUseFilter == null ? null : expectConsumerNumUseFilter.trim();
    }

    public String getTraceTopicEnable() {
        return traceTopicEnable;
    }

    public void setTraceTopicEnable(String traceTopicEnable) {
        this.traceTopicEnable = traceTopicEnable == null ? null : traceTopicEnable.trim();
    }

    public String getUseEpollNativeSelector() {
        return useEpollNativeSelector;
    }

    public void setUseEpollNativeSelector(String useEpollNativeSelector) {
        this.useEpollNativeSelector = useEpollNativeSelector == null ? null : useEpollNativeSelector.trim();
    }

    public String getEnablePropertyFilter() {
        return enablePropertyFilter;
    }

    public void setEnablePropertyFilter(String enablePropertyFilter) {
        this.enablePropertyFilter = enablePropertyFilter == null ? null : enablePropertyFilter.trim();
    }

    public String getMessageDelayLevel() {
        return messageDelayLevel;
    }

    public void setMessageDelayLevel(String messageDelayLevel) {
        this.messageDelayLevel = messageDelayLevel == null ? null : messageDelayLevel.trim();
    }

    public String getDeleteCommitLogFilesInterval() {
        return deleteCommitLogFilesInterval;
    }

    public void setDeleteCommitLogFilesInterval(String deleteCommitLogFilesInterval) {
        this.deleteCommitLogFilesInterval = deleteCommitLogFilesInterval == null ? null : deleteCommitLogFilesInterval.trim();
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName == null ? null : brokerName.trim();
    }

    public String getMaxTransferBytesOnMessageInDisk() {
        return maxTransferBytesOnMessageInDisk;
    }

    public void setMaxTransferBytesOnMessageInDisk(String maxTransferBytesOnMessageInDisk) {
        this.maxTransferBytesOnMessageInDisk = maxTransferBytesOnMessageInDisk == null ? null : maxTransferBytesOnMessageInDisk.trim();
    }

    public String getMapedFileSizeConsumeQueue() {
        return mapedFileSizeConsumeQueue;
    }

    public void setMapedFileSizeConsumeQueue(String mapedFileSizeConsumeQueue) {
        this.mapedFileSizeConsumeQueue = mapedFileSizeConsumeQueue == null ? null : mapedFileSizeConsumeQueue.trim();
    }

    public String getListenPort() {
        return listenPort;
    }

    public void setListenPort(String listenPort) {
        this.listenPort = listenPort == null ? null : listenPort.trim();
    }

    public String getFlushConsumeQueueLeastPages() {
        return flushConsumeQueueLeastPages;
    }

    public void setFlushConsumeQueueLeastPages(String flushConsumeQueueLeastPages) {
        this.flushConsumeQueueLeastPages = flushConsumeQueueLeastPages == null ? null : flushConsumeQueueLeastPages.trim();
    }

    public String getPullMessageThreadPoolNums() {
        return pullMessageThreadPoolNums;
    }

    public void setPullMessageThreadPoolNums(String pullMessageThreadPoolNums) {
        this.pullMessageThreadPoolNums = pullMessageThreadPoolNums == null ? null : pullMessageThreadPoolNums.trim();
    }

    public String getUseReentrantLockWhenPutMessage() {
        return useReentrantLockWhenPutMessage;
    }

    public void setUseReentrantLockWhenPutMessage(String useReentrantLockWhenPutMessage) {
        this.useReentrantLockWhenPutMessage = useReentrantLockWhenPutMessage == null ? null : useReentrantLockWhenPutMessage.trim();
    }

    public String getFlushIntervalConsumeQueue() {
        return flushIntervalConsumeQueue;
    }

    public void setFlushIntervalConsumeQueue(String flushIntervalConsumeQueue) {
        this.flushIntervalConsumeQueue = flushIntervalConsumeQueue == null ? null : flushIntervalConsumeQueue.trim();
    }

    public String getSendThreadPoolQueueCapacity() {
        return sendThreadPoolQueueCapacity;
    }

    public void setSendThreadPoolQueueCapacity(String sendThreadPoolQueueCapacity) {
        this.sendThreadPoolQueueCapacity = sendThreadPoolQueueCapacity == null ? null : sendThreadPoolQueueCapacity.trim();
    }

    public String getDebugLockEnable() {
        return debugLockEnable;
    }

    public void setDebugLockEnable(String debugLockEnable) {
        this.debugLockEnable = debugLockEnable == null ? null : debugLockEnable.trim();
    }

    public String getHaHousekeepingInterval() {
        return haHousekeepingInterval;
    }

    public void setHaHousekeepingInterval(String haHousekeepingInterval) {
        this.haHousekeepingInterval = haHousekeepingInterval == null ? null : haHousekeepingInterval.trim();
    }

    public String getDiskFallRecorded() {
        return diskFallRecorded;
    }

    public void setDiskFallRecorded(String diskFallRecorded) {
        this.diskFallRecorded = diskFallRecorded == null ? null : diskFallRecorded.trim();
    }

    public String getMessageIndexEnable() {
        return messageIndexEnable;
    }

    public void setMessageIndexEnable(String messageIndexEnable) {
        this.messageIndexEnable = messageIndexEnable == null ? null : messageIndexEnable.trim();
    }

    public String getClientAsyncSemaphoreValue() {
        return clientAsyncSemaphoreValue;
    }

    public void setClientAsyncSemaphoreValue(String clientAsyncSemaphoreValue) {
        this.clientAsyncSemaphoreValue = clientAsyncSemaphoreValue == null ? null : clientAsyncSemaphoreValue.trim();
    }

    public String getClientCallbackExecutorThreads() {
        return clientCallbackExecutorThreads;
    }

    public void setClientCallbackExecutorThreads(String clientCallbackExecutorThreads) {
        this.clientCallbackExecutorThreads = clientCallbackExecutorThreads == null ? null : clientCallbackExecutorThreads.trim();
    }

    public String getPutMsgIndexHightWater() {
        return putMsgIndexHightWater;
    }

    public void setPutMsgIndexHightWater(String putMsgIndexHightWater) {
        this.putMsgIndexHightWater = putMsgIndexHightWater == null ? null : putMsgIndexHightWater.trim();
    }

    public String getSendMessageThreadPoolNums() {
        return sendMessageThreadPoolNums;
    }

    public void setSendMessageThreadPoolNums(String sendMessageThreadPoolNums) {
        this.sendMessageThreadPoolNums = sendMessageThreadPoolNums == null ? null : sendMessageThreadPoolNums.trim();
    }

    public String getClientManagerThreadPoolQueueCapacity() {
        return clientManagerThreadPoolQueueCapacity;
    }

    public void setClientManagerThreadPoolQueueCapacity(String clientManagerThreadPoolQueueCapacity) {
        this.clientManagerThreadPoolQueueCapacity = clientManagerThreadPoolQueueCapacity == null ? null : clientManagerThreadPoolQueueCapacity.trim();
    }

    public String getServerSocketSndBufSize() {
        return serverSocketSndBufSize;
    }

    public void setServerSocketSndBufSize(String serverSocketSndBufSize) {
        this.serverSocketSndBufSize = serverSocketSndBufSize == null ? null : serverSocketSndBufSize.trim();
    }

    public String getMaxDelayTime() {
        return maxDelayTime;
    }

    public void setMaxDelayTime(String maxDelayTime) {
        this.maxDelayTime = maxDelayTime == null ? null : maxDelayTime.trim();
    }

    public String getClientSocketSndBufSize() {
        return clientSocketSndBufSize;
    }

    public void setClientSocketSndBufSize(String clientSocketSndBufSize) {
        this.clientSocketSndBufSize = clientSocketSndBufSize == null ? null : clientSocketSndBufSize.trim();
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr == null ? null : namesrvAddr.trim();
    }

    public String getCommercialEnable() {
        return commercialEnable;
    }

    public void setCommercialEnable(String commercialEnable) {
        this.commercialEnable = commercialEnable == null ? null : commercialEnable.trim();
    }

    public String getMaxHashSlotNum() {
        return maxHashSlotNum;
    }

    public void setMaxHashSlotNum(String maxHashSlotNum) {
        this.maxHashSlotNum = maxHashSlotNum == null ? null : maxHashSlotNum.trim();
    }

    public String getHeartbeatThreadPoolNums() {
        return heartbeatThreadPoolNums;
    }

    public void setHeartbeatThreadPoolNums(String heartbeatThreadPoolNums) {
        this.heartbeatThreadPoolNums = heartbeatThreadPoolNums == null ? null : heartbeatThreadPoolNums.trim();
    }

    public String getTransactionTimeOut() {
        return transactionTimeOut;
    }

    public void setTransactionTimeOut(String transactionTimeOut) {
        this.transactionTimeOut = transactionTimeOut == null ? null : transactionTimeOut.trim();
    }

    public String getMaxMessageSize() {
        return maxMessageSize;
    }

    public void setMaxMessageSize(String maxMessageSize) {
        this.maxMessageSize = maxMessageSize == null ? null : maxMessageSize.trim();
    }

    public String getAdminBrokerThreadPoolNums() {
        return adminBrokerThreadPoolNums;
    }

    public void setAdminBrokerThreadPoolNums(String adminBrokerThreadPoolNums) {
        this.adminBrokerThreadPoolNums = adminBrokerThreadPoolNums == null ? null : adminBrokerThreadPoolNums.trim();
    }

    public String getDefaultQueryMaxNum() {
        return defaultQueryMaxNum;
    }

    public void setDefaultQueryMaxNum(String defaultQueryMaxNum) {
        this.defaultQueryMaxNum = defaultQueryMaxNum == null ? null : defaultQueryMaxNum.trim();
    }

    public String getForceRegister() {
        return forceRegister;
    }

    public void setForceRegister(String forceRegister) {
        this.forceRegister = forceRegister == null ? null : forceRegister.trim();
    }

    public String getMaxTransferBytesOnMessageInMemory() {
        return maxTransferBytesOnMessageInMemory;
    }

    public void setMaxTransferBytesOnMessageInMemory(String maxTransferBytesOnMessageInMemory) {
        this.maxTransferBytesOnMessageInMemory = maxTransferBytesOnMessageInMemory == null ? null : maxTransferBytesOnMessageInMemory.trim();
    }

    public String getEnableConsumeQueueExt() {
        return enableConsumeQueueExt;
    }

    public void setEnableConsumeQueueExt(String enableConsumeQueueExt) {
        this.enableConsumeQueueExt = enableConsumeQueueExt == null ? null : enableConsumeQueueExt.trim();
    }

    public String getLongPollingEnable() {
        return longPollingEnable;
    }

    public void setLongPollingEnable(String longPollingEnable) {
        this.longPollingEnable = longPollingEnable == null ? null : longPollingEnable.trim();
    }

    public String getServerWorkerThreads() {
        return serverWorkerThreads;
    }

    public void setServerWorkerThreads(String serverWorkerThreads) {
        this.serverWorkerThreads = serverWorkerThreads == null ? null : serverWorkerThreads.trim();
    }

    public String getMessageIndexSafe() {
        return messageIndexSafe;
    }

    public void setMessageIndexSafe(String messageIndexSafe) {
        this.messageIndexSafe = messageIndexSafe == null ? null : messageIndexSafe.trim();
    }

    public String getDeleteConsumeQueueFilesInterval() {
        return deleteConsumeQueueFilesInterval;
    }

    public void setDeleteConsumeQueueFilesInterval(String deleteConsumeQueueFilesInterval) {
        this.deleteConsumeQueueFilesInterval = deleteConsumeQueueFilesInterval == null ? null : deleteConsumeQueueFilesInterval.trim();
    }

    public String getHaSlaveFallbehindMax() {
        return haSlaveFallbehindMax;
    }

    public void setHaSlaveFallbehindMax(String haSlaveFallbehindMax) {
        this.haSlaveFallbehindMax = haSlaveFallbehindMax == null ? null : haSlaveFallbehindMax.trim();
    }

    public String getServerCallbackExecutorThreads() {
        return serverCallbackExecutorThreads;
    }

    public void setServerCallbackExecutorThreads(String serverCallbackExecutorThreads) {
        this.serverCallbackExecutorThreads = serverCallbackExecutorThreads == null ? null : serverCallbackExecutorThreads.trim();
    }

    public String getFlushCommitLogThoroughInterval() {
        return flushCommitLogThoroughInterval;
    }

    public void setFlushCommitLogThoroughInterval(String flushCommitLogThoroughInterval) {
        this.flushCommitLogThoroughInterval = flushCommitLogThoroughInterval == null ? null : flushCommitLogThoroughInterval.trim();
    }

    public String getCommercialTimerCount() {
        return commercialTimerCount;
    }

    public void setCommercialTimerCount(String commercialTimerCount) {
        this.commercialTimerCount = commercialTimerCount == null ? null : commercialTimerCount.trim();
    }

    public String getUseTls() {
        return useTls;
    }

    public void setUseTls(String useTls) {
        this.useTls = useTls == null ? null : useTls.trim();
    }

    public String getRedeleteHangedFileInterval() {
        return redeleteHangedFileInterval;
    }

    public void setRedeleteHangedFileInterval(String redeleteHangedFileInterval) {
        this.redeleteHangedFileInterval = redeleteHangedFileInterval == null ? null : redeleteHangedFileInterval.trim();
    }

    public String getFlushIntervalCommitLog() {
        return flushIntervalCommitLog;
    }

    public void setFlushIntervalCommitLog(String flushIntervalCommitLog) {
        this.flushIntervalCommitLog = flushIntervalCommitLog == null ? null : flushIntervalCommitLog.trim();
    }

    public String getRocketmqHome() {
        return rocketmqHome;
    }

    public void setRocketmqHome(String rocketmqHome) {
        this.rocketmqHome = rocketmqHome == null ? null : rocketmqHome.trim();
    }

    public String getQueryMessageThreadPoolNums() {
        return queryMessageThreadPoolNums;
    }

    public void setQueryMessageThreadPoolNums(String queryMessageThreadPoolNums) {
        this.queryMessageThreadPoolNums = queryMessageThreadPoolNums == null ? null : queryMessageThreadPoolNums.trim();
    }

    public String getMessageStorePlugin() {
        return messageStorePlugin;
    }

    public void setMessageStorePlugin(String messageStorePlugin) {
        this.messageStorePlugin = messageStorePlugin == null ? null : messageStorePlugin.trim();
    }

    public String getServerChannelMaxIdleTimeSeconds() {
        return serverChannelMaxIdleTimeSeconds;
    }

    public void setServerChannelMaxIdleTimeSeconds(String serverChannelMaxIdleTimeSeconds) {
        this.serverChannelMaxIdleTimeSeconds = serverChannelMaxIdleTimeSeconds == null ? null : serverChannelMaxIdleTimeSeconds.trim();
    }

    public String getMaxIndexNum() {
        return maxIndexNum;
    }

    public void setMaxIndexNum(String maxIndexNum) {
        this.maxIndexNum = maxIndexNum == null ? null : maxIndexNum.trim();
    }

    public String getFilterDataCleanTimeSpan() {
        return filterDataCleanTimeSpan;
    }

    public void setFilterDataCleanTimeSpan(String filterDataCleanTimeSpan) {
        this.filterDataCleanTimeSpan = filterDataCleanTimeSpan == null ? null : filterDataCleanTimeSpan.trim();
    }

    public String getFilterServerNums() {
        return filterServerNums;
    }

    public void setFilterServerNums(String filterServerNums) {
        this.filterServerNums = filterServerNums == null ? null : filterServerNums.trim();
    }

    public String getCommitCommitLogLeastPages() {
        return commitCommitLogLeastPages;
    }

    public void setCommitCommitLogLeastPages(String commitCommitLogLeastPages) {
        this.commitCommitLogLeastPages = commitCommitLogLeastPages == null ? null : commitCommitLogLeastPages.trim();
    }

    public String getWaitTimeMillsInPullQueue() {
        return waitTimeMillsInPullQueue;
    }

    public void setWaitTimeMillsInPullQueue(String waitTimeMillsInPullQueue) {
        this.waitTimeMillsInPullQueue = waitTimeMillsInPullQueue == null ? null : waitTimeMillsInPullQueue.trim();
    }

    public String getHaSendHeartbeatInterval() {
        return haSendHeartbeatInterval;
    }

    public void setHaSendHeartbeatInterval(String haSendHeartbeatInterval) {
        this.haSendHeartbeatInterval = haSendHeartbeatInterval == null ? null : haSendHeartbeatInterval.trim();
    }

    public String getClientChannelMaxIdleTimeSeconds() {
        return clientChannelMaxIdleTimeSeconds;
    }

    public void setClientChannelMaxIdleTimeSeconds(String clientChannelMaxIdleTimeSeconds) {
        this.clientChannelMaxIdleTimeSeconds = clientChannelMaxIdleTimeSeconds == null ? null : clientChannelMaxIdleTimeSeconds.trim();
    }

    public String getFilterSupportRetry() {
        return filterSupportRetry;
    }

    public void setFilterSupportRetry(String filterSupportRetry) {
        this.filterSupportRetry = filterSupportRetry == null ? null : filterSupportRetry.trim();
    }

    public String getFlushDelayOffsetInterval() {
        return flushDelayOffsetInterval;
    }

    public void setFlushDelayOffsetInterval(String flushDelayOffsetInterval) {
        this.flushDelayOffsetInterval = flushDelayOffsetInterval == null ? null : flushDelayOffsetInterval.trim();
    }

    public String getDuplicationEnable() {
        return duplicationEnable;
    }

    public void setDuplicationEnable(String duplicationEnable) {
        this.duplicationEnable = duplicationEnable == null ? null : duplicationEnable.trim();
    }

    public String getOffsetCheckInSlave() {
        return offsetCheckInSlave;
    }

    public void setOffsetCheckInSlave(String offsetCheckInSlave) {
        this.offsetCheckInSlave = offsetCheckInSlave == null ? null : offsetCheckInSlave.trim();
    }

    public String getClientCloseSocketIfTimeout() {
        return clientCloseSocketIfTimeout;
    }

    public void setClientCloseSocketIfTimeout(String clientCloseSocketIfTimeout) {
        this.clientCloseSocketIfTimeout = clientCloseSocketIfTimeout == null ? null : clientCloseSocketIfTimeout.trim();
    }

    public String getTransientStorePoolSize() {
        return transientStorePoolSize;
    }

    public void setTransientStorePoolSize(String transientStorePoolSize) {
        this.transientStorePoolSize = transientStorePoolSize == null ? null : transientStorePoolSize.trim();
    }

    public String getWaitTimeMillsInSendQueue() {
        return waitTimeMillsInSendQueue;
    }

    public void setWaitTimeMillsInSendQueue(String waitTimeMillsInSendQueue) {
        this.waitTimeMillsInSendQueue = waitTimeMillsInSendQueue == null ? null : waitTimeMillsInSendQueue.trim();
    }

    public String getWarmMapedFileEnable() {
        return warmMapedFileEnable;
    }

    public void setWarmMapedFileEnable(String warmMapedFileEnable) {
        this.warmMapedFileEnable = warmMapedFileEnable == null ? null : warmMapedFileEnable.trim();
    }

    public String getEndTransactionThreadPoolNums() {
        return endTransactionThreadPoolNums;
    }

    public void setEndTransactionThreadPoolNums(String endTransactionThreadPoolNums) {
        this.endTransactionThreadPoolNums = endTransactionThreadPoolNums == null ? null : endTransactionThreadPoolNums.trim();
    }

    public String getFlushCommitLogTimed() {
        return flushCommitLogTimed;
    }

    public void setFlushCommitLogTimed(String flushCommitLogTimed) {
        this.flushCommitLogTimed = flushCommitLogTimed == null ? null : flushCommitLogTimed.trim();
    }

    public String getFlushLeastPagesWhenWarmMapedFile() {
        return flushLeastPagesWhenWarmMapedFile;
    }

    public void setFlushLeastPagesWhenWarmMapedFile(String flushLeastPagesWhenWarmMapedFile) {
        this.flushLeastPagesWhenWarmMapedFile = flushLeastPagesWhenWarmMapedFile == null ? null : flushLeastPagesWhenWarmMapedFile.trim();
    }

    public String getClientWorkerThreads() {
        return clientWorkerThreads;
    }

    public void setClientWorkerThreads(String clientWorkerThreads) {
        this.clientWorkerThreads = clientWorkerThreads == null ? null : clientWorkerThreads.trim();
    }

    public String getEndTransactionPoolQueueCapacity() {
        return endTransactionPoolQueueCapacity;
    }

    public void setEndTransactionPoolQueueCapacity(String endTransactionPoolQueueCapacity) {
        this.endTransactionPoolQueueCapacity = endTransactionPoolQueueCapacity == null ? null : endTransactionPoolQueueCapacity.trim();
    }

    public String getRegisterNameServerPeriod() {
        return registerNameServerPeriod;
    }

    public void setRegisterNameServerPeriod(String registerNameServerPeriod) {
        this.registerNameServerPeriod = registerNameServerPeriod == null ? null : registerNameServerPeriod.trim();
    }

    public String getRegisterBrokerTimeoutMills() {
        return registerBrokerTimeoutMills;
    }

    public void setRegisterBrokerTimeoutMills(String registerBrokerTimeoutMills) {
        this.registerBrokerTimeoutMills = registerBrokerTimeoutMills == null ? null : registerBrokerTimeoutMills.trim();
    }

    public String getAccessMessageInMemoryMaxRatio() {
        return accessMessageInMemoryMaxRatio;
    }

    public void setAccessMessageInMemoryMaxRatio(String accessMessageInMemoryMaxRatio) {
        this.accessMessageInMemoryMaxRatio = accessMessageInMemoryMaxRatio == null ? null : accessMessageInMemoryMaxRatio.trim();
    }

    public String getHighSpeedMode() {
        return highSpeedMode;
    }

    public void setHighSpeedMode(String highSpeedMode) {
        this.highSpeedMode = highSpeedMode == null ? null : highSpeedMode.trim();
    }

    public String getTransactionCheckMax() {
        return transactionCheckMax;
    }

    public void setTransactionCheckMax(String transactionCheckMax) {
        this.transactionCheckMax = transactionCheckMax == null ? null : transactionCheckMax.trim();
    }

    public String getCheckCrcOnRecover() {
        return checkCrcOnRecover;
    }

    public void setCheckCrcOnRecover(String checkCrcOnRecover) {
        this.checkCrcOnRecover = checkCrcOnRecover == null ? null : checkCrcOnRecover.trim();
    }

    public String getDestroyMapedFileIntervalForcibly() {
        return destroyMapedFileIntervalForcibly;
    }

    public void setDestroyMapedFileIntervalForcibly(String destroyMapedFileIntervalForcibly) {
        this.destroyMapedFileIntervalForcibly = destroyMapedFileIntervalForcibly == null ? null : destroyMapedFileIntervalForcibly.trim();
    }

    public String getBrokerIp2() {
        return brokerIp2;
    }

    public void setBrokerIp2(String brokerIp2) {
        this.brokerIp2 = brokerIp2 == null ? null : brokerIp2.trim();
    }

    public String getBrokerIp1() {
        return brokerIp1;
    }

    public void setBrokerIp1(String brokerIp1) {
        this.brokerIp1 = brokerIp1 == null ? null : brokerIp1.trim();
    }

    public String getCommitIntervalCommitLog() {
        return commitIntervalCommitLog;
    }

    public void setCommitIntervalCommitLog(String commitIntervalCommitLog) {
        this.commitIntervalCommitLog = commitIntervalCommitLog == null ? null : commitIntervalCommitLog.trim();
    }

    public String getClientOnewaySemaphoreValue() {
        return clientOnewaySemaphoreValue;
    }

    public void setClientOnewaySemaphoreValue(String clientOnewaySemaphoreValue) {
        this.clientOnewaySemaphoreValue = clientOnewaySemaphoreValue == null ? null : clientOnewaySemaphoreValue.trim();
    }

    public String getTraceOn() {
        return traceOn;
    }

    public void setTraceOn(String traceOn) {
        this.traceOn = traceOn == null ? null : traceOn.trim();
    }

    public String getClientManageThreadPoolNums() {
        return clientManageThreadPoolNums;
    }

    public void setClientManageThreadPoolNums(String clientManageThreadPoolNums) {
        this.clientManageThreadPoolNums = clientManageThreadPoolNums == null ? null : clientManageThreadPoolNums.trim();
    }

    public String getChannelNotActiveInterval() {
        return channelNotActiveInterval;
    }

    public void setChannelNotActiveInterval(String channelNotActiveInterval) {
        this.channelNotActiveInterval = channelNotActiveInterval == null ? null : channelNotActiveInterval.trim();
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName == null ? null : clusterName.trim();
    }

    public String getMappedFileSizeConsumeQueueExt() {
        return mappedFileSizeConsumeQueueExt;
    }

    public void setMappedFileSizeConsumeQueueExt(String mappedFileSizeConsumeQueueExt) {
        this.mappedFileSizeConsumeQueueExt = mappedFileSizeConsumeQueueExt == null ? null : mappedFileSizeConsumeQueueExt.trim();
    }

    public String getConsumerManagerThreadPoolQueueCapacity() {
        return consumerManagerThreadPoolQueueCapacity;
    }

    public void setConsumerManagerThreadPoolQueueCapacity(String consumerManagerThreadPoolQueueCapacity) {
        this.consumerManagerThreadPoolQueueCapacity = consumerManagerThreadPoolQueueCapacity == null ? null : consumerManagerThreadPoolQueueCapacity.trim();
    }

    public String getServerOnewaySemaphoreValue() {
        return serverOnewaySemaphoreValue;
    }

    public void setServerOnewaySemaphoreValue(String serverOnewaySemaphoreValue) {
        this.serverOnewaySemaphoreValue = serverOnewaySemaphoreValue == null ? null : serverOnewaySemaphoreValue.trim();
    }

    public String getHaListenPort() {
        return haListenPort;
    }

    public void setHaListenPort(String haListenPort) {
        this.haListenPort = haListenPort == null ? null : haListenPort.trim();
    }

    public String getEnableCalcFilterBitmap() {
        return enableCalcFilterBitmap;
    }

    public void setEnableCalcFilterBitmap(String enableCalcFilterBitmap) {
        this.enableCalcFilterBitmap = enableCalcFilterBitmap == null ? null : enableCalcFilterBitmap.trim();
    }

    public String getClientPooledByteBufAllocatorEnable() {
        return clientPooledByteBufAllocatorEnable;
    }

    public void setClientPooledByteBufAllocatorEnable(String clientPooledByteBufAllocatorEnable) {
        this.clientPooledByteBufAllocatorEnable = clientPooledByteBufAllocatorEnable == null ? null : clientPooledByteBufAllocatorEnable.trim();
    }

    public String getAclEnable() {
        return aclEnable;
    }

    public void setAclEnable(String aclEnable) {
        this.aclEnable = aclEnable == null ? null : aclEnable.trim();
    }

    public String getStorePathRootDir() {
        return storePathRootDir;
    }

    public void setStorePathRootDir(String storePathRootDir) {
        this.storePathRootDir = storePathRootDir == null ? null : storePathRootDir.trim();
    }

    public String getSyncFlushTimeout() {
        return syncFlushTimeout;
    }

    public void setSyncFlushTimeout(String syncFlushTimeout) {
        this.syncFlushTimeout = syncFlushTimeout == null ? null : syncFlushTimeout.trim();
    }

    public String getRejectTransactionMessage() {
        return rejectTransactionMessage;
    }

    public void setRejectTransactionMessage(String rejectTransactionMessage) {
        this.rejectTransactionMessage = rejectTransactionMessage == null ? null : rejectTransactionMessage.trim();
    }

    public String getCommitCommitLogThoroughInterval() {
        return commitCommitLogThoroughInterval;
    }

    public void setCommitCommitLogThoroughInterval(String commitCommitLogThoroughInterval) {
        this.commitCommitLogThoroughInterval = commitCommitLogThoroughInterval == null ? null : commitCommitLogThoroughInterval.trim();
    }

    public String getConnectTimeoutMillis() {
        return connectTimeoutMillis;
    }

    public void setConnectTimeoutMillis(String connectTimeoutMillis) {
        this.connectTimeoutMillis = connectTimeoutMillis == null ? null : connectTimeoutMillis.trim();
    }

    public String getQueryThreadPoolQueueCapacity() {
        return queryThreadPoolQueueCapacity;
    }

    public void setQueryThreadPoolQueueCapacity(String queryThreadPoolQueueCapacity) {
        this.queryThreadPoolQueueCapacity = queryThreadPoolQueueCapacity == null ? null : queryThreadPoolQueueCapacity.trim();
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }

    public String getConsumerManageThreadPoolNums() {
        return consumerManageThreadPoolNums;
    }

    public void setConsumerManageThreadPoolNums(String consumerManageThreadPoolNums) {
        this.consumerManageThreadPoolNums = consumerManageThreadPoolNums == null ? null : consumerManageThreadPoolNums.trim();
    }

    public String getDisableConsumeIfConsumerReadSlowly() {
        return disableConsumeIfConsumerReadSlowly;
    }

    public void setDisableConsumeIfConsumerReadSlowly(String disableConsumeIfConsumerReadSlowly) {
        this.disableConsumeIfConsumerReadSlowly = disableConsumeIfConsumerReadSlowly == null ? null : disableConsumeIfConsumerReadSlowly.trim();
    }

    public String getFlushConsumerOffsetHistoryInterval() {
        return flushConsumerOffsetHistoryInterval;
    }

    public void setFlushConsumerOffsetHistoryInterval(String flushConsumerOffsetHistoryInterval) {
        this.flushConsumerOffsetHistoryInterval = flushConsumerOffsetHistoryInterval == null ? null : flushConsumerOffsetHistoryInterval.trim();
    }

    public String getFetchNamesrvAddrByAddressServer() {
        return fetchNamesrvAddrByAddressServer;
    }

    public void setFetchNamesrvAddrByAddressServer(String fetchNamesrvAddrByAddressServer) {
        this.fetchNamesrvAddrByAddressServer = fetchNamesrvAddrByAddressServer == null ? null : fetchNamesrvAddrByAddressServer.trim();
    }

    public String getHaTransferBatchSize() {
        return haTransferBatchSize;
    }

    public void setHaTransferBatchSize(String haTransferBatchSize) {
        this.haTransferBatchSize = haTransferBatchSize == null ? null : haTransferBatchSize.trim();
    }

    public String getCompressedRegister() {
        return compressedRegister;
    }

    public void setCompressedRegister(String compressedRegister) {
        this.compressedRegister = compressedRegister == null ? null : compressedRegister.trim();
    }

    public String getStorePathCommitLog() {
        return storePathCommitLog;
    }

    public void setStorePathCommitLog(String storePathCommitLog) {
        this.storePathCommitLog = storePathCommitLog == null ? null : storePathCommitLog.trim();
    }

    public String getCommercialTransCount() {
        return commercialTransCount;
    }

    public void setCommercialTransCount(String commercialTransCount) {
        this.commercialTransCount = commercialTransCount == null ? null : commercialTransCount.trim();
    }

    public String getTransactionCheckInterval() {
        return transactionCheckInterval;
    }

    public void setTransactionCheckInterval(String transactionCheckInterval) {
        this.transactionCheckInterval = transactionCheckInterval == null ? null : transactionCheckInterval.trim();
    }

    public String getStartAcceptSendRequestTimeStamp() {
        return startAcceptSendRequestTimeStamp;
    }

    public void setStartAcceptSendRequestTimeStamp(String startAcceptSendRequestTimeStamp) {
        this.startAcceptSendRequestTimeStamp = startAcceptSendRequestTimeStamp == null ? null : startAcceptSendRequestTimeStamp.trim();
    }

    public String getServerPooledByteBufAllocatorEnable() {
        return serverPooledByteBufAllocatorEnable;
    }

    public void setServerPooledByteBufAllocatorEnable(String serverPooledByteBufAllocatorEnable) {
        this.serverPooledByteBufAllocatorEnable = serverPooledByteBufAllocatorEnable == null ? null : serverPooledByteBufAllocatorEnable.trim();
    }

    public String getServerAsyncSemaphoreValue() {
        return serverAsyncSemaphoreValue;
    }

    public void setServerAsyncSemaphoreValue(String serverAsyncSemaphoreValue) {
        this.serverAsyncSemaphoreValue = serverAsyncSemaphoreValue == null ? null : serverAsyncSemaphoreValue.trim();
    }

    public String getHeartbeatThreadPoolQueueCapacity() {
        return heartbeatThreadPoolQueueCapacity;
    }

    public void setHeartbeatThreadPoolQueueCapacity(String heartbeatThreadPoolQueueCapacity) {
        this.heartbeatThreadPoolQueueCapacity = heartbeatThreadPoolQueueCapacity == null ? null : heartbeatThreadPoolQueueCapacity.trim();
    }

    public String getWaitTimeMillsInTransactionQueue() {
        return waitTimeMillsInTransactionQueue;
    }

    public void setWaitTimeMillsInTransactionQueue(String waitTimeMillsInTransactionQueue) {
        this.waitTimeMillsInTransactionQueue = waitTimeMillsInTransactionQueue == null ? null : waitTimeMillsInTransactionQueue.trim();
    }

    public String getDeleteWhen() {
        return deleteWhen;
    }

    public void setDeleteWhen(String deleteWhen) {
        this.deleteWhen = deleteWhen == null ? null : deleteWhen.trim();
    }

    public String getBitMapLengthConsumeQueueExt() {
        return bitMapLengthConsumeQueueExt;
    }

    public void setBitMapLengthConsumeQueueExt(String bitMapLengthConsumeQueueExt) {
        this.bitMapLengthConsumeQueueExt = bitMapLengthConsumeQueueExt == null ? null : bitMapLengthConsumeQueueExt.trim();
    }

    public String getFastFailIfNoBufferInStorePool() {
        return fastFailIfNoBufferInStorePool;
    }

    public void setFastFailIfNoBufferInStorePool(String fastFailIfNoBufferInStorePool) {
        this.fastFailIfNoBufferInStorePool = fastFailIfNoBufferInStorePool == null ? null : fastFailIfNoBufferInStorePool.trim();
    }

    public String getDefaultTopicQueueNums() {
        return defaultTopicQueueNums;
    }

    public void setDefaultTopicQueueNums(String defaultTopicQueueNums) {
        this.defaultTopicQueueNums = defaultTopicQueueNums == null ? null : defaultTopicQueueNums.trim();
    }

    public String getNotifyConsumerIdsChangedEnable() {
        return notifyConsumerIdsChangedEnable;
    }

    public void setNotifyConsumerIdsChangedEnable(String notifyConsumerIdsChangedEnable) {
        this.notifyConsumerIdsChangedEnable = notifyConsumerIdsChangedEnable == null ? null : notifyConsumerIdsChangedEnable.trim();
    }

    public String getFlushConsumeQueueThoroughInterval() {
        return flushConsumeQueueThoroughInterval;
    }

    public void setFlushConsumeQueueThoroughInterval(String flushConsumeQueueThoroughInterval) {
        this.flushConsumeQueueThoroughInterval = flushConsumeQueueThoroughInterval == null ? null : flushConsumeQueueThoroughInterval.trim();
    }

    public String getFileReservedTime() {
        return fileReservedTime;
    }

    public void setFileReservedTime(String fileReservedTime) {
        this.fileReservedTime = fileReservedTime == null ? null : fileReservedTime.trim();
    }

    public String getBrokerPermission() {
        return brokerPermission;
    }

    public void setBrokerPermission(String brokerPermission) {
        this.brokerPermission = brokerPermission == null ? null : brokerPermission.trim();
    }

    public String getTransferMsgByHeap() {
        return transferMsgByHeap;
    }

    public void setTransferMsgByHeap(String transferMsgByHeap) {
        this.transferMsgByHeap = transferMsgByHeap == null ? null : transferMsgByHeap.trim();
    }

    public String getPullThreadPoolQueueCapacity() {
        return pullThreadPoolQueueCapacity;
    }

    public void setPullThreadPoolQueueCapacity(String pullThreadPoolQueueCapacity) {
        this.pullThreadPoolQueueCapacity = pullThreadPoolQueueCapacity == null ? null : pullThreadPoolQueueCapacity.trim();
    }

    public String getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId == null ? null : brokerId.trim();
    }

    public String getMaxTransferCountOnMessageInDisk() {
        return maxTransferCountOnMessageInDisk;
    }

    public void setMaxTransferCountOnMessageInDisk(String maxTransferCountOnMessageInDisk) {
        this.maxTransferCountOnMessageInDisk = maxTransferCountOnMessageInDisk == null ? null : maxTransferCountOnMessageInDisk.trim();
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