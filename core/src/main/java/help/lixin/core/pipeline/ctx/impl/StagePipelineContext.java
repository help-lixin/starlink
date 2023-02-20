package help.lixin.core.pipeline.ctx.impl;

import help.lixin.core.pipeline.ctx.PipelineContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 阶段上下文
 */
public class StagePipelineContext implements PipelineContext {

    private String stageParams;

    private Map<String, Object> vars = new HashMap<>();


    @Override
    public void addVar(String varName, Object varValue) {
        vars.put(varName, varValue);
    }

    @Override
    public Object getVar(String varName) {
        return vars.get(varName);
    }

    @Override
    public Map<String, Object> getVars() {
        return vars;
    }

    @Override
    public void setStageParams(String stageParams) {
        this.stageParams = stageParams;
    }

    @Override
    public String getStageParams() {
        return stageParams;
    }
}
