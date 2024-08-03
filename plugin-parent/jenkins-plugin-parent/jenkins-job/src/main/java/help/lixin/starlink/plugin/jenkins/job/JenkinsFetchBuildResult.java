package help.lixin.starlink.plugin.jenkins.job;

import help.lixin.starlink.plugin.jenkins.job.service.IJenkinsFetchJobStatusService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/22 下午5:53
 * @Description 获取jenkins构建结果
 */
public class JenkinsFetchBuildResult {

    private IJenkinsFetchJobStatusService jenkinsFetchJobStatusService;

    @Scheduled(cron = "${scheduling.cron.jenkins.fetchBuildResult}")
    @SchedulerLock(name = "jenkinsFetchBuildResultSchedulerLock", lockAtMostFor = "1m", lockAtLeastFor = "1m")
    public void fetchBuildResult(){
        jenkinsFetchJobStatusService.fetchJobStatus();
    }

    public JenkinsFetchBuildResult(IJenkinsFetchJobStatusService jenkinsFetchJobStatusService) {
        this.jenkinsFetchJobStatusService = jenkinsFetchJobStatusService;
    }
}
