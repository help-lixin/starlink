package help.lixin.starlink.plugin.jenkins.job;

import help.lixin.starlink.plugin.jenkins.job.service.IJenkinsInstallPluginJobService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/12 上午10:30
 * @Description
 */
public class JenkinsInstallPluginJob {

    private IJenkinsInstallPluginJobService jenkinsInstallPluginJobService;

    @Scheduled(cron = "${scheduling.cron.jenkins.initPlugin}")
    @SchedulerLock(name = "jenkinsInstallSchedulerLock", lockAtMostFor = "1m", lockAtLeastFor = "1m")
    public void initPluginTask(){
        jenkinsInstallPluginJobService.checkInitPlugin();
    }

    public JenkinsInstallPluginJob(IJenkinsInstallPluginJobService jenkinsInstallPluginJobService) {
        this.jenkinsInstallPluginJobService = jenkinsInstallPluginJobService;
    }
}
