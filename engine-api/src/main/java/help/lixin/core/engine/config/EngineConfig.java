package help.lixin.core.engine.config;

import help.lixin.core.definition.PipelineDefinition;
import help.lixin.core.engine.model.PipelineDeploy;
import help.lixin.core.engine.model.PipelineInstance;
import help.lixin.core.engine.service.IPipelineDeployCallbackService;
import help.lixin.core.engine.service.IPipelineDeployService;
import help.lixin.core.engine.service.IPipelineRuntimeService;
import help.lixin.core.engine.service.IStartPipelineCallbackService;
import help.lixin.core.engine.service.impl.PipelineDeployCallbackService;
import help.lixin.core.engine.service.impl.PipelineDeployTemplate;
import help.lixin.core.engine.service.impl.PipelineRuntimeTemplate;
import help.lixin.core.engine.service.impl.StartPipelineCallbackService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class EngineConfig {

    @Bean
    @ConditionalOnMissingBean
    public IStartPipelineCallbackService startPipelineCallbackService() {
        StartPipelineCallbackService startPipelineCallbackService = new StartPipelineCallbackService();
        return startPipelineCallbackService;
    }

    @Bean
    @ConditionalOnMissingBean
    public IPipelineDeployCallbackService pipelineDeployCallbackService() {
        PipelineDeployCallbackService pipelineDeployCallbackService = new PipelineDeployCallbackService();
        return pipelineDeployCallbackService;
    }

    @Bean
    @ConditionalOnMissingBean
    public PipelineRuntimeTemplate pipelineRuntimeTemplate(IPipelineRuntimeService pipelineRuntimeService,
                                                           //
                                                           IStartPipelineCallbackService startPipelineCallbackService) {
        PipelineRuntimeTemplate pipelineRuntimeTemplate = new PipelineRuntimeTemplate(pipelineRuntimeService, startPipelineCallbackService);
        return pipelineRuntimeTemplate;
    }

    @Bean
    @ConditionalOnMissingBean
    public PipelineDeployTemplate pipelineDeployTemplate(IPipelineDeployService pipelineDeployService,
                                                         //
                                                         IPipelineDeployCallbackService pipelineDeployCallbackService) {
        PipelineDeployTemplate pipelineDeployTemplate = new PipelineDeployTemplate(pipelineDeployService, pipelineDeployCallbackService);
        return pipelineDeployTemplate;
    }
}
