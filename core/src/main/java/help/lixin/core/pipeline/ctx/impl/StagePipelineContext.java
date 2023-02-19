package help.lixin.core.pipeline.ctx.impl;

import help.lixin.core.pipeline.ctx.PipelineContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 阶段上下文
 */
public class StagePipelineContext implements PipelineContext {

    private String originParams;

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
    public void setOriginParams(String originParams) {
        this.originParams = originParams;
    }

    @Override
    public String getOriginParams() {
        return new String(originParams);
    }
}
