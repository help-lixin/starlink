package help.lixin.starlink.plugin.rocketmq.job;

import org.springframework.scheduling.annotation.Scheduled;

import help.lixin.starlink.plugin.rocketmq.job.service.RocketMQTopicJobService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/26 9:49 下午
 * @Description group定时任务
 */
public class RocketMQTopicJob {

    private RocketMQTopicJobService rocketMQTopicJobService;

    @Scheduled(cron = "${scheduling.cron.rocketmq.topic}")
    @SchedulerLock(name = "rocketmqTopicSchedulerLock", lockAtMostFor = "1m", lockAtLeastFor = "1m")
    public void pullTopic() {
        rocketMQTopicJobService.pullTopic();
    }

    public RocketMQTopicJob(RocketMQTopicJobService rocketMQTopicJobService) {
        this.rocketMQTopicJobService = rocketMQTopicJobService;
    }
}
