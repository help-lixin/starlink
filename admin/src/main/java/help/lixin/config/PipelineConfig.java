package help.lixin.config;

import help.lixin.core.pipeline.Pipeline;
import help.lixin.core.pipeline.mediator.ActionMediator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PipelineConfig {

    @Bean
    public Pipeline pipeline(ActionMediator actionMediator) {
        return new Pipeline(actionMediator);
    }
}
