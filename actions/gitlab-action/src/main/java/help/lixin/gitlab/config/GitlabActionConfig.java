package help.lixin.gitlab.config;

import help.lixin.gitlab.action.GitlabAction;
import help.lixin.core.pipeline.action.Action;
import help.lixin.gitlab.service.GitlabFaceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GitlabActionConfig {

    @Bean
    public Action gitlabAction(GitlabFaceService gitlabFaceService) {
        return new GitlabAction(gitlabFaceService);
    }
}
