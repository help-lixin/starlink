package help.lixin.starlink.plugin.k8s;

import help.lixin.starlink.plugin.k8s.job.IDeploymentSyncService;
import org.springframework.scheduling.annotation.Scheduled;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/22 下午5:53
 * @Description 获取jenkins构建结果
 */
public class DeploymentSyncJob {

    private IDeploymentSyncService deploymentSyncService;

    @Scheduled(cron = "${scheduling.cron.k8s.deployment}")
    @SchedulerLock(name = "k8sDeploymentSchedulerLock", lockAtMostFor = "1m", lockAtLeastFor = "1m")
    public void syncDeploymentJob() {
        deploymentSyncService.syncDeployment();
    }

    public DeploymentSyncJob(IDeploymentSyncService deploymentSyncService) {
        this.deploymentSyncService = deploymentSyncService;
    }
}
