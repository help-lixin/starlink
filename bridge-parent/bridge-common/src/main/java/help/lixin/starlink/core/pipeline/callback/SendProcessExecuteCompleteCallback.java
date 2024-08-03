package help.lixin.starlink.core.pipeline.callback;

import java.util.Map;

import help.lixin.workflow.service.IProcessExecuteCompleteCallback;

public class SendProcessExecuteCompleteCallback implements IProcessExecuteCompleteCallback {

    private ProcessExecuteMessageProvider processExecuteMessageProvider;

    public SendProcessExecuteCompleteCallback(ProcessExecuteMessageProvider processExecuteMessageProvider) {
        this.processExecuteMessageProvider = processExecuteMessageProvider;
    }

    @Override
    public void callback(Map<String, Object> ctx) {
        processExecuteMessageProvider.sendComplete(ctx);
    }
}
