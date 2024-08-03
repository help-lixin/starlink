package help.lixin.starlink.core.pipeline.callback;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.core.pipeline.constants.Constant;
import help.lixin.transport.client.Client;
import help.lixin.transport.client.model.Message;

public class ProcessExecuteMessageProvider {

    private static final String SUPER_ADMIN = "super-admin";
    private static final String SYSTEM = "starlink-service";
    private static final String SYSTEM_MODULE = "pipeline";
    private static final String PIPLINE_EXECUTE_COMPLETE = "pipeline-complete";
    private static final String PIPLINE_EXECUTE_FAIL = "pipeline-fail";

    private Client client;

    public ProcessExecuteMessageProvider(Client client) {
        this.client = client;
    }

    public void sendFail(Map<String, Object> ctx) {
        String instnaceId = (String)ctx.get(Constant.Pipeline.PIPELINE_INSTANCE_ID);
        String taskId = (String)ctx.get(Constant.Pipeline.PIPELINE_NODE_TASK_ID);
        String nodeId = (String)ctx.get(Constant.Pipeline.PIPELINE_NODE_ID);
        String userName = (String)ctx.get(Constant.Common.USER_NAME);
        // 业务主键ID
        String businessId = (String)ctx.get(Constant.Common.BUSINESS_ID);
        String msg = (String)ctx.get(Constant.Common.ERROR_MSG);

        Map<String, Object> body = new HashMap<>(0);
        body.put(Constant.Common.BUSINESS_ID, businessId);
        body.put("instnaceId", instnaceId);
        body.put("taskId", taskId);
        body.put("nodeId", nodeId);
        if (null != msg) {
            body.put("msg", msg);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            String bodyJson = mapper.writeValueAsString(body);
            client.send(Message.newBuilder() //
                .withTo(userName) //
                .withSendTime(new Date()) //
                .withFrom(SUPER_ADMIN) //
                .withSystem(SYSTEM) //
                .withSystemModule(SYSTEM_MODULE) //
                .withBusinessModule(PIPLINE_EXECUTE_FAIL) //
                .withType(0) // 系统消息(底层用于通信的消息,不会对外进行展示)
                .withBody(bodyJson) //
                .build());
        } catch (JsonProcessingException ignore) {
        }
    }

    /**
     * 发送完成通知
     *
     * @param ctx
     */
    public void sendComplete(Map<String, Object> ctx) {
        String userName = (String)ctx.get(Constant.Common.USER_NAME);
        // 业务主键ID
        String businessId = (String)ctx.get(Constant.Common.BUSINESS_ID);

        Map<String, Object> body = new HashMap<>(0);
        body.put(Constant.Common.BUSINESS_ID, businessId);

        try {
            ObjectMapper mapper = new ObjectMapper();
            String bodyJson = mapper.writeValueAsString(body);
            client.send(Message.newBuilder() //
                .withTo(userName) //
                .withSendTime(new Date()) //
                .withFrom(SUPER_ADMIN) //
                .withSystem(SYSTEM) //
                .withSystemModule(SYSTEM_MODULE) //
                .withBusinessModule(PIPLINE_EXECUTE_COMPLETE) //
                .withType(0) // 系统消息(底层用于通信的消息,不会对外进行展示)
                .withBody(bodyJson) //
                .build());
        } catch (JsonProcessingException ignore) {
        }
    }
}
