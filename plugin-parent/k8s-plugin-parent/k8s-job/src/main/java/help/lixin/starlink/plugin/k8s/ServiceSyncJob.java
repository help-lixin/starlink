package help.lixin.starlink.plugin.k8s;

import help.lixin.starlink.plugin.k8s.job.IServiceSyncService;
import org.springframework.scheduling.annotation.Scheduled;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/22 下午5:53
 * @Description 获取jenkins构建结果
 */
public class ServiceSyncJob {

    private IServiceSyncService serviceSyncService;

    @Scheduled(cron = "${scheduling.cron.k8s.service}")
    @SchedulerLock(name = "k8sServiceSchedulerLock", lockAtMostFor = "1m", lockAtLeastFor = "1m")
    public void syncServiceJob() {
        serviceSyncService.syncService();
    }

    public ServiceSyncJob(IServiceSyncService serviceSyncService) {
        this.serviceSyncService = serviceSyncService;
    }
}
