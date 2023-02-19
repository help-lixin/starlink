package help.lixin.core.pipeline;

import help.lixin.core.definition.ElementDefinition;
import help.lixin.core.definition.PipelineDefinition;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.core.pipeline.ctx.impl.GlobalPipelineContext;
import help.lixin.core.pipeline.ctx.impl.StagePipelineContext;
import help.lixin.core.pipeline.mediator.ActionMediator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Pipeline {
    private ActionMediator actionMediator;

    public Pipeline(ActionMediator actionMediator) {
        this.actionMediator = actionMediator;
    }

    public void execute(PipelineDefinition pipelineDefinition) throws Exception {
        List<ElementDefinition> pipelines = pipelineDefinition.getPipelines();
        // 找到第一个action的定义
        Optional<ElementDefinition> firstElement = pipelines.stream().filter(element -> null == element.getSource()).findFirst();
        // 找到最后一个action的定义
        Optional<ElementDefinition> lastElement = pipelines.stream().filter(element -> null == element.getTarget()).findFirst();

        // 把所有的action打平
        // key: id
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

        PipelineContext globalContext = new GlobalPipelineContext();

        ElementDefinition curr = first;
        executeAction(first);
        do {
            String nextTarget = first.getTarget();
            if (null != nextTarget) {
                curr = all.get(nextTarget);
            } else {
                curr = last;
            }
            executeAction(curr);
        } while (curr != last);
    }

    protected void executeAction(ElementDefinition actionElement) throws Exception {
        String pluginName = actionElement.getPlugin();
        String params = actionElement.getParams();
        Action action = actionMediator.getAction(pluginName);
        if (null == action) {
            // TODO lixin 抛异常
        }

        // 阶段上下文,应该要拷贝来自于Gloabl的信息
        PipelineContext ctx = new StagePipelineContext();
        ctx.setOriginParams(params);
        action.before(ctx);
        action.execute(ctx);
        action.after(ctx);
    }


    /**
     * 从第一个元素开始,验证
     */
    protected void validate(ElementDefinition first,
                            //
                            ElementDefinition last,
                            //
                            Map<String, ElementDefinition> all) {
        // TODO lixin
    }
}
