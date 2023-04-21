package help.lixin.shell.config;

import help.lixin.core.pipeline.service.IExpressionService;
import help.lixin.shell.service.ShellFaceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShellConfig {

    @Bean
    public ShellFaceService shellFaceService(IExpressionService expressionService) {
        return new ShellFaceService(expressionService);
    }

}
