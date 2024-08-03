package help.lixin.starlink.plugin.jsch.action.thread;

import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.starlink.core.action.thread.ActionThread;
import help.lixin.starlink.plugin.jsch.action.Tuple;
import help.lixin.starlink.plugin.jsch.action.dto.SSHExecuteResult;
import help.lixin.starlink.plugin.jsch.api.dto.CommandExecuteResult;
import help.lixin.starlink.plugin.jsch.api.properties.JschProperties;
import help.lixin.starlink.plugin.jsch.api.service.IShellExecuteTemplateService;

public class SSHShellThread extends ActionThread {
    private Logger logger = LoggerFactory.getLogger(SSHShellThread.class);
    private final JschProperties jschProperties;
    private final IShellExecuteTemplateService shellExecuteTemplateService;

    private final Tuple tuple;

    private final String instance;
    private final CountDownLatch latch;

    private SSHExecuteResult result;

    public SSHShellThread(PipelineContext ctx, //
        JschProperties jschProperties, //
        IShellExecuteTemplateService shellExecuteTemplateService, //
        String instance, //
        Tuple tuple, //
        SSHExecuteResult result, //
        CountDownLatch latch) {
        super(ctx);
        this.jschProperties = jschProperties;
        this.shellExecuteTemplateService = shellExecuteTemplateService;
        this.instance = instance;
        this.tuple = tuple;
        this.result = result;
        this.latch = latch;

    }

    @Override
    public void runInternal() {
        String host = jschProperties.getHost();
        try {
            String executeBeforeMsg = String.format( //
                "准备调用远程主机[%s],执行如下命令:[\n%s\n]", //
                host, //
                tuple.getRight());
            logger.info(executeBeforeMsg);
            CommandExecuteResult executeResult = shellExecuteTemplateService.execute(tuple.getLeft());

            String executeAfterMsg = String.format( //
                "远程主机[%s],执行命令:[\n%s\n] %s", //
                host, //
                tuple.getRight(), //
                executeResult.isSuccess() ? "成功" : "失败");
            logger.info(executeAfterMsg);
            result.setSuccess(executeResult.isSuccess());
            if (!executeResult.isSuccess()) {
                result.setFailMsg(executeResult.getMsg());
            }
        } catch (Exception e) {
            String msg = String.format("[%s]主机,执行命令[%s]异常,异常详细信息如下:[\n%s\n]", host, tuple.getRight(),
                ExceptionUtils.getMessage(e));
            result.setSuccess(Boolean.FALSE);
            result.setFailMsg(msg);
            throw new RuntimeException(msg, e);
        } finally {
            latch.countDown();
        }
    }
}