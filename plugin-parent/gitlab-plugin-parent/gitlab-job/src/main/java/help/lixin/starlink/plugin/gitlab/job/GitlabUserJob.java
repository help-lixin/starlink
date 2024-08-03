package help.lixin.starlink.plugin.gitlab.job;

import org.springframework.scheduling.annotation.Scheduled;

import help.lixin.starlink.plugin.gitlab.job.service.GitlabUserJobService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/26 9:49 下午
 * @Description user定时任务
 */
public class GitlabUserJob {

    private GitlabUserJobService userJobService;

    @Scheduled(cron = "${scheduling.cron.gitlab.user}")
    @SchedulerLock(name = "gitlabUserSchedulerLock", lockAtMostFor = "1m", lockAtLeastFor = "1m")
    public void userTask() {
        userJobService.checkGitlabUserList();
    }

    public GitlabUserJob(GitlabUserJobService userJobService) {
        this.userJobService = userJobService;
    }
}
