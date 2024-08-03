package help.lixin.starlink.plugin.harbor.action.config;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.core.pipeline.action.Action;
import help.lixin.service.IExpressionService;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.harbor.action.HarborConfigAction;
import help.lixin.starlink.plugin.harbor.service.IHarborProjectService;

@Configuration
public class HarborActionConfig {

    @Bean
    public Action harborAction(
        @Autowired @Qualifier("harborServiceFactory") AbstractServiceFactory harborServiceFactory, //
        StringEncryptor stringEncryptor, //
        IHarborProjectService harborProjectService, //
        IExpressionService expressionService) {
        return new HarborConfigAction(harborProjectService, harborServiceFactory, stringEncryptor, expressionService);
    }
}
