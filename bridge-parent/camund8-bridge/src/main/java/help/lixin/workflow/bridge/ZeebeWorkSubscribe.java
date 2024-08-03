package help.lixin.workflow.bridge;

import java.time.Duration;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.context.SmartLifecycle;

import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.mediator.ActionMediator;
import io.camunda.zeebe.client.ZeebeClient;

/**
 * <zeebe:taskDefinition type="gitlab-clone" retries="3" /> zeebe task 订阅
 */
public class ZeebeWorkSubscribe implements SmartLifecycle {

    private AtomicBoolean isRunning = new AtomicBoolean(false);

    private ZeebeClient zeebeClient;

    private ActionMediator actionMediator;

    private PluginBridge pluginBridge;

    public ZeebeWorkSubscribe(ZeebeClient zeebeClient, //
        ActionMediator actionMediator, //
        PluginBridge pluginBridge) {
        this.zeebeClient = zeebeClient;
        this.actionMediator = actionMediator;
        this.pluginBridge = pluginBridge;
    }

    public ZeebeClient getZeebeClient() {
        return zeebeClient;
    }

    public void setZeebeClient(ZeebeClient zeebeClient) {
        this.zeebeClient = zeebeClient;
    }

    public ActionMediator getActionMediator() {
        return actionMediator;
    }

    public void setActionMediator(ActionMediator actionMediator) {
        this.actionMediator = actionMediator;
    }

    @Override
    public void start() {
        // 遍历所有的action名称,进行订阅
        if (isRunning.compareAndSet(false, true)) {
            Map<String, Action> actions = actionMediator.getActions();
            Iterator<Map.Entry<String, Action>> iterator = actions.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Action> entry = iterator.next();
                String key = entry.getKey();
                zeebeClient.newWorker() //
                    .jobType(key) //
                    .handler(pluginBridge) // ¬
                    .timeout(Duration.ofMillis(60 * 60 * 1000)) //
                    .open();

            }
        }
    }

    @Override
    public void stop() {
        isRunning.set(false);
    }

    @Override
    public boolean isRunning() {
        return isRunning.get();
    }
}
