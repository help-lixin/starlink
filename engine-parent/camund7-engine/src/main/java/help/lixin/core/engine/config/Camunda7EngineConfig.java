package help.lixin.core.engine.config;

import help.lixin.core.engine.bridge.ITopicSubscribeCustomizer;
import help.lixin.core.engine.bridge.ITopicSubscribeFactory;
import help.lixin.core.engine.bridge.impl.TopicSubscribeFactory;
import help.lixin.core.engine.properties.CamundaClientProperties;
import help.lixin.core.engine.service.IExternalTaskClientCustomizer;
import help.lixin.core.engine.service.IPipelineConverterBpmnModelInstance;
import help.lixin.core.engine.service.IPipelineDeployService;
import help.lixin.core.engine.service.IPipelineRuntimeService;
import help.lixin.core.engine.service.impl.PipelineConverterBpmnModelInstance;
import help.lixin.core.engine.service.impl.PipelineDeployService;
import help.lixin.core.engine.service.impl.PipelineRuntimeService;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.ExternalTaskClientBuilder;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableConfigurationProperties(CamundaClientProperties.class)
public class Camunda7EngineConfig {


//    @Bean(name = {"camundaClient"})
//    @ConditionalOnMissingBean
//    public ExternalTaskClient camundaClient(CamundaClientProperties camundaClientProperties,
//                                            //
//                                            @Autowired(required = false) IExternalTaskClientCustomizer externalTaskClientCustomizer) {
//        ExternalTaskClientBuilder clientBuilder = ExternalTaskClient.create()
//                //
//                .baseUrl(camundaClientProperties.getBaseUrl())
//                //
//                .asyncResponseTimeout(camundaClientProperties.getAsyncResponseTimeout());
//        if (null != externalTaskClientCustomizer) {
//            externalTaskClientCustomizer.customizer(clientBuilder);
//        }
//        return clientBuilder.build();
//    }

//    @Bean
//    @ConditionalOnMissingBean
//    public ITopicSubscribeFactory topicSubscribeFactory(ExternalTaskClient camundaClient,
//                                                        //
//                                                        @Autowired(required = false) List<ITopicSubscribeCustomizer> customizers) {
//        TopicSubscribeFactory topicSubscribeFactory = new TopicSubscribeFactory(camundaClient, customizers);
//        return topicSubscribeFactory;
//    }


    @Bean
    @ConditionalOnMissingBean
    public IPipelineConverterBpmnModelInstance pipelineConverterBpmnModelInstance() {
        return new PipelineConverterBpmnModelInstance();
    }

    @Bean
    public IPipelineRuntimeService pipelineRuntimeService(RuntimeService runtimeService) {
        PipelineRuntimeService pipelineRuntimeService = new PipelineRuntimeService(runtimeService);
        return pipelineRuntimeService;
    }

    @Bean
    public IPipelineDeployService pipelineDeployService(RepositoryService repositoryService, IPipelineConverterBpmnModelInstance pipelineConverterBpmnModelInstance) {
        PipelineDeployService pipelineDeployService = new PipelineDeployService(repositoryService, pipelineConverterBpmnModelInstance);
        return pipelineDeployService;
    }
}