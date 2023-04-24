package help.lixin.docker.config;

import help.lixin.core.pipeline.action.Action;
import help.lixin.docker.action.DockerBuildImageAction;
import help.lixin.docker.service.DockerFaceService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DockerActionConfig {

    @Bean
    @ConditionalOnMissingBean(name = "dockerAction")
    public Action dockerAction(DockerFaceService dockerFaceService) {
        return new DockerBuildImageAction(dockerFaceService);
    }
}
