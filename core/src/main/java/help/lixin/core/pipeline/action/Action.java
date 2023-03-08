package help.lixin.core.pipeline.action;

import help.lixin.core.constants.Constant;
import help.lixin.core.pipeline.PipelineContextHolder;
import help.lixin.core.pipeline.ctx.PipelineContext;

public interface Action {
    default boolean validate(String params) throws Exception {
        return true;
    }

    default boolean before(PipelineContext ctx) throws Exception {
        ctx.addVar(Constant.Common.ACTION_NAME, name());

        PipelineContextHolder.bind(ctx);
        return true;
    }

    default boolean execute(PipelineContext ctx) throws Exception {
        return true;
    }

    default boolean after(PipelineContext ctx) throws Exception {
        ctx.getVars().remove(Constant.Common.ACTION_NAME);
        PipelineContextHolder.unbind();
        return true;
    }

    default String name() {
        return null;
    }
}
