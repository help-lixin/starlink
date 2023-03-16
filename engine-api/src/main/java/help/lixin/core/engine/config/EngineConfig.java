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


    // 以下两个做成默认的目的在于就算没有实现类扔进来也不要造成启动失败
    @Bean
    @ConditionalOnMissingBean
    public IPipelineRuntimeService pipelineRuntimeService() {
        return new IPipelineRuntimeService() {
            @Override
            public PipelineInstance startPipelineInstanceById(String pipelineDeployId, String businessKey, Map<String, Object> globalVars, Map<String, Object> localVars) {
                return null;
            }

            @Override
            public PipelineInstance startPipelineInstanceByKey(String pipelineDeployKey, String businessKey, Map<String, Object> globalVars, Map<String, Object> localVars) {
                return null;
            }
        };
    }

    @Bean
    @ConditionalOnMissingBean
    public IPipelineDeployService pipelineDeployService() {
        return new IPipelineDeployService() {
            @Override
            public PipelineDeploy deploy(PipelineDefinition pipeline) {
                return null;
            }
        };
    }

}
