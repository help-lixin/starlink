package help.lixin.core.log.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class NamedThreadFactory implements ThreadFactory {
    private String prefix;

    private AtomicLong inc = new AtomicLong(0);

    public NamedThreadFactory(String prefix, Boolean flag) {
        this.prefix = prefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName(prefix + inc.incrementAndGet());
        return t;
    }
}
