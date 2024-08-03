package help.lixin.starlink.plugin.ansible.job;

import help.lixin.starlink.plugin.ansible.job.service.IUpdateAnsibleHostsService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/2 下午5:41
 * @Description
 */
public class UpdateAnsibleHostsJob {

    private IUpdateAnsibleHostsService updateAnsibleHostsService;

    public UpdateAnsibleHostsJob(IUpdateAnsibleHostsService jschService) {
        this.updateAnsibleHostsService = jschService;
    }

    @Scheduled(cron = "${scheduling.cron.ansible.updateKnownHostsJob}")
    @SchedulerLock(name = "jschKnownHostSchedulerLock", lockAtMostFor = "1m", lockAtLeastFor = "1m")
    public void updateKnownHostsJob(){
        updateAnsibleHostsService.updateKnownHosts();
    }
}
