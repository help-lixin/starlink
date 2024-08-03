package help.lixin.starlink.plugin.k8s;

import org.springframework.scheduling.annotation.Scheduled;

import help.lixin.starlink.plugin.k8s.job.INodeSyncService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/22 下午5:53
 * @Description 获取jenkins构建结果
 */
public class NodeSyncJob {

    private INodeSyncService nodeSyncService;

    @Scheduled(cron = "${scheduling.cron.k8s.node}")
    @SchedulerLock(name = "k8sNodeSchedulerLock", lockAtMostFor = "1m", lockAtLeastFor = "1m")
    public void syncNodeJob() {
        nodeSyncService.syncNode();
    }

    public NodeSyncJob(INodeSyncService nodeSyncService) {
        this.nodeSyncService = nodeSyncService;
    }
}
