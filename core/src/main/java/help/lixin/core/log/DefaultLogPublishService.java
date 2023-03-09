package help.lixin.core.log;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import help.lixin.core.log.util.DisruptorBuilder;
import help.lixin.core.log.util.NamedThreadFactory;

public class DefaultLogPublishService implements ILogPublishService {

    private Disruptor<LogEntry> disruptor;
    private RingBuffer<LogEntry> taskQueue;
    private static final Integer DEFAULT_DISUPTOR_BUFFER_SIZE = 32768;
    private int disruptorBufferSize = DEFAULT_DISUPTOR_BUFFER_SIZE;

    public DefaultLogPublishService() {
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
    }

    public void setDisruptorBufferSize(int disruptorBufferSize) {
        this.disruptorBufferSize = disruptorBufferSize;
    }

    public int getDisruptorBufferSize() {
        return disruptorBufferSize;
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
            System.out.println(event);
            // 记得清除消息体的内容
            event.reset();
        }
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

    @Override
    public void publish(LogEntry logEntry) {
        this.taskQueue.publishEvent(eventTranslator(logEntry));
    }
}
