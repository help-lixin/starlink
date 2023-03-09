package help.lixin.core.log.logback;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.spi.DeferredProcessingAware;
import help.lixin.core.constants.Constant;
import help.lixin.core.log.ILogPublishService;
import help.lixin.core.log.LogEntry;
import help.lixin.core.pipeline.PipelineContextHolder;
import help.lixin.core.pipeline.ctx.PipelineContext;

public class LogbackLogHubAppender<E> extends UnsynchronizedAppenderBase<E> {
    private static final String DEFAULT_PATTERN = "%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}) [%clr(%5p)] %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n%wEx";

    private static final String NAME = "LogbackLogHub";
    private PatternLayoutEncoder encoder = new PatternLayoutEncoder();

    private String pattern = DEFAULT_PATTERN;

    private ILogPublishService logPublishService;

    public void setLogPublishService(ILogPublishService logPublishService) {
        this.logPublishService = logPublishService;
    }

    public ILogPublishService getLogPublishService() {
        return logPublishService;
    }

    public void setEncoder(PatternLayoutEncoder encoder) {
        this.encoder = encoder;
    }

    public PatternLayoutEncoder getEncoder() {
        return encoder;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    @Override
    public void start() {
        encoder.setContext(getContext());
        encoder.setPattern(getPattern());
        encoder.start();
        super.start();
    }


    @Override
    public void setName(String name) {
        super.setName(NAME);
    }

    @Override
    protected void append(E eventObject) {
        if (!isStarted()) {
            return;
        }
        subAppend(eventObject);
    }

    protected void subAppend(E event) {
        if (!isStarted()) {
            return;
        }
        if (event instanceof DeferredProcessingAware) {
            ((DeferredProcessingAware) event).prepareForDeferredProcessing();
        }

        String body = encoder.getLayout().doLayout((ILoggingEvent) event);

        // to entity
        LogEntry logEntry = new LogEntry();
        logEntry.setBody(body);

        // 这样就代表属Pipline的日志.
        PipelineContext ctx = PipelineContextHolder.get();
        if (null != ctx) {
            String instanceId = (String) ctx.getVar(Constant.Pipeline.PIPELINE_INSTANCE_ID);
            String nodeId = (String) ctx.getVar(Constant.Pipeline.PIPELINE_NODE_ID);
            String actionPlugin = (String) ctx.getVar(Constant.Common.ACTION_NAME);

            logEntry.setInstanceId(instanceId);
            logEntry.setNodeId(nodeId);
            logEntry.setActionPlugin(actionPlugin);
        }

        // 异步发送日志,允许丢失消息.
        logPublishService.publish(logEntry);
    }
}
