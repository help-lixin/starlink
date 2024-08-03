package help.lixin.starlink.plugin.gitlab.job;

import org.springframework.scheduling.annotation.Scheduled;

import help.lixin.starlink.plugin.gitlab.job.service.GitlabProjectJobService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/26 9:49 下午
 * @Description project定时任务
 */
public class GitlabProjectJob {

    private GitlabProjectJobService projectJobService;

    @Scheduled(cron = "${scheduling.cron.gitlab.project}")
    @SchedulerLock(name = "gitlabProjectSchedulerLock", lockAtMostFor = "1m", lockAtLeastFor = "1m")
    public void projectTask() {
        projectJobService.checkGitlabProjectList();
    }

    public GitlabProjectJob(GitlabProjectJobService projectJobService) {
        this.projectJobService = projectJobService;
    }
}
