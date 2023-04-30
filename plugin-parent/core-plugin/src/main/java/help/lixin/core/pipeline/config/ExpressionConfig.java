package help.lixin.core.pipeline.config;

import help.lixin.core.pipeline.service.IContextCustomizer;
import help.lixin.core.pipeline.service.IExpressionService;
import help.lixin.core.pipeline.service.impl.SpringExpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ExpressionConfig {
    @Bean
    @ConditionalOnMissingBean
    public IExpressionService expressionService(Environment environment, //
                                                @Autowired(required = false) IContextCustomizer contextCustomizer) {
        SpringExpressionService springExpressionService = new SpringExpressionService();
        if (null != contextCustomizer) {
            springExpressionService.setContextCustomizer(contextCustomizer);
        }
        springExpressionService.setEnvironment(environment);
        return springExpressionService;
    }
}
