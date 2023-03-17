package help.lixin.core.engine.service;

import help.lixin.core.definition.PipelineDefinition;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

public interface IPipelineConverterBpmnModelInstance {
    BpmnModelInstance toBpmnModelInstance(PipelineDefinition pipelineDefinition) throws Exception;
}
