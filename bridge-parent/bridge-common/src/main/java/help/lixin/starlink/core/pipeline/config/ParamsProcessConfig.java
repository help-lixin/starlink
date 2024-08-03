package help.lixin.starlink.core.pipeline.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.core.pipeline.interceptor.IActionExecuteInterceptor;
import help.lixin.service.IExpressionService;
import help.lixin.starlink.core.pipeline.interceptor.impl.StageParamProcessInterceptor;

@Configuration
public class ParamsProcessConfig {

    @Bean
    @ConditionalOnMissingBean(name = "stageParamProcessInterceptor")
    public IActionExecuteInterceptor stageParamProcessInterceptor(IExpressionService expressionService) {
        return new StageParamProcessInterceptor(expressionService);
    }
}
