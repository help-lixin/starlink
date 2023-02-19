package help.lixin.core.pipeline.ctx.impl;

import help.lixin.core.pipeline.ctx.PipelineContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Global级别的上下文.
 */
public class GlobalPipelineContext implements PipelineContext {

    private Map<String, Object> vars = new HashMap<>();

    @Override
    public void addVar(String varName, Object varValue) {
        vars.put(varName, varValue);
    }

    @Override
    public Object getVar(String varName) {
        return vars.get(varName);
    }
}
