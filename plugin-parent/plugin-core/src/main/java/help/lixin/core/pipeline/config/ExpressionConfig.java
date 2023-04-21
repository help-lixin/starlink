package help.lixin.core.pipeline.config;

import help.lixin.core.pipeline.service.IContextCustomizer;
import help.lixin.core.pipeline.service.IExpressionService;
import help.lixin.core.pipeline.service.impl.BeetlExpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExpressionConfig {

    @Bean
    @ConditionalOnMissingBean
    public IExpressionService expressionService(@Autowired(required = false) IContextCustomizer contextCustomizer) {
        BeetlExpressionService beetlExpressionService = new BeetlExpressionService();
        if (null != contextCustomizer) {
            beetlExpressionService.setContextCustomizer(contextCustomizer);
        }
        return beetlExpressionService;
    }
}
