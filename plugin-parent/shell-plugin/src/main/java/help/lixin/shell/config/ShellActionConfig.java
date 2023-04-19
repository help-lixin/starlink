package help.lixin.shell.config;

import help.lixin.core.pipeline.action.Action;
import help.lixin.shell.action.ShellAction;
import help.lixin.shell.service.ShellFaceService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class ShellActionConfig {

    @Bean
    @ConditionalOnMissingBean(name = "shellAction")
    public Action shellAction(ShellFaceService shellFaceService) {
        return new ShellAction(shellFaceService);
    }
}
