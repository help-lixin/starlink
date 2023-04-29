package help.lixin.core.pipeline.ctx;

import java.util.Map;

public interface PipelineContext {
    default void addVar(String varName, Object varValue) {
    }

    default Object getVar(String varName) {
        return null;
    }

    default Map<String, Object> getVars() {
        return null;
    }


    default void setStageParams(String stageParams) {
    }

    default String getStageParams() {
        return null;
    }

    default void copyFor(PipelineContext ctx) {
        Map<String, Object> vars = ctx.getVars();
        getVars().putAll(vars);
    }
}