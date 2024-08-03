package help.lixin.config;

import help.lixin.service.IContextCustomizer;
import help.lixin.service.IExpressionService;
import help.lixin.service.impl.SpringExpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
public class ExpressionConfig {

    @Bean
    @ConditionalOnMissingBean(name = "expressionService")
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
