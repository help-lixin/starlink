package help.lixin.core.engine;

import help.lixin.core.engine.service.IPipelineConverterBpmnModelInstance;
import help.lixin.core.engine.service.impl.PipelineConverterBpmnModelInstance;
import help.lixin.core.engine.service.IPipelineDeployService;
import help.lixin.core.engine.service.IPipelineRuntimeService;
import help.lixin.core.engine.service.impl.PipelineDeployService;
import help.lixin.core.engine.service.impl.PipelineRuntimeService;
import org.camunda.bpm.engine.RepositoryService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamundaSevenEngineConfig {

    @Bean
    @ConditionalOnMissingBean
    public IPipelineConverterBpmnModelInstance pipelineConverterBpmnModelInstance() {
        return new PipelineConverterBpmnModelInstance();
    }

    @Bean
    @ConditionalOnMissingBean
    public IPipelineRuntimeService pipelineRuntimeService() {
        PipelineRuntimeService pipelineRuntimeService = new PipelineRuntimeService();
        return pipelineRuntimeService;
    }

    @Bean
    @ConditionalOnMissingBean
    public IPipelineDeployService pipelineDeployService(RepositoryService repositoryService, IPipelineConverterBpmnModelInstance pipelineConverterBpmnModelInstance) {
        PipelineDeployService pipelineDeployService = new PipelineDeployService(repositoryService, pipelineConverterBpmnModelInstance);
        return pipelineDeployService;
    }
}