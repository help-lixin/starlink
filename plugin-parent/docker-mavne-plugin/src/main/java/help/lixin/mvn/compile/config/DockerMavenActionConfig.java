package help.lixin.mvn.compile.config;

import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.service.IExpressionService;
import help.lixin.mvn.compile.action.DockerMavenCompileAction;
import help.lixin.mvn.compile.service.DockerMvnFaceService;
import help.lixin.mvn.compile.service.IContainerService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DockerMavenActionConfig {

    @Bean
    public DockerMvnFaceService dockerMvnFaceService(IExpressionService expressionService,
                                                     //
                                                     IContainerService containerService) {
        DockerMvnFaceService dockerMvnFaceService = new DockerMvnFaceService();
        dockerMvnFaceService.setExpressionService(expressionService);
        dockerMvnFaceService.setContainerService(containerService);
        return dockerMvnFaceService;
    }

    @Bean
    @ConditionalOnMissingBean(name = "dockerMavenCompileAction")
    public Action dockerMavenCompileAction(DockerMvnFaceService dockerMvnFaceService) {
        return new DockerMavenCompileAction(dockerMvnFaceService);
    }
}
