package help.lixin.core.pipeline;

import help.lixin.core.pipeline.ctx.PipelineContext;

public class PipelineContextHolder {
    private static final ThreadLocal<PipelineContext> CTX = new ThreadLocal<>();

    public static PipelineContext get() {
        return CTX.get();
    }

    public static void bind(PipelineContext context) {
        CTX.set(context);
    }

    public static void unbind() {
        CTX.remove();
    }
}
