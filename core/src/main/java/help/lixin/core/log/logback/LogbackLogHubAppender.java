package help.lixin.core.log.logback;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.spi.DeferredProcessingAware;
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import help.lixin.core.constants.Constant;
import help.lixin.core.log.LogEntry;
import help.lixin.core.log.util.DisruptorBuilder;
import help.lixin.core.log.util.NamedThreadFactory;
import help.lixin.core.pipeline.PipelineContextHolder;
import help.lixin.core.pipeline.ctx.PipelineContext;

// TODO lixin 后期要把消息发送和和处理抽出来
@Deprecated
public class LogbackLogHubAppender<E> extends UnsynchronizedAppenderBase<E> {
    private static final String DEFAULT_PATTERN = "%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}) [%clr(%5p)] %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n%wEx";

    private static final String NAME = "LogbackLogHub";
    private PatternLayoutEncoder encoder = new PatternLayoutEncoder();

    private Disruptor<LogEntry> disruptor;
    private RingBuffer<LogEntry> taskQueue;
    private static final Integer DEFAULT_DISUPTOR_BUFFER_SIZE = 32768;
    private int disruptorBufferSize = DEFAULT_DISUPTOR_BUFFER_SIZE;

    private String pattern = DEFAULT_PATTERN;

    public void setDisruptorBufferSize(int disruptorBufferSize) {
        this.disruptorBufferSize = disruptorBufferSize;
    }

    public int getDisruptorBufferSize() {
        return disruptorBufferSize;
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

        this.disruptor = DisruptorBuilder.<LogEntry>newInstance() //
                .setEventFactory(new LogEntryFactory()) //
                .setRingBufferSize(getDisruptorBufferSize()) //
                .setThreadFactory(new NamedThreadFactory("Log-Disruptor-", true)) //
                .setProducerType(ProducerType.MULTI) //
                .setWaitStrategy(new BlockingWaitStrategy()) //
                .build();

        this.disruptor.handleEventsWith(new LogEntryHandler());
        this.disruptor.setDefaultExceptionHandler(new LogExceptionHandler<Object>(getClass().getSimpleName()));
        this.taskQueue = this.disruptor.start();

        super.start();
    }

    private class LogEntryFactory implements EventFactory<LogEntry> {
        @Override
        public LogEntry newInstance() {
            return new LogEntry();
        }
    }

    private class LogExceptionHandler<T> implements ExceptionHandler<T> {

        private final String name;

        public LogExceptionHandler(String name) {
            this.name = name;
        }

        @Override
        public void handleEventException(Throwable ex, long sequence, T event) {
            // TODO lixin
        }

        @Override
        public void handleOnStartException(Throwable ex) {
            // TODO lixin
        }

        @Override
        public void handleOnShutdownException(Throwable ex) {
            // TODO lixin
        }
    }

    private class LogEntryHandler implements EventHandler<LogEntry> {
        @Override
        public void onEvent(LogEntry event, long sequence, boolean endOfBatch) throws Exception {
            // TODO lixin
            // 回调给业务系统去保存日志


            // 记得清除消息体的内容
            event.reset();
        }
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
        this.taskQueue.publishEvent(eventTranslator(logEntry));
    }


    protected EventTranslator<LogEntry> eventTranslator(LogEntry logEntry) {
        return new EventTranslator<LogEntry>() {
            @Override
            public void translateTo(LogEntry event, long sequence) {
                event.setInstanceId(logEntry.getInstanceId());
                event.setNodeId(logEntry.getNodeId());
                event.setActionPlugin(logEntry.getActionPlugin());
                event.setBody(logEntry.getBody());
                event.setOthers(logEntry.getOthers());
            }
        };
    }// end eventTranslator
}
