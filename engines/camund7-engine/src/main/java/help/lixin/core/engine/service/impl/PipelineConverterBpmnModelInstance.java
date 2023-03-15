package help.lixin.core.engine.service.impl;

import cn.lzgabel.camunda.converter.BpmnBuilder;
import cn.lzgabel.camunda.converter.bean.ProcessDefinition;
import cn.lzgabel.camunda.converter.bean.task.ServiceTaskDefinition;
import help.lixin.core.constants.Constant;
import help.lixin.core.definition.ElementDefinition;
import help.lixin.core.definition.PipelineDefinition;
import help.lixin.core.definition.impl.PluginDefinition;
import help.lixin.core.engine.service.IPipelineConverterBpmnModelInstance;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PipelineConverterBpmnModelInstance implements IPipelineConverterBpmnModelInstance {

    @Override
    public BpmnModelInstance toBpmnModelInstance(PipelineDefinition pipelineDefinition) throws Exception {
        String key = pipelineDefinition.getKey();
        String name = pipelineDefinition.getName();
        List<ElementDefinition> pipelines = pipelineDefinition.getPipelines();
        List<ServiceTaskDefinition> serviceTasks = converterPluginToServiceTask(pipelines);
        ServiceTaskDefinition rootServiceTask = rootServiceTask(serviceTasks);

        if (null == rootServiceTask) {
            // TODO lixin
        }

        ProcessDefinition processDefinition = ProcessDefinition.builder()
                //
                .name(name)
                //
                .processId(key)
                //
                .processNode(rootServiceTask)
                //
                .build();
        BpmnModelInstance bpmnModelInstance = BpmnBuilder.build(processDefinition);
        return bpmnModelInstance;
    }

    protected List<ServiceTaskDefinition> converterPluginToServiceTask(List<ElementDefinition> pipelines) {
        List<ServiceTaskDefinition> results = new ArrayList<ServiceTaskDefinition>(pipelines.size());

        // 找到第一个Action
        Optional<ElementDefinition> firstElement = pipelines.stream()
                //
                .filter(element -> null == element.getSource()).findFirst();
        // 找到最后一个Action
        Optional<ElementDefinition> lastElement = pipelines.stream()
                //
                .filter(element -> null == element.getTarget()).findFirst();

        // 把所有的Action转换成Map结构.
        // key:   id
        // value: ElementDefinition
        Map<String, ElementDefinition> all = pipelines.stream()
                //
                .collect(Collectors.toMap(ElementDefinition::getId, element -> element));

        ElementDefinition first = null;
        ElementDefinition last = null;
        if (firstElement.isPresent()) {
            first = firstElement.get();
        }

        if (lastElement.isPresent()) {
            last = lastElement.get();
        }

        ElementDefinition curr = first;
        ServiceTaskDefinition task = serviceTaskBuild((PluginDefinition) curr);
        results.add(task);
        do {
            String nextTarget = curr.getTarget();
            if (null != nextTarget) {
                curr = all.get(nextTarget);
            } else {
                curr = last;
            }

            if (curr instanceof PluginDefinition) {
                task = serviceTaskBuild((PluginDefinition) curr);
                results.add(task);
            }
        } while (curr != last);
        return results;
    }


    public ServiceTaskDefinition serviceTaskBuild(PluginDefinition pluginDefinition) {
        ServiceTaskDefinition serviceTaskDefinition = ServiceTaskDefinition.builder()
                //
                .nodeName(pluginDefinition.getName())
                //
                .topic(pluginDefinition.getPlugin())
                //
                .build();
        String params = pluginDefinition.getParams();
        if (null != params && !params.isEmpty()) {
            serviceTaskDefinition.addInputParameter(Constant.Pipeline.INPUT_JSON_PARAM, params);
        }
        return serviceTaskDefinition;
    }

    protected ServiceTaskDefinition rootServiceTask(List<ServiceTaskDefinition> serviceTaskDefinitions) {
        if (null == serviceTaskDefinitions || serviceTaskDefinitions.isEmpty()) {
            return null;
        }

        int len = serviceTaskDefinitions.size();
        int next = 1;
        for (int i = 0; i < len && len != next; i++) {
            ServiceTaskDefinition currentTask = serviceTaskDefinitions.get(i);
            ServiceTaskDefinition nextServiceTask = serviceTaskDefinitions.get(next);
            if (null != nextServiceTask) {
                currentTask.setNextNode(nextServiceTask);
            }
            next++;
        }
        return serviceTaskDefinitions.get(0);
    }
}
