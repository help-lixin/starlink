package help.lixin.starlink.core.action.thread;

import help.lixin.core.pipeline.PipelineContextHolder;
import help.lixin.core.pipeline.ctx.PipelineContext;

public abstract class ActionThread extends Thread {

    private PipelineContext ctx;

    public ActionThread(PipelineContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void run() {
        try {
            // 为当前新的线程绑定:ctx
            PipelineContextHolder.bind(ctx);
            runInternal();
        } finally {
            PipelineContextHolder.unbind();
        }
    }

    public void setCtx(PipelineContext ctx) {
        this.ctx = ctx;
    }

    public PipelineContext getCtx() {
        return ctx;
    }

    public abstract void runInternal();
}
