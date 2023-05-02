package help.lixin.core.engine.service.impl;

import help.lixin.core.definition.PipelineDefinition;
import help.lixin.core.engine.model.PipelineDeploy;
import help.lixin.core.engine.service.IPipelineConverterBpmnModelInstance;
import help.lixin.core.engine.service.IPipelineDeployService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

public class PipelineDeployService implements IPipelineDeployService {

    private static final String SOURCE = "pipline-auto-gen";
    private IPipelineConverterBpmnModelInstance pipelineConverterBpmnModelInstance;

    private RepositoryService repositoryService;

    public PipelineDeployService(RepositoryService repositoryService, IPipelineConverterBpmnModelInstance pipelineConverterBpmnModelInstance) {
        this.repositoryService = repositoryService;
        this.pipelineConverterBpmnModelInstance = pipelineConverterBpmnModelInstance;
    }

    public ProcessDefinition queryProcessDefinitionByDeploymentId(String deploymentId) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                //
                .deploymentId(deploymentId)
                //
                .singleResult();
        return processDefinition;
    }

    @Override
    public PipelineDeploy deploy(PipelineDefinition pipeline) {
        try {
            String resourceName = String.format("%s.bpmn", pipeline.getKey());

            // TODO lixin
            // 部署之前,通过md5验证(pipeline),验证:是否有部署过.
            // 如果,有部署过,则不进行部署,并返回md5的那个版本部署版本回去.
            // 如果,没有部署过,则部署一份.
            // 表结构:
            //  process.getId() ||  process.getKey() || process.getName() || process.getVersion() ||  pipeline_content
            // process.getId() == pipeline-test:1:381e73ad-c3b5-11ed-93fe-52819222aa32

            BpmnModelInstance bpmnModelInstance = pipelineConverterBpmnModelInstance.toBpmnModelInstance(pipeline);
            Deployment deploy = repositoryService.createDeployment()
                    //
                    .source(SOURCE)
                    //
                    .name(resourceName)
                    //
                    .enableDuplicateFiltering(true)
                    //
                    .addModelInstance(resourceName, bpmnModelInstance)
                    //
                    .deploy();

            ProcessDefinition process = queryProcessDefinitionByDeploymentId(deploy.getId());
            PipelineDeploy pipelineDeploy = new PipelineDeploy();
            pipelineDeploy.setId(process.getId());
            pipelineDeploy.setKey(process.getKey());
            pipelineDeploy.setName(process.getName());
            pipelineDeploy.setVersion(process.getVersion());
            return pipelineDeploy;
        } catch (Exception e) {
            // TODO lixin
        }
        return null;
    }

    @Override
    public PipelineDeploy get(String deployId) {
        return null;
    }
}
