package help.lixin.svn.config;


import help.lixin.core.pipeline.service.IExpressionService;
import help.lixin.svn.service.SvnFaceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SvnConfig {

    @Bean
    public SvnFaceService svnFaceService(IExpressionService expressionService) {
        SvnFaceService svnFaceService = new SvnFaceService();
        svnFaceService.setExpressionService(expressionService);
        return svnFaceService;
    }
}
