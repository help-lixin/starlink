package help.lixin.starlink.core.pipeline.callback;

import java.util.Map;

import help.lixin.workflow.service.IProcessExecuteFailCallback;

public class SendProcessExecuteFailCallback implements IProcessExecuteFailCallback {

    private ProcessExecuteMessageProvider processExecuteMessageProvider;

    public SendProcessExecuteFailCallback(ProcessExecuteMessageProvider processExecuteMessageProvider) {
        this.processExecuteMessageProvider = processExecuteMessageProvider;
    }

    @Override
    public void callback(Map<String, Object> ctx) {
        processExecuteMessageProvider.sendFail(ctx);
    }
}
