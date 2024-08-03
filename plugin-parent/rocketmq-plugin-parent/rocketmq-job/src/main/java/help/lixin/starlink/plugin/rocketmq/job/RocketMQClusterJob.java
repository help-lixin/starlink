package help.lixin.starlink.plugin.rocketmq.job;

import org.springframework.scheduling.annotation.Scheduled;

import help.lixin.starlink.plugin.rocketmq.job.service.RocketMQClusterJobService;
import help.lixin.starlink.plugin.rocketmq.job.service.RocketMQTopicJobService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/26 9:49 下午
 * @Description user定时任务
 */
public class RocketMQClusterJob {

    private RocketMQClusterJobService clusterJobService;
    private RocketMQTopicJobService rocketMQTopicJobService;

    @Scheduled(cron = "${scheduling.cron.rocketmq.cluster}")
    @SchedulerLock(name = "rocketmqClusterSchedulerLock", lockAtMostFor = "1m", lockAtLeastFor = "1m")
    public void pullCluster() {
        clusterJobService.pullCluster();
        rocketMQTopicJobService.pullTopic();
    }

    public RocketMQClusterJob(RocketMQClusterJobService clusterJobService,
        RocketMQTopicJobService rocketMQTopicJobService) {
        this.clusterJobService = clusterJobService;
        this.rocketMQTopicJobService = rocketMQTopicJobService;
    }
}
