package help.lixin.starlink.plugin.k8s;

import help.lixin.starlink.plugin.k8s.job.IPodSyncService;
import org.springframework.scheduling.annotation.Scheduled;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/22 下午5:53
 * @Description 获取jenkins构建结果
 */
public class PodSyncJob {

    private IPodSyncService podSyncService;

    @Scheduled(cron = "${scheduling.cron.k8s.pod}")
    @SchedulerLock(name = "k8sPodSchedulerLock", lockAtMostFor = "1m", lockAtLeastFor = "1m")
    public void syncPodJob() {
        podSyncService.syncPod();
    }

    public PodSyncJob(IPodSyncService podSyncService) {
        this.podSyncService = podSyncService;
    }
}
