package help.lixin.starlink.plugin.harbor.job;

import org.springframework.scheduling.annotation.Scheduled;

import help.lixin.starlink.plugin.harbor.job.service.HarborProjectJobService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

/**
 * @Author: 伍岳林
 * @Date: 2023/6/2 10:43 上午
 * @Description
 */
public class HarborProjectJob {
    private HarborProjectJobService projectJobService;

    @Scheduled(cron = "${scheduling.cron.harbor.project}")
    @SchedulerLock(name = "harborProjectSchedulerLock", lockAtMostFor = "1m", lockAtLeastFor = "1m")
    public void projectTask() {
        projectJobService.checkHarborProjectList();
    }

    public HarborProjectJob(HarborProjectJobService projectJobService) {
        this.projectJobService = projectJobService;
    }
}
