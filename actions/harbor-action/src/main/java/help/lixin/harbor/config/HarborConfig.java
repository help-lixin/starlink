package help.lixin.harbor.config;

import help.lixin.harbor.properties.HarborProperties;
import help.lixin.harbor.service.HarborFaceService;
import help.lixin.harbor.service.IProjectService;
import help.lixin.harbor.service.impl.ProjectService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HarborProperties.class)
public class HarborConfig {

    @Bean
    @ConditionalOnMissingBean(name = "harborProjectService")
    public IProjectService harborProjectService(HarborProperties harborProperties) {
        return new ProjectService(harborProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public HarborFaceService harborFaceService(HarborProperties harborProperties,
                                               //
                                               IProjectService harborProjectService) {
        return new HarborFaceService(harborProperties, harborProjectService);
    }
}
