package help.lixin.oci.config;

import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.service.IExpressionService;
import help.lixin.oci.action.OCIAction;
import help.lixin.oci.service.JibFaceService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OCIActionConfig {

    @Bean
    public JibFaceService jibFaceService(IExpressionService expressionService) {
        JibFaceService faceService = new JibFaceService();
        faceService.setExpressionService(expressionService);
        return faceService;
    }

    @Bean
    @ConditionalOnMissingBean(name = "imageBuildAction")
    public Action imageBuildAction(JibFaceService jibFaceService) {
        return new OCIAction(jibFaceService);
    }
}
