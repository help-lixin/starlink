package help.lixin.shell.config;

import help.lixin.shell.service.IContextCustomizer;
import help.lixin.shell.service.IExpressionService;
import help.lixin.shell.service.ShellFaceService;
import help.lixin.shell.service.impl.BeetlExpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShellConfig {


    @Bean
    @ConditionalOnMissingBean
    public IExpressionService expressionService(@Autowired(required = false) IContextCustomizer contextCustomizer) {
        BeetlExpressionService beetlExpressionService = new BeetlExpressionService();
        if (null != contextCustomizer) {
            beetlExpressionService.setContextCustomizer(contextCustomizer);
        }
        return beetlExpressionService;
    }


    @Bean
    public ShellFaceService shellFaceService(IExpressionService expressionService) {
        return new ShellFaceService(expressionService);
    }

}
