package help.lixin.action.gitlab.config;

import help.lixin.action.gitlab.GitlabAction;
import help.lixin.core.pipeline.action.Action;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GitlabConfig {

    @Bean
    public Action gitlabAction() {
        return new GitlabAction();
    }
}
