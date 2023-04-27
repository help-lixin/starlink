package help.lixin.jib.config;

import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.service.IExpressionService;
import help.lixin.jib.action.JibAction;
import help.lixin.jib.service.JibFaceService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JibActionConfig {

    @Bean
    public JibFaceService jibFaceService(IExpressionService expressionService) {
        JibFaceService faceService = new JibFaceService();
        faceService.setExpressionService(expressionService);
        return faceService;
    }

    @Bean
    @ConditionalOnMissingBean(name = "jibImageBuildAction")
    public Action jibImageBuildAction(JibFaceService jibFaceService) {
        return new JibAction(jibFaceService);
    }
}
