package help.lixin.harbor.config;

import help.lixin.core.pipeline.action.Action;
import help.lixin.harbor.action.HarborAction;
import help.lixin.harbor.service.HarborFaceService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HarborActionConfig {

    @Bean
    @ConditionalOnMissingBean(name = "harborAction")
    public Action harborAction(HarborFaceService harborFaceService) {
        return new HarborAction(harborFaceService);
    }
}
