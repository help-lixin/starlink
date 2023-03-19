package help.lixin.core.simple.engine.config;

import help.lixin.core.engine.service.IPipelineDeployService;
import help.lixin.core.engine.service.IPipelineRuntimeService;
import help.lixin.core.pipeline.mediator.ActionMediator;
import help.lixin.core.simple.engine.PipelineEngine;
import help.lixin.core.simple.engine.service.impl.PipelineDeployService;
import help.lixin.core.simple.engine.service.impl.PipelineRuntimeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleEngineConfig {

    @Bean
    public PipelineEngine pipelineEngine(ActionMediator actionMediator) {
        return new PipelineEngine(actionMediator);
    }

    @Bean
    public IPipelineRuntimeService pipelineRuntimeService(PipelineEngine pipelineEngine) {
        PipelineRuntimeService pipelineRuntimeService = new PipelineRuntimeService(pipelineEngine);
        return pipelineRuntimeService;
    }

    @Bean
    public IPipelineDeployService pipelineDeployService() {
        PipelineDeployService pipelineDeployService = new PipelineDeployService();
        return pipelineDeployService;
    }
}