package help.lixin.core.simple.engine;

import help.lixin.core.constants.Constant;
import help.lixin.core.definition.ElementDefinition;
import help.lixin.core.definition.PipelineDefinition;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.core.pipeline.ctx.impl.GlobalPipelineContext;
import help.lixin.core.pipeline.ctx.impl.StagePipelineContext;
import help.lixin.core.pipeline.mediator.ActionMediator;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class PipelineEngine {
    private ActionMediator actionMediator;

    public PipelineEngine(ActionMediator actionMediator) {
        this.actionMediator = actionMediator;
    }

    public void execute(String pipelineInstaceId) throws Exception {
        // TODO lixin
        // 委派给 execute(PipelineDefinition pipelineDefinition)
    }

    public void execute(PipelineDefinition pipelineDefinition) throws Exception {
        List<ElementDefinition> pipelines = pipelineDefinition.getPipelines();
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

        // 创建global上下文,并配置公共变量
        PipelineContext globalContext = new GlobalPipelineContext();
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        globalContext.getVars().put(Constant.Common.YEAR, cal.get(Calendar.YEAR));
        globalContext.getVars().put(Constant.Common.MONTH, cal.get(Calendar.MONTH) + 1);
        globalContext.getVars().put(Constant.Common.DAY, cal.get(Calendar.DAY_OF_MONTH));
        globalContext.getVars().put(Constant.Common.HOUR, cal.get(Calendar.HOUR));
        globalContext.getVars().put(Constant.Common.MINUTE, cal.get(Calendar.MINUTE));
        globalContext.getVars().put(Constant.Common.SECOND, cal.get(Calendar.SECOND));
        globalContext.getVars().put(Constant.Common.DATETIME, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));

        // 从第一个元素的target开始,依次遍历向后执行.
        ElementDefinition curr = first;
        boolean isExecuteFail = Boolean.FALSE;
        isExecuteFail = executeOne(curr, globalContext);

        do {
            String nextTarget = curr.getTarget();
            if (null != nextTarget) {
                curr = all.get(nextTarget);
            } else {
                curr = last;
            }

            isExecuteFail = executeOne(curr, globalContext);
            // 执行失败,跳出循环.
            if (!isExecuteFail) {
                // 抛个异常出去
                break;
            }
        } while (curr != last);
    }

    protected boolean executeOne(ElementDefinition actionElement,
                                 //
                                 PipelineContext globalContext) throws Exception {
        // TODO lixin

        boolean execute = Boolean.FALSE;
        boolean before = Boolean.FALSE;
        boolean after = Boolean.FALSE;

        String pluginName = actionElement.getPlugin();
        String params = actionElement.getParams();
        Action action = actionMediator.getAction(pluginName);
        if (null == action) {
            throw new Exception("");
        }

        // 1. 创建"阶段性"的的上下文
        PipelineContext stageContext = new StagePipelineContext();
        // 2. 拷贝global的变量到阶段性的上下文中
        stageContext.copyFor(globalContext);
        try {
            stageContext.setStageParams(params);

            // 3. 执行before
            before = action.before(stageContext);
            // 4. 执行: execute
            if (before) { // before成功,才会去执行
                execute = action.execute(stageContext);
            }
            // 5. 执行after
            if (execute) { // 执行成功了,才会执行after
                after = action.after(stageContext);
            }
        } catch (Exception e) {
            // TODO lixin
        } finally {
            // 6. 拷贝"阶段性"的变量到global上下文中
            globalContext.copyFor(stageContext);
        }

        // TODO lixin
        return before && execute && after;
    }
}
