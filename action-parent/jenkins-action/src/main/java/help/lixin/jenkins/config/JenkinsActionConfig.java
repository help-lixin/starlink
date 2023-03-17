package help.lixin.jenkins.config;

import help.lixin.jenkins.action.JenkinsAction;
import help.lixin.core.pipeline.action.Action;
import help.lixin.jenkins.service.JenkinsFaceService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JenkinsActionConfig {
    @Bean
    @ConditionalOnMissingBean(name = "jenkinsAction")
    public Action jenkinsAction(JenkinsFaceService jenkinsFaceService) {
        return new JenkinsAction(jenkinsFaceService);
    }
}
