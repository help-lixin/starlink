package help.lixin.core.pipeline.ctx;

public interface PipelineContext {

    default void addVar(String varName, Object varValue) {
    }

    default Object getVar(String varName) {
        return null;
    }


    default void setOriginParams(String params) {
    }

    default String getOriginParams() {
        return null;
    }
}