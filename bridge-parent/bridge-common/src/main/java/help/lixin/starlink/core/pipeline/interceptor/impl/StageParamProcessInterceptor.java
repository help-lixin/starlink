package help.lixin.starlink.core.pipeline.interceptor.impl;

import java.util.Map;

import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.core.pipeline.interceptor.IActionExecuteInterceptor;
import help.lixin.service.IExpressionService;

/**
 * 对阶段参数中的变量进行处理.
 */
public class StageParamProcessInterceptor implements IActionExecuteInterceptor {

    private static final String ORIGINAL_PARAMS = "_original_params";

    private IExpressionService expressionService;

    public StageParamProcessInterceptor(IExpressionService expressionService) {
        this.expressionService = expressionService;
    }

    public void setExpressionService(IExpressionService expressionService) {
        this.expressionService = expressionService;
    }

    public IExpressionService getExpressionService() {
        return expressionService;
    }

    @Override
    public void afterExecute(PipelineContext ctx) throws Exception {
        Map<String, Object> vars = ctx.getVars();
        if (null != vars && vars.containsKey(ORIGINAL_PARAMS)) {
            vars.remove(ORIGINAL_PARAMS);
        }
    }

    @Override
    public void beforeExecute(PipelineContext ctx) throws Exception {
        Map<String, Object> vars = ctx.getVars();
        String stageParams = ctx.getStageParams();
        if (null != stageParams //
            && null != vars //
            && null != expressionService) {
            String newStageParams = expressionService.prase(stageParams, vars);
            if (null != newStageParams) {
                ctx.setStageParams(newStageParams);
                ctx.addVar(ORIGINAL_PARAMS, stageParams);
            }
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
