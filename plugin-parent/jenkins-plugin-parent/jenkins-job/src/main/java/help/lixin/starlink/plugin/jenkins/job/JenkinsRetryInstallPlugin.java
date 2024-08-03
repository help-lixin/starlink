package help.lixin.starlink.plugin.jenkins.job;

import help.lixin.starlink.plugin.jenkins.job.service.IJenkinsRetryInstallPluginService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/22 下午5:53
 * @Description 重试安装插件
 */
public class JenkinsRetryInstallPlugin {

    private IJenkinsRetryInstallPluginService retryInstallPluginService;

    @Scheduled(cron = "${scheduling.cron.jenkins.retryInstallPlugin}")
    @SchedulerLock(name = "jenkinsRetryInstallPluginResultSchedulerLock", lockAtMostFor = "1m", lockAtLeastFor = "1m")
    public void retryInstallPlugin(){
        retryInstallPluginService.updatePluginInstallResult();
        retryInstallPluginService.retryInstallPlugin();
    }

    public JenkinsRetryInstallPlugin(IJenkinsRetryInstallPluginService retryInstallPluginService) {
        this.retryInstallPluginService = retryInstallPluginService;
    }
}
