package help.lixin.starlink.plugin.k8s;

import help.lixin.starlink.plugin.k8s.job.INameSpaceSyncService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/22 下午5:53
 * @Description 获取jenkins构建结果
 */
public class NameSpaceSyncJob {

    private INameSpaceSyncService nameSpaceSyncService;

    @Scheduled(cron = "${scheduling.cron.k8s.namespace}")
    @SchedulerLock(name = "k8sNameSpaceSchedulerLock", lockAtMostFor = "1m", lockAtLeastFor = "1m")
    public void syncNameSpaceJob(){
        nameSpaceSyncService.syncNameSpace();
    }

    public NameSpaceSyncJob(INameSpaceSyncService nameSpaceSyncService) {
        this.nameSpaceSyncService = nameSpaceSyncService;
    }
}
