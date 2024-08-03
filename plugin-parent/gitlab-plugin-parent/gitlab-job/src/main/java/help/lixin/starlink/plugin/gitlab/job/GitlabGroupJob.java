package help.lixin.starlink.plugin.gitlab.job;

import org.springframework.scheduling.annotation.Scheduled;

import help.lixin.starlink.plugin.gitlab.job.service.GitlabGroupJobService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/26 9:49 下午
 * @Description group定时任务
 */
public class GitlabGroupJob {

    private GitlabGroupJobService groupJobService;

    @Scheduled(cron = "${scheduling.cron.gitlab.group}")
    @SchedulerLock(name = "gitlabGroupSchedulerLock", lockAtMostFor = "1m", lockAtLeastFor = "1m")
    public void groupTask() {
        groupJobService.checkGitlabGroupList();
    }

    public GitlabGroupJob(GitlabGroupJobService groupJobService) {
        this.groupJobService = groupJobService;
    }
}
