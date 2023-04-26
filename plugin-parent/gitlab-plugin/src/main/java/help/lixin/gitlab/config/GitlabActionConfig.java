package help.lixin.gitlab.config;

import help.lixin.gitlab.action.GitlabCloneAction;
import help.lixin.gitlab.action.GitlabConfigAction;
import help.lixin.core.pipeline.action.Action;
import help.lixin.gitlab.service.GitlabFaceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GitlabActionConfig {

    @Bean
    public Action gitlabConfigAction(GitlabFaceService gitlabFaceService) {
        return new GitlabConfigAction(gitlabFaceService);
    }

    @Bean
    public Action gitlabCloneAction(GitlabFaceService gitlabFaceService) {
        return new GitlabCloneAction(gitlabFaceService);
    }
}
